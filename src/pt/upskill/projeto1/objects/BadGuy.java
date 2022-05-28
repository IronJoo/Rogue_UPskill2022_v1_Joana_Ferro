package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemy implements ImageTile {

    public BadGuy() {
        super(10);
    }

    public BadGuy(Position position) {
        super(position,10);
    }

    @Override
    public String getName() {
        return "BadGuy";
    }
}
