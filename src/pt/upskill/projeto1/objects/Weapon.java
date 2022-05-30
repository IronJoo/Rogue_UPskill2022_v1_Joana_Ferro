package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Weapon extends Item{
    private int damage;
    public Weapon() {
    }

    public Weapon(Position position) {
        super(position);
    }
    public Weapon(int damage) {
        this.damage = damage;
    }
}
