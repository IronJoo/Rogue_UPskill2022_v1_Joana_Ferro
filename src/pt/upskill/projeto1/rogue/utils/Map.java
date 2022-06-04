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
    private ImageMatrixGUI gameEngine;
    private Room currentRoom;
    private Hero hero = new Hero();

    public Map(ImageMatrixGUI gameEngine) {
        this.gameEngine = gameEngine;
    }

    public HashMap<Integer, Room> getRoomList() {
        return roomList;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom(int roomNumber){
        if (roomList.containsKey(roomNumber)) { //if room exists in list
            return (roomList.get(roomNumber));  //return it
        }
        else {
            Room newRoom = new Room(roomNumber); //else generate new room and add it to room list
            newRoom.readTextRoom();
            roomList.put(roomNumber, newRoom);
            return (newRoom);
        }

    }
    public void runRoomEngine(int roomNumber){ //creates room graphics

        //Hero hero = new Hero();
        hero.setPosition(new Position(6,8));
        hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
        gameEngine.clearImages();
        currentRoom = getRoom(roomNumber);
        ArrayList<ImageTile> mapTiles;
        mapTiles = currentRoom.getMapTiles();
        ItemObserver itemObserver = new ItemObserver(gameEngine);
        hero.setPosition(new Position(4, 3));
        mapTiles.add(hero);
        //gui.setStatus();
        //gui.addStatusImage();
        gameEngine.newImages(mapTiles);
    }
    public void changeRoom(Door previousDoor, int nextRoomNumber, Hero hero){

        int nextDoor = previousDoor.getLeadsToDoor();
        runRoomEngine(nextRoomNumber);
        hero.setPosition(currentRoom.getDoorList().get(nextDoor).getPosition());
    }
    public void update(int keyPressed){
        hero.init(keyPressed, this);
        for (Enemy enemy : getCurrentRoom().getEnemyList()){
            enemy.move(getCurrentRoom(), hero);
        }
    }
}
