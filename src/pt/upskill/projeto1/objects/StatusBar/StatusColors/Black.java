package pt.upskill.projeto1.objects.StatusBar.StatusColors;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Black extends Colors implements ImageTile {

    public Black(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Black";
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
