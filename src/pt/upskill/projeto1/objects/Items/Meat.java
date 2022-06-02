package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Meat extends Item implements ImageTile {
    private int healthValue = 10;
    public Meat(){}

    public Meat(Position position) {
        super(position);
    }

    public int getHealthValue() {
        return healthValue;
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }
}
