package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Entities.Hero;
import pt.upskill.projeto1.objects.Items.Item;

import java.util.HashMap;

public class ItemObserver {
    ImageMatrixGUI gui;

    public ItemObserver(ImageMatrixGUI gui) {
        this.gui = gui;
    }

    public void PickUp(Item item, Hero hero){
        if (!hero.getInventory().containsKey(0)){
            hero.getInventory().put(0, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(0);
            //moveItemToOutOfView(item);
        }
        else if (!hero.getInventory().containsKey(1)){
            hero.getInventory().put(1, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(1);
            //moveItemToOutOfView(item);
        } else if (!hero.getInventory().containsKey(2)){
            hero.getInventory().put(2, item);
            hero.getStatusBar().update(hero.getHealth(), hero.getNumberOfFireballs(), hero.getInventory());
            hero.getInventory().remove(2);
            //moveItemToOutOfView(item);
        }
    }
}
