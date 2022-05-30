package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Door implements ImageTile {
    private Position position;
    private String number;
    private String type;
    private String leadsToRoom;
    private String leadsToDoor;
    private String requiredKey;
    private boolean isOpen = false;

    public Door(Position position) {
        this.position = position;
    }
    public Door(String number, String type, String leadsToRoom, String leadsToDoor){
        this.number = number;
        this.type = type;
        this.leadsToDoor = leadsToDoor;
        this.leadsToRoom = leadsToRoom;
    }
    public Door(String number, String type, String leadsToRoom, String leadsToDoor, String requiredKey){
        this(number, type, leadsToRoom, leadsToDoor);
        this.requiredKey = requiredKey;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String getName() {
        if (type.equals("E")){
            return "DoorWay";
        } else {
            if (isOpen) {
                return "DoorOpen";
            }
            return "DoorClosed";
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
