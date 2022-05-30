package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import sun.tracing.ProbeSkeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private ArrayList<ImageTile> mapTiles = new ArrayList<ImageTile>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<Door> doorList = new ArrayList<Door>();
    private String currentRoom = "./rooms/room2.txt";

    public Map(ArrayList<ImageTile> tiles) {
        this.mapTiles = tiles;
    }

    public Map() {
    }

    public ArrayList<ImageTile> getMapTiles() {
        return mapTiles;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
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
                    String[] tokens = line.split(" ");
                    switch (tokens.length) {
                        case 6:
                            doorList.add(new Door(tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]));
                            break;
                        case 5:
                            doorList.add(new Door(tokens[1],tokens[2],tokens[3],tokens[4]));
                            break;
                        case 3:
                            break;
                    }
                    continue;
                }
                for (int x = 0; x < line.length(); x++) {
                    char character = line.charAt(x);
                    switch (character) {
                        case 'W':
                            mapTiles.add(new Wall(new Position(x, y)));
                            break;
                        case 'H':
                            hero.setPosition(new Position(x, y));
                            break;
                        case 'S':
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            mapTiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case 'G':
                            mapTiles.add(new BadGuy(new Position(x, y)));
                            break;
                        case 'm':
                            mapTiles.add(new Meat(new Position(x, y)));
                            break;
                        default:
                            if (Character.isDigit(character)){
                                int i = character-'0';
                                Door door = doorList.get(i);
                                door.setPosition(new Position(x,y));
                                mapTiles.add(door);
                            }

                    }
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

