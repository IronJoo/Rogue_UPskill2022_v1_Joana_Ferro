package pt.upskill.projeto1.objects.RoomElements;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.Random;

public class Floor implements ImageTile {

    private Position position;
    private int randomFloor = new Random().nextInt(50);

    public Floor(Position position) {
        this.position = position;

    }

    @Override
    public String getName() {
        switch (randomFloor){
            case 0:
            case 1:
            case 2:
            case 3:
                return "Grass"; // == 8% chance
            case 4:
                return "Trap";  // == 2% chance
            default:
                return "Floor"; // == 90% chance
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
