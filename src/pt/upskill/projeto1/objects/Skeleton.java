package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Skeleton extends Enemy implements ImageTile {

    public Skeleton(Position position, int health, int damage) {
        super(position, health, damage);
    }

    public Skeleton(int damage) {
        super(damage);
    }

    public Skeleton(Position position, int damage) {
        super(position, damage);
    }
    public Skeleton(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Skeleton";
    }

}
