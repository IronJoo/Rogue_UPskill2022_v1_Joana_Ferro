package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hero extends Entity implements ImageTile {
    //private String currentRoom;
    private ArrayList<Item> inventory = new ArrayList<Item>();

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
        switch(keyPressed){
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                break;
            default:
                return;
        }
        Direction direction = toDirection(keyPressed);
        Position nextPosition = super.getPosition().plus(direction.asVector());
            if (!map.findsCollision(nextPosition)) {
                super.setPosition(nextPosition);
            }
    }
    private Direction toDirection(int keyPressed){

        switch (keyPressed){
            case (KeyEvent.VK_DOWN):
                return Direction.DOWN;
            case (KeyEvent.VK_LEFT):
                return Direction.LEFT;
            case (KeyEvent.VK_RIGHT):
                return Direction.RIGHT;
            default:
                return Direction.UP;
        }

    }
}
