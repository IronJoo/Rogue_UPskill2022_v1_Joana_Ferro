package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Sword extends Weapon implements ImageTile {

    public Sword(Position position) {
        super(position);
        super.setDamage(10);
    }

    @Override
    public String getName() {
        return "Sword";
    }
}
