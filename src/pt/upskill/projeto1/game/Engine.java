package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Enemy;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.TileColors.Black;
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

        ArrayList<ImageTile> statusBar = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            statusBar.add(new Black(new Position(i, 0)));

        }
        gui.newStatusImages(statusBar);

        while (true){
            gui.update();
        }

    }

    public void notify(int keyPressed){
        hero.move(keyPressed, map);
        for (Enemy enemy : map.getCurrentRoom().getEnemyList()){
            enemy.move(map.getCurrentRoom(), hero);
        }
//        if (hero.isDead()){
//            init();
//        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
