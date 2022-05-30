package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.Random;

public abstract class Enemy extends Entity implements ImageTile {

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

    public void move(Map map) {
        Direction direction = toDirection(new Random().nextInt(4));
        Position nextPosition = super.getPosition().plus(direction.asVector());
        if (!map.findsCollision(nextPosition)) {
            super.setPosition(nextPosition);
        }
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
}




