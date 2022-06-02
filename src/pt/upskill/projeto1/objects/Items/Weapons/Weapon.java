package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.objects.Items.Item;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Weapon extends Item {
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
