package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero extends Entity implements ImageTile {
    //private String currentRoom;

    public Hero(Position position) {

        super(position);
        //this.currentRoom = currentRoom;
    }
    public Hero(){
        super(100,10);
        //currentRoom = "./rooms/room0.txt";
    }

    @Override
    public String getName() {
        return "Hero";
    }
//    public String getCurrentRoom() {
//        return currentRoom;
//    }

//    public void setCurrentRoom(String currentRoom) {
//        this.currentRoom = currentRoom;
//    }

    public void move(int keyPressed, Map map) {
        if (keyPressed == KeyEvent.VK_DOWN) {
            if (!map.isWall(super.getPosition().plus(Direction.DOWN.asVector()))) {
                super.setPosition(super.getPosition().plus(Direction.DOWN.asVector()));
            }
//            if (!map.isEnemy(super.getPosition().plus(Direction.DOWN.asVector()))){
//                enemy.moveEnemy();
                //if (super.getPosition().plus(Direction.DOWN.asVector()) ))
            //}
        }
        if (keyPressed == KeyEvent.VK_UP) {
            if (!map.isWall(super.getPosition().plus(Direction.UP.asVector())))
                super.setPosition(super.getPosition().plus(Direction.UP.asVector()));
        }
        if (keyPressed == KeyEvent.VK_LEFT) {
            if (!map.isWall(super.getPosition().plus(Direction.LEFT.asVector())))
                super.setPosition(super.getPosition().plus(Direction.LEFT.asVector()));
        }
        if (keyPressed == KeyEvent.VK_RIGHT) {
            if (!map.isWall(super.getPosition().plus(Direction.RIGHT.asVector())))
                super.setPosition(super.getPosition().plus(Direction.RIGHT.asVector()));
        }
    }
}
