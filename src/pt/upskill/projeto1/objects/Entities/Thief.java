package pt.upskill.projeto1.objects.Entities;

import javafx.geometry.Pos;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.Random;

public class Thief extends Enemy implements ImageTile {
    public Thief(Position position, int health, int damage) {
        super(position, health, damage);
    }

    public Thief(int damage) {
        super(damage);
    }

    public Thief(Position position, int damage) {
        super(position, damage);
    }
    public Thief(Position position) {
        super(position);
        super.setDamage(10);
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public void update(Room room, Entity entity) {
        int distance = calculateDistance(entity);
        if (distance > 4) {             //if hero is distant, move randomly
            Direction direction = toDirection(new Random().nextInt(4));
            Position nextPosition = super.getPosition().plus(direction.asVector());
            if (!room.findsCollision(nextPosition)) {
                super.setPosition(nextPosition);
            }
        } else if (distance > 1) {       //if hero is close, move towards him
            moveToDirectionOf(entity, room);
        } else if (distance == 1 && isDiagonalFrom(entity)) { //if enemy is next to hero, attack
            entity.receiveDamage(getDamage()); //TO DO: fix thief not attacking
        }
    }
    public boolean isDiagonalFrom(Entity entity){
        Position heroPosition = entity.getPosition();
        Position thiefDiagonal0 = getPosition().plus(toDirection(0).asVector());
        Position thiefDiagonal1 = getPosition().plus(toDirection(1).asVector());
        Position thiefDiagonal2 = getPosition().plus(toDirection(2).asVector());
        Position thiefDiagonal3 = getPosition().plus(toDirection(3).asVector());
        if (heroPosition.equals(thiefDiagonal0) || heroPosition.equals(thiefDiagonal1) ||
                heroPosition.equals(thiefDiagonal2) || heroPosition.equals(thiefDiagonal3))
            return true;
        return false;
    }
    private void moveToDirectionOf(Entity entity, Room room){
        int xDifference = calculateXDifference(getPosition(), entity.getPosition());
        int yDifference = getPosition().getY() - entity.getPosition().getY();

        int randomCoordinate = new Random().nextInt(2);
        int randomDirection = new Random().nextInt(2);
        Position nextPosition;
        switch (randomCoordinate){
            case 0:
                if (xDifference > 0){   //if thief wants to move to the left, he can either move upleft or downleft
                    switch (randomDirection) {
                        case 0:
                            nextPosition = super.getPosition().plus(Direction.UPLEFT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                        default:
                            nextPosition = super.getPosition().plus(Direction.DOWNLEFT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                    }
                }
                else if (xDifference < 0){ //if thief wants to move to the right, he can either move upright or downright
                    switch (randomDirection) {
                        case 0:
                            nextPosition = super.getPosition().plus(Direction.UPRIGHT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                        default:
                            nextPosition = super.getPosition().plus(Direction.DOWNRIGHT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                    }
                }
                break;
            default:
                if (yDifference > 0){   //if thief wants to move up, he can either move upleft or upright
                    switch (randomDirection) {
                        case 0:
                            nextPosition = super.getPosition().plus(Direction.UPLEFT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                        default:
                            nextPosition = super.getPosition().plus(Direction.UPRIGHT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                    }
                }
                else if (yDifference < 0){ //if thief wants to move down, he can either move downleft or downright
                    switch (randomDirection) {
                        case 0:
                            nextPosition = super.getPosition().plus(Direction.UPRIGHT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                        default:
                            nextPosition = super.getPosition().plus(Direction.DOWNRIGHT.asVector());
                            if (!room.findsCollision(nextPosition)) {
                                setPosition(nextPosition);
                            }
                            break;
                    }
                }
                break;
        }
    }
    private Direction toDirection(int num){ //converts int into one of the four directions

        switch (num){
            case (0):
                return Direction.UPLEFT;
            case (1):
                return Direction.UPRIGHT;
            case (2):
                return Direction.DOWNLEFT;
            default:
                return Direction.DOWNRIGHT;
        }
    }
}
