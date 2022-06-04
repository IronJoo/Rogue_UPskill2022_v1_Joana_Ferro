package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Key extends Item implements ImageTile {
    private String keyName;

    public Key(Position position) {
        super(position);
    }
    public Key(String keyName){
        //this(new Position(-1, -1)); //Position is mandatory, therefore setting position to out of view until new reset
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
    @Override
    public String getName() {
        return "Key";
    }
}
