package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Enemy;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.StatusBar.StatusBar;
import pt.upskill.projeto1.rogue.utils.Map;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;

public class Engine {
    private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private Map map = new Map(gui);

    public void init(){
        gui.setEngine(this);
        int startingRoom = 0;
        map.runRoomEngine(startingRoom);
        gui.go();
        gui.setStatus("The game has started!");
        while (true){
            gui.update();
        }

    }

    public void notify(int keyPressed){
        map.update(keyPressed);
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }
}
