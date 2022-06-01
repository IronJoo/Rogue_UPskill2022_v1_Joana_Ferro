package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.objects.Hero;

import java.util.HashMap;

public class Map {
    private HashMap<Integer, Room> roomList = new HashMap<Integer, Room>();

    public HashMap<Integer, Room> getRoomList() {
        return roomList;
    }
    public Room getRoom(Hero hero){
        if (roomList.containsKey(hero.getCurrentRoom())) { //if room exists in list
            return (roomList.get(hero.getCurrentRoom()));  //return it
        }
        else {
            Room room = new Room(hero.getCurrentRoom());                 //else generate it and add it to room list
            room.generateNewRoom();
            roomList.put(hero.getCurrentRoom(), room);
            return (room);
        }

    }
}
