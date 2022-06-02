package pt.upskill.projeto1.objects.Items.Weapons;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Hammer extends Weapon implements ImageTile {

    public Hammer(Position position) {
        super(position);
        super.setDamage(30);
    }
    @Override
    public String getName() {
        return "Hammer";
    }
}
