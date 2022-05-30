package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Meat extends Item implements ImageTile {
    private int health;

    public Meat(Position position) {
        super(position);
    }


    @Override
    public String getName() {
        return "GoodMeat";
    }
}
