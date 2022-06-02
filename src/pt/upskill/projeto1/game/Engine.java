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
    //private Room room;
    private ArrayList<ImageTile> mapTiles;

    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        hero = new Hero();
        hero.setPosition(new Position(6,8));
        map = new Map(gui);
        gui.setEngine(this);
        int startingRoom = 0;
        map.runRoomEngine(startingRoom, hero);

        //gui.newStatusImages();
        gui.go();

        gui.setStatus("O jogo começou!");


        while (true){
            gui.update();
        }

    }

    public void notify(int keyPressed){
        hero.move(keyPressed, map);
        for (Enemy enemy : map.getCurrentRoom().getEnemyList()){
            enemy.move(map.getCurrentRoom(), hero);
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
