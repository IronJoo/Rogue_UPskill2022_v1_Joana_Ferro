package pt.upskill.projeto1.objects.StatusBar;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.Items.Item;
import pt.upskill.projeto1.objects.Items.Weapons.Fireball;
import pt.upskill.projeto1.objects.Items.Weapons.Weapon;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Black;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Green;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Red;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.RedGreen;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusBar {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private ArrayList<ImageTile> statusList = new ArrayList<>();
    public StatusBar() {}
    public ArrayList<ImageTile> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<ImageTile> statusList) {
        this.statusList = statusList;
    }
    public void getFireBalls(int numberOfFireballs){
//        for (int i = 0; i < numberOfFireballs; i++){
//            FireTile fireball = new Fireball(new Position(i, 0));
//            statusList.add(fireball);
//        }
    }
    public void getHealth(int health){
        for (int i = 3; i <= 6; i++){
            statusList.add(new Green(new Position(i, 0))); //set life bar to green
        }
        if (health < 100){
            statusList.add(new RedGreen(new Position(6, 0)));
        }
        if (health < 89){
            statusList.add(new Red(new Position(6, 0)));
        }
        if (health < 76){
            statusList.add(new RedGreen(new Position(5, 0)));
        }
        if (health < 64){
            statusList.add(new Red(new Position(5, 0)));
        }
        if (health < 51){
            statusList.add(new RedGreen(new Position(4, 0)));
        }
        if (health < 39){
            statusList.add(new Red(new Position(4, 0)));
            gui.setStatus("You're almost dead! Get some food.");
        }
        if (health < 26){
            statusList.add(new RedGreen(new Position(3, 0)));
            gui.setStatus("You're almost dead! Get some food.");
        }
        if (health < 14){
            statusList.add(new Red(new Position(3, 0)));
            gui.setStatus("You're almost dead! Get some food.");
        }
    }
    public void getInventory(HashMap<Integer, Item> inventory){
        int key = 0;
        for (int i = 7; i < 10; i++){
            if (inventory.containsKey(key)){
                Position position = new Position(i, 0);
                Item copyOfItem = inventory.get(key);
                //inventory.remove(key);
                copyOfItem.setPosition(position);
                statusList.add((ImageTile) copyOfItem);
            }
            key++;
        }
    }
    public void update(int health, int numberOfFireballs, HashMap<Integer, Item> inventory){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0))); //set everything black
        }
        getHealth(health);
        getFireBalls(numberOfFireballs);
        getInventory(inventory);
        gui.newStatusImages(statusList);

    }
}
