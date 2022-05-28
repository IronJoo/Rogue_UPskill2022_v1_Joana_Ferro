package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private ArrayList<ImageTile> mapTiles = new ArrayList<ImageTile>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private String currentRoom = "./rooms/room0.txt";

    public Map(ArrayList<ImageTile> tiles) {
        this.mapTiles = tiles;
    }

    public Map() {
    }

    public ArrayList<ImageTile> getMapTiles() {
        return mapTiles;
    }

    public void generateMap(Hero hero) {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                mapTiles.add(new Floor(new Position(x, y)));
            }
        }
        try {
            Scanner roomFile = new Scanner(new File(currentRoom));
            int y = 0;
            String line = "";
            while (roomFile.hasNextLine()) {
                line = roomFile.nextLine();
                if (line.charAt(0) == '#') {
                    continue;
                }
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    if (character == 'W') {
                        mapTiles.add(new Wall(new Position(x, y)));
                    } else if (character == 'H') {
                        hero.setPosition(new Position(x, y));
                    } //else if (Character.isDigit(character)){
//                        createDoor(character);
//                    }
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//    public void createDoor(char character){
//        if (character == '0') {
//
//        }
//
//    }

    public boolean isWall(Position position) {
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile instanceof Wall)
                return true;
        }
        return false;
    }
    public boolean isEnemy(Position position) {
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile instanceof Enemy)
                return true;
        }
        return false;
    }
}

