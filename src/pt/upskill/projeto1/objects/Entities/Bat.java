package pt.upskill.projeto1.objects.Entities;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Bat extends Enemy implements ImageTile {

    public Bat() {
        super( 1);
    }

    public Bat(Position position) {
        super(position, 1);
    }

    @Override
    public String getName() {
        return "Bat";
    }
}
