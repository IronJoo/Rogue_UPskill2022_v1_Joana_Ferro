package pt.upskill.projeto1.objects.StatusBar.StatusColors;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Colors implements ImageTile {
    private Position position;

    public Colors(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
