package pt.upskill.projeto1.objects.TileColors;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class RedGreen extends Colors implements ImageTile {

    public RedGreen(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "RedGreen";
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
