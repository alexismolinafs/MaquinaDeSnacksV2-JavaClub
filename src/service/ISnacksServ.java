package service;

import entity.Snack;

import java.util.List;

public interface ISnacksServ {

    void agregarSnack(Snack snack);

    void mostrarSnacks();

    List<Snack> getSnacks();
}
