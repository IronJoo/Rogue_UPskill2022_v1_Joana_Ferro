package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Room;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;

public class Engine {
    private Hero hero;
    private Map map;
    private Room room;
    private ArrayList<ImageTile> mapTiles;

    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

//        ArrayList<ImageTile> tiles = new ArrayList<>();
//        for(int x=0; x<10; x++){
//            for(int y=0; y<10; y++){
//                tiles.add(new Floor(new Position(x, y)));
//            }
//        }

        hero = new Hero();
        hero.setPosition(new Position(6,8));
        map = new Map();
        room = map.getRoom(hero);
        //room.generateNewRoom();
        mapTiles = room.getMapTiles();

        mapTiles.add(hero);
        //gui.setStatus();
        gui.setEngine(this);

        //gui.addStatusImage();
        gui.newImages(mapTiles);
        //gui.newStatusImages();
        gui.go();

        gui.setStatus("O jogo começou!");


        while (true){
            gui.update();
        }

    }

    public void notify(int keyPressed){
        hero.move(keyPressed, room);
        for (Enemy enemy : room.getEnemyList()){
            enemy.move(room, hero);
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
