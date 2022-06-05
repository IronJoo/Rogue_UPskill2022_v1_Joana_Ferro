package pt.upskill.projeto1.objects.Entities;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.objects.Items.Weapons.Fireball;
import pt.upskill.projeto1.objects.RoomElements.Door;
import pt.upskill.projeto1.objects.Items.Item;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.Weapons.Weapon;
import pt.upskill.projeto1.objects.StatusBar.StatusBar;
import pt.upskill.projeto1.rogue.utils.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Hero extends Entity implements ImageTile {
    //private String currentRoom;
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private HashMap<Integer, Item> inventory = new HashMap<Integer, Item>(); //inventory is limited to three items
    //private ArrayList<Fireball> fireballList = new ArrayList<Fireball>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private int numberOfFireballs = 3;
    private ItemObserver itemObserver;
    private Weapon weapon = null;
    private boolean hasWeapon = false;
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

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public int getNumberOfFireballs() {
        return numberOfFireballs;
    }
    //    public ArrayList<Fireball> getFireballList() {
//        return fireballList;
//    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
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
        if (!isOutOfBounds(nextPosition)){ //prevents hero from moving out of bounds after being teleported to next room's door
            if (!room.findsCollision(nextPosition)) {
                super.setPosition(nextPosition);
            }
            if (room.isEnemy(nextPosition)) { //attack
                for (Enemy enemy : room.getEnemyList()) {
                    if (enemy.getPosition().equals(nextPosition)) {
                        int totalDamage = getDamage();
                        if (hasWeapon)
                            totalDamage += weapon.getDamage();
                        System.out.println("Total damage = " + totalDamage);
                        ((Enemy) enemy).receiveDamage(totalDamage);
                        //statusBar.update(getHealth(),getFireballList(),getInventory());
                        System.out.println("Enemy health = " + ((Enemy) enemy).getHealth());
                        break; //break the cycle when corresponding enemy was found, no need to keep cycling afterwards
                    }
                }
            }
            if (room.isClosedDoor(nextPosition)) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(nextPosition) && door instanceof Door) {
                        if (door.requiresKey() && heroHasKey(door)) { //if (hero has key and it matches door key)
                            door.setRequiresKey(false);
                            door.setType("D");
                            door.setOpen(true); //"D" and "true" are required for Door to [getName() = "DoorOpen"]
                            gui.setStatus("You have successfuly opened the door!");
                            break;
                        } else if (door.requiresKey() && !heroHasKey(door)) {
                            gui.setStatus("You do not have the right key to open this door!");
                        } else if (!door.requiresKey()) {
                            door.setType("D");
                            door.setOpen(true); //"D" and "true" are required for Door to [getName() = "DoorOpen"]
                            break;
                        }
                    }
                }
            }
            if (room.isOpenDoor(getPosition())) {
                for (Door door : room.getDoorList()) {
                    if (door.getPosition().equals(getPosition()) && (door.getName().equals("DoorOpen") || door.getName().equals("DoorWay"))) {
                        //System.out.println(door.toString());
                        setCurrentRoom(door.getLeadsToRoom());
                        map.changeRoom(door, getCurrentRoom(), this);
                        break; //this break is required or for loop will continue reading doors from the new room??
                    }
                }
            }
            if (room.isItem(nextPosition)) {
                for (Item item : room.getItemList()) {
                    if (item.getPosition().equals(nextPosition) && item instanceof Meat) {
                        super.addHealth(((Meat) item).getHealthValue());
                        moveItemToOutOfView(item);
                        gui.setStatus("You have eaten some food.");
                        break;
                    } else if (item.getPosition().equals(nextPosition) && item instanceof Weapon) {
                        switchWeapon((Weapon) item, map);
                        gui.setStatus("You have picked up a weapon.");
//                    weapon = (Weapon) item;
//                    moveItemToOutOfView(item);
                        break;
                    } else if (item.getPosition().equals(nextPosition) && item instanceof Key) {
                        addToInventory(item);
                        gui.setStatus("You have picked up a key.");
                        break;
                    } else if (item.getPosition().equals(nextPosition) && item instanceof Item) {
                        addToInventory(item);
                        gui.setStatus("You have picked up an item.");
                        break;
                    }
                }
            }
        }
        //statusBar.update(getHealth(), getNumberOfFireballs(), getInventory());

    }
    private boolean isOutOfBounds(Position position){ //checks if hero is out of visual tiles
        if (position.getX() == -1 || position.getX() == 10)
            return true;
        if (position.getY() == -1 || position.getY() == 10)
            return true;
        return false;
    }
    private boolean heroHasKey(Door door){
        for (Key key : keyList){
            if (door.getRequiredKey().equals(key.getKeyName()))
                return true;
        }
        return false;
    }
    private void switchWeapon(Weapon newWeapon, Map map) {
//        if (hasWeapon()) {  //to do: hero drops old weapon on the floor and picks up new one
//            Weapon droppedWeapon = weapon;
//            droppedWeapon.setPosition(getPosition());
//            map.getCurrentRoom().getMapTiles().add(droppedWeapon);
//            itemList.add(droppedWeapon);
//        }
//            addToInventory(newWeapon);
        //}
        addToInventory(newWeapon);
        weapon = newWeapon;
        hasWeapon = true;
        }
    private void addToInventory(Item item) {
        if (!inventory.containsKey(0)) {
            inventory.put(0, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), numberOfFireballs, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        } else if (!inventory.containsKey(1)) {
            inventory.put(1, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), numberOfFireballs, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        } else if (!inventory.containsKey(2)) {
            inventory.put(2, item);
            if (item instanceof Key)
                keyList.add((Key) item);
            statusBar.update(getHealth(), numberOfFireballs, inventory);
            //inventory.remove(0);
            //moveItemToOutOfView(item);
        }
        //----------------------
//        if (!inventoryFull()){ //old method for when inventory was an ArrayList
//            inventory.add(item);
//            moveItemToOutOfView(item);
//            if (item instanceof Weapon)
//                weapon = (Weapon) item;
//        }
//    private boolean inventoryFull(){ //old method for when inventory was an ArrayList
//        int i = 0;
//        for (Item item : inventory){
//            i++;
//        }
//        if (i == 3){
//            return true;
//        }
//        return false;
//    }
    }
//    private boolean hasWeapon() {
//        return weapon != null;
//    }
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
            super.setHealth(0);
            this.dies();
        }
        else
            super.setHealth(super.getHealth() - amount);
        if (!isDead)
            gui.setStatus("You are being attacked!");
    }

    public void dies(){
        gui.showMessage("Game Over","Game over! Restart game and try again.");
        gui.setStatus("Game over! Restart game and try again.");
        setDead(true);
    }
}
