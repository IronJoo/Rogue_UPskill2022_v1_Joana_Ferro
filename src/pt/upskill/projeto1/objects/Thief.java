package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
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

    @Override
    public String getName() {
        return "Thief";
    }

    public Thief(Position position) {
        super(position);
    }
    @Override
    public void move(Map map) {
        Direction direction = toDirection(new Random().nextInt(4));
        Position nextPosition = super.getPosition().plus(direction.asVector());
        if (!map.isWall(nextPosition) && !map.isHero(nextPosition) && !map.isEnemy(nextPosition)) {
            super.setPosition(nextPosition);
        }
    }
    private Direction toDirection(int num){ //converts random int into one of the four directions

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
