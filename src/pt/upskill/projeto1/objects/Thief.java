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
    public void move(Map map){
        int num = new Random().nextInt(4);
        switch (num){
            case 1:
                if (!map.isWall(super.getPosition().plus(Direction.DOWN.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.DOWN.asVector()));
            case 2:
                if (!map.isWall(super.getPosition().plus(Direction.UP.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.UP.asVector()));
            case 3:
                if (!map.isWall(super.getPosition().plus(Direction.LEFT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.LEFT.asVector()));
            case 4:
                if (!map.isWall(super.getPosition().plus(Direction.RIGHT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.RIGHT.asVector()));

        }
    }
}
