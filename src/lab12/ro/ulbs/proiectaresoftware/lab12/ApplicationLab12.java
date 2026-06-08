package lab12.ro.ulbs.proiectaresoftware.lab12;

import java.util.Arrays;

public class ApplicationLab12 {

    public static void main(String[] arg) {
        CookingTasks t1 = new CookingTasks( Arrays.asList("Add pasta to water","boil", "add salt", "make sauce", "add sauce", "fill platter", "DONE Pasta") );
        CookingTasks t2 = new CookingTasks(Arrays.asList("Grab salad", "grab cucumbers", "grab eggs", "boil eggs", "add eggs", "add salt", "add sauce", "DONE Salad"));
        CookingTasks t3 = new CookingTasks(Arrays.asList("Grab flour","grab eggs","grab sugar","grab cinamon","cook all","add cream","add icecream","DONE Dessert"));
        CookingTasks t4 = new CookingTasks(Arrays.asList("Grab rice and boil","DONE Rice"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Restaurant used water: "+Restaurant.getRestaurant().getUsedWater());
    }
}