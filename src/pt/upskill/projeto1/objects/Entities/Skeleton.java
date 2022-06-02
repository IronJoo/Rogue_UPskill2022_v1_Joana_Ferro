package pt.upskill.projeto1.objects.Entities;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Skeleton extends Enemy implements ImageTile {

    public Skeleton(Position position, int health, int damage) {
        super(position, health, 5);
    }

    public Skeleton(int damage) {
        super(5);
    }

    public Skeleton(Position position, int damage) {
        super(position, 5);
    }
    public Skeleton(Position position) {
        super(position);
        super.setDamage(5);
    }

    @Override
    public String getName() {
        return "Skeleton";
    }

}
