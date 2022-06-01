package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.Random;

public abstract class Enemy extends Entity implements ImageTile {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

    public Enemy(Position position, int health, int damage) {
        super(position, health, damage);
    }

    public Enemy(int damage) {
        super(damage);
    }

    public Enemy(Position position, int damage) {
        super(position, damage);
    }

    public Enemy(Position position) {
        super(position);
    }

    public void move(Room room, Entity entity) {
        int distance = calculateDistance(entity);
        if (distance > 4) {             //if hero is distant, move randomly
            Direction direction = toDirection(new Random().nextInt(4));
            Position nextPosition = super.getPosition().plus(direction.asVector());
            if (!room.findsCollision(nextPosition)) {
                super.setPosition(nextPosition);
            }
        } else if (distance > 1){       //if hero is close, move towards him
            moveToDirectionOf(entity, room);

        } else if (distance == 1 && (calculateYDifference(getPosition(), entity.getPosition()) == 0 || calculateXDifference(getPosition(), entity.getPosition()) == 0)){ //if enemy is next to hero && in same x or y, attack
            entity.receiveDamage(getDamage());
            gui.setStatus("Hero health = " + entity.getHealth());
            //System.out.println("Hero health = " + entity.getHealth());
        }
    }
    public int calculateXDifference (Position enemy, Position hero){
        return (getPosition().getX() - hero.getX());
    }
    public int calculateYDifference (Position enemy, Position hero){
        return (getPosition().getY() - hero.getY());
    }
    private void moveToDirectionOf(Entity entity, Room room){
        int xDifference = calculateXDifference(getPosition(), entity.getPosition());
        int yDifference = getPosition().getY() - entity.getPosition().getY();

        int randomCoordinate = new Random().nextInt(2); //enemy will use either y or x coodinate to get closer to hero
        switch (randomCoordinate){
            case 0:
                if (xDifference > 0){
                    Position nextPosition = super.getPosition().plus(Direction.LEFT.asVector());
                    if (!room.findsCollision(nextPosition)) {
                        setPosition(nextPosition);
                        //System.out.println("Moving to the left");
                    }
                }
                else if (xDifference < 0){
                    Position nextPosition = super.getPosition().plus(Direction.RIGHT.asVector());
                    if (!room.findsCollision(nextPosition)) {
                        setPosition(nextPosition);
                        //System.out.println("Moving to the right");
                    }
                }
                break;
            default:
                if (yDifference > 0){
                    Position nextPosition = super.getPosition().plus(Direction.UP.asVector());
                    if (!room.findsCollision(nextPosition)) {
                        setPosition(nextPosition);
                        //System.out.println("Moving up");
                    }
                }
                else if (yDifference < 0){
                    Position nextPosition = super.getPosition().plus(Direction.DOWN.asVector());
                    if (!room.findsCollision(nextPosition)) {
                        setPosition(nextPosition);
                        //System.out.println("Moving down");
                    }
                }
                break;
        }
    }
    public int calculateDistance(Entity entity){
        return (int)(Math.sqrt(Math.pow(entity.getPosition().getX() - getPosition().getX(), 2) + Math.pow(entity.getPosition().getY() - getPosition().getY(), 2)));
    }
    private Direction toDirection(int num){ //converts random int into one of the four directions

        switch (num){
            case (0):
                return Direction.DOWN;
            case (1):
                return Direction.LEFT;
            case (2):
                return Direction.RIGHT;
            default:
                return Direction.UP;
        }
    }
    @Override
    public void receiveDamage(int amount){
        if (super.getHealth() - amount <= 0)
            super.setHealth(0);
        else
            super.setHealth(super.getHealth() - amount);
        if (super.getHealth() == 0){
            super.setPosition(new Position(-1, -1)); //when enemy dies, it is moved to out of view

        }
    }
}




