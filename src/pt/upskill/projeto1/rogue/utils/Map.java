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
    private ArrayList<Key> keyList = new ArrayList<Key>();
    private String currentRoom = "./rooms/room0.txt";

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
                            keyList.add(new Key(tokens[2]));
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
                        case 'S':
                            Skeleton skeleton = new Skeleton(new Position(x, y));
                            mapTiles.add(skeleton);
                            enemyList.add(skeleton);
                            break;
                        case 'G':
                            BadGuy badguy = new BadGuy(new Position(x, y));
                            mapTiles.add(badguy);
                            enemyList.add(badguy);
                            break;
                        case 'T':
                            Thief thief = new Thief(new Position(x, y));
                            mapTiles.add(thief);
                            enemyList.add(thief);
                            break;
                        case 'm':
                            mapTiles.add(new Meat(new Position(x, y)));
                            break;
                        case 'k':
                            Key key = keyList.get(0);
                            key.setPosition(new Position(x, y));
                            mapTiles.add(key);
                            break;
                        case 's':
                            mapTiles.add(new Sword(new Position(x, y)));
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
    public boolean findsCollision(Position position){
        if (isWall(position) || isEnemy(position) || isClosedDoor(position) || isHero(position))
            return true;
        return false;
    }

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
    public boolean isHero(Position position) {
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile instanceof Hero)
                return true;
        }
        return false;
    }
    public boolean isClosedDoor(Position position) {
        int i = 0;
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile.getName().equals("DoorClosed"))
                return true;
        }
        return false;
    }
    public boolean isOpenDoor(Position position) {
        int i = 0;
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && (tile.getName().equals("DoorWay") || tile.getName().equals("DoorOpen")))
                return true;
        }
        return false;
    }
    public boolean isItem(Position position) {
        int i = 0;
        for (ImageTile tile : mapTiles) {
            if (tile.getPosition().equals(position) && tile instanceof Item)
                return true;
        }
        return false;
    }
}

