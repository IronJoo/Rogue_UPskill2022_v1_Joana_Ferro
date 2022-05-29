package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;

public class Engine {
    private Hero hero;
    private Map map;
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
        hero.setPosition(new Position(1,1));
        map = new Map();

        map.generateMap(hero);
        mapTiles = map.getMapTiles();

        mapTiles.add(hero);

        gui.setEngine(this);

        gui.newImages(mapTiles);
        gui.go();

        gui.setStatus("O jogo começou!");


        while (true){
            gui.update();
        }

    }

    public void notify(int keyPressed){
        hero.move(keyPressed, map);
        for (Enemy enemy : map.getEnemyList()){
            enemy.move(map);
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
