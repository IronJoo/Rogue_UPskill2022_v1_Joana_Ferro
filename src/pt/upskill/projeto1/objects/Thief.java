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
            case 0:
                if (!map.isWall(super.getPosition().plus(Direction.UPLEFT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.UPLEFT.asVector()));
                System.out.println("UPLEFT");
                break;
            case 1:
                if (!map.isWall(super.getPosition().plus(Direction.UPRIGHT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.UPRIGHT.asVector()));
                System.out.println("UPRIGHT");
                break;
            case 2:
                if (!map.isWall(super.getPosition().plus(Direction.DOWNLEFT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.DOWNLEFT.asVector()));
                System.out.println("DOWNLEFT");
                break;
            case 3:
                if (!map.isWall(super.getPosition().plus(Direction.DOWNRIGHT.asVector())))
                    super.setPosition(super.getPosition().plus(Direction.DOWNRIGHT.asVector()));
                System.out.println("DOWNRIGHT");
                break;

        }
    }
}
