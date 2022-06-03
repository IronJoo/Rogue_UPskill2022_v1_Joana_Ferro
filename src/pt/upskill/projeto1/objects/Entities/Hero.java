package pt.upskill.projeto1.objects.Entities;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.Weapons.Fireball;
import pt.upskill.projeto1.objects.RoomElements.Door;
import pt.upskill.projeto1.objects.Items.Item;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.Weapons.Weapon;
import pt.upskill.projeto1.objects.StatusBar.StatusBar;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero extends Entity implements ImageTile {
    //private String currentRoom;
    private ArrayList<Item> inventory = new ArrayList<Item>(); //inventory is limited to three items
    private ArrayList<Fireball> fireballList = new ArrayList<Fireball>();
    private int numberOfFireballs = 3;
    private Weapon weapon = null;
    private int currentRoom = 0;
    private StatusBar statusBar = new StatusBar();
    private boolean isDead = false;

//    public Hero(Position position) {
//
//        super(position); //to do: set health and damage values
//        //this.currentRoom = currentRoom;
//    }
    public Hero(){
        super(100,10);
//        fireballList.add(new Fireball());
//        fireballList.add(new Fireball());
//        fireballList.add(new Fireball());
        //currentRoom = "./rooms/room0.txt";
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ArrayList<Fireball> getFireballList() {
        return fireballList;
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

    public void init(int keyPressed, Map map) {
        Room room = map.getCurrentRoom();
        switch (keyPressed) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
                break;
            default:
                return;
        }
        Direction direction = toDirection(keyPressed);
        Position nextPosition = super.getPosition().plus(direction.asVector());
        if (!room.findsCollision(nextPosition)) {
            super.setPosition(nextPosition);
        }
        if (room.isClosedDoor(nextPosition)) {
            for (ImageTile tile : room.getMapTiles()) {
                if (tile.getPosition().equals(nextPosition) && tile instanceof Door) {
                    //if (hero has key and it matches door key)
                    ((Door) tile).setType("D");
                    ((Door) tile).setOpen(true); //"D" and "true" are required for Door to [getName() = "DoorOpen"]
                }
            }
        }
        if (room.isEnemy(nextPosition)) { //attack
            for (ImageTile tile : room.getMapTiles()) {
                if (tile.getPosition().equals(nextPosition) && tile instanceof Enemy) {
                    int totalDamage = getDamage();
                    if (hasWeapon()) {
                        totalDamage += weapon.getDamage();
                    }
                    ((Enemy) tile).receiveDamage(totalDamage);
                    //statusBar.update(getHealth(),getFireballList(),getInventory());
                    System.out.println("Enemy health = " + ((Enemy) tile).getHealth());
                }
            }
        }
        if (room.isOpenDoor(getPosition())) {
            for (Door door : room.getDoorList()) {
                if (door.getPosition().equals(getPosition()) && (door.getName().equals("DoorOpen") || door.getName().equals("DoorWay"))) {
                    setCurrentRoom(door.getLeadsToRoom());
                    //setPosition(new Position(1,1)); //to do: set proper position
                    map.changeRoom(door, getCurrentRoom(), this);

                }
            }
        }
        if (room.isItem(nextPosition)) {
            for (Item item : room.getItemList()) {
                if (item.getPosition().equals(nextPosition) && item instanceof Meat) {
                    super.addHealth(new Meat().getHealthValue()); //bad practice! fix later: get value of actual piece of meat and not from a new instance
                    moveItemToOutOfView(item);
                } else if (item.getPosition().equals(nextPosition) && item instanceof Weapon) {
                    switchWeapon((Weapon)item, map);
//                    weapon = (Weapon) item;
//                    moveItemToOutOfView(item);
                } else if (item.getPosition().equals(nextPosition) && item instanceof Item) {
                    addToInventory(item);
                }
            }
        }
        //ImageMatrixGUI statusGui = ImageMatrixGUI.getInstance();
        //ArrayList<ImageTile> statusBarList = statusBar.getStatusList();
        //statusGui.newStatusImages(statusBarList);
        statusBar.update(getHealth());
        //statusBar.update(getHealth(),numberOfFireballs,inventory);
    }
    private void switchWeapon(Weapon newWeapon, Map map){
//        if (hasWeapon()) {  //to do: hero drops old weapon on the floor and picks up new one
//            Weapon droppedWeapon = weapon;
//            droppedWeapon.setPosition(getPosition());
//            map.getCurrentRoom().getMapTiles().add(droppedWeapon);
//            itemList.add(droppedWeapon);
//        }
        addToInventory(newWeapon);
    }
    private void addToInventory(Item item) {
        if (!inventoryFull()){
            inventory.add(item);
            moveItemToOutOfView(item);
            if (item instanceof Weapon)
                weapon = (Weapon) item;
        }
    }
    private boolean inventoryFull(){
        int i = 0;
        for (Item item : inventory){
            i++;
        }
        if (i == 3){
            return true;
        }
        return false;
    }

    private boolean hasWeapon() {
        return weapon != null;
    }
    private void moveItemToOutOfView(Item item){
        item.setPosition(new Position(9, -1));
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
    public void receiveDamage(int amount){
        if (super.getHealth() - amount <= 0) {
            super.setHealth(0); //to do: add to gui
            //this.dies();
            //setDead(true);
        }
        else
            super.setHealth(super.getHealth() - amount);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void dies(){ //to do: FIX!!! NOT WORKING
        //ImageMatrixGUI newGui = ImageMatrixGUI.getInstance();
        //Map newMapAfterDeath = new Map(newGui);
        //newGui.setEngine(??);
        //newMapAfterDeath.runRoomEngine(0, new Hero());
        // -------
    }
}
