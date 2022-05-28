package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.Random;

public class Enemy extends Entity implements ImageTile {

    public Enemy(Position position, int health, int damage) {
        super(position, health, damage);
    }
    public Enemy(int damage){
        super(damage);
    }
    public Enemy(Position position, int damage){
        super(position, damage);
    }

    @Override
    public String getName() {
        return "Enemy";
    }
//    public void moveEnemy(){
//        int num = new Random().nextInt(4);
//        switch (num){
//            case 1:
//                if (!map.isWall(position.plus(Direction.DOWN.asVector())))
//                    position = position.plus(Direction.DOWN.asVector());
//            case 2:
//
//        }
//    }


}
