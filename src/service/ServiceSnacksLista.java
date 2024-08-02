package service;

import entity.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServiceSnacksLista implements ISnacksServ {
    private static final List<Snack> snacks;


    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Soda", 15.50));
        snacks.add(new Snack("Pepsi", 12.00));
        snacks.add(new Snack("Mango", 20.00));
        snacks.add(new Snack("Burger", 30.00));
        snacks.add(new Snack("Cheese", 40.00));
    }

    public void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    public void mostrarSnacks() {
        var inventarioSnack = "";
        for (Snack snack : snacks) {
            inventarioSnack += snack.toString() + "\n";
        }
        System.out.println("Inventario de Snacks: \n" + inventarioSnack);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}
