package pt.upskill.projeto1.objects.TileColors;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Green extends Colors implements ImageTile {

    public Green(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Green";
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
