package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Enemy;
import pt.upskill.projeto1.objects.RoomElements.Door;
import pt.upskill.projeto1.objects.Entities.Hero;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private HashMap<Integer, Room> roomList = new HashMap<Integer, Room>();
    private ImageMatrixGUI gui;
    private Room currentRoom;
    private Hero hero = new Hero();

    public Map(ImageMatrixGUI gui) {
        this.gui = gui;
    }

    public HashMap<Integer, Room> getRoomList() {
        return roomList;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom(int roomNumber){ //if room exists in list, return it. Else generate new room and add it to room list
        if (roomList.containsKey(roomNumber)) {
            return (roomList.get(roomNumber));
        }
        else {
            Room newRoom = new Room(roomNumber);
            newRoom.readTextRoom();
            roomList.put(roomNumber, newRoom);
            return (newRoom);
        }

    }
    public void runRoomEngine(int roomNumber){ //initiates all visual elements

        hero.setPosition(new Position(6,8));
        hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
        gui.clearImages();
        currentRoom = getRoom(roomNumber);
        ArrayList<ImageTile> mapTiles;
        mapTiles = currentRoom.getMapTiles();
        ItemObserver itemObserver = new ItemObserver(gui);
        hero.setPosition(new Position(4, 3));
        mapTiles.add(hero);
        //gui.setStatus();
        //gui.addStatusImage();
        gui.newImages(mapTiles);
    }
    public void changeRoom(Door previousDoor, int nextRoomNumber, Hero hero){ //set visual elements to next room

        int nextDoor = previousDoor.getLeadsToDoor();
        runRoomEngine(nextRoomNumber);
        hero.setPosition(currentRoom.getDoorList().get(nextDoor).getPosition());
    }
    public void update(int keyPressed){ //updates game interactions and functionalities after each key press
        if (!hero.isDead()) {
            hero.update(keyPressed, this);
            for (Enemy enemy : getCurrentRoom().getEnemyList()) {
                enemy.update(getCurrentRoom(), hero);
            }
            if (hero.isDead()) //prevents statusBar from updating after hero has died
                return;
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
        }
    }
}
