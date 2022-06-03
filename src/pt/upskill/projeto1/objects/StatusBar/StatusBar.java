package pt.upskill.projeto1.objects.StatusBar;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Black;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Green;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Red;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.RedGreen;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;

public class StatusBar {
    private ArrayList<ImageTile> statusList = new ArrayList<>();
    public StatusBar() {



        //update();
    }

    public ArrayList<ImageTile> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<ImageTile> statusList) {
        this.statusList = statusList;
    }
    public void getFireBalls(Hero hero){

    }
    public void getHealth(int health){
        //int healthDifference = 100 - health;
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
        }
        if (health < 26){
            statusList.add(new RedGreen(new Position(3, 0)));
        }
        if (health < 14){
            statusList.add(new Red(new Position(3, 0)));
        }
    }
    public void getInventory(){

    }
    public void update(int health /*, int numberOfFireballs, ArrayList inventory*/){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        statusList.clear();
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0))); //set everything black
        }
        getHealth(health);
//        getFireBalls(fireballList);
//        getInventory(inventory);
        gui.newStatusImages(statusList);

    }
}
