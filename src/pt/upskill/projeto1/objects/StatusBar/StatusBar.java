package pt.upskill.projeto1.objects.StatusBar;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.Black;
import pt.upskill.projeto1.objects.StatusBar.StatusColors.RedGreen;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;

public class StatusBar {
    private ArrayList<ImageTile> statusList = new ArrayList<>();
    public StatusBar() {
        for (int i = 0; i < 10; i++){
            statusList.add(new Black(new Position(i, 0)));
        }
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
    public void getHealth(){
        for (int i = 3; i <= 6; i++){
            statusList.add(new RedGreen(new Position(i, 0)));
        }
    }
    public void getInventory(){

    }
    public void update(int health, ArrayList fireballList, ArrayList inventory){
//        getHealth(health);
//        getFireBalls(fireballList);
//        getInventory(inventory);
    }
}
