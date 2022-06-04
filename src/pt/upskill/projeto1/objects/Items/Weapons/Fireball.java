package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Fireball implements FireTile {
    private Position position;
    int damage = 10;
    String name;

    public Fireball(Position position) {
        this.position = position;
        this.name = "Fire";
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean validateImpact() {
        return false;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}
