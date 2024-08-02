package entity;

import java.util.Objects;

public class Snack {
    private static Integer contadorSnacks = 0;
    private Integer idSnack = 0;
    private String nombre;
    private Double precio = 0.0;


    public Snack() {
        this.idSnack = ++Snack.contadorSnacks;
    }

    public Snack(String nombre, Double precio) {
        this();
        this.nombre = nombre;
        this.precio = precio;
    }

    public static Integer getContadorSnacks() {
        return contadorSnacks;
    }

    public Integer getIdSnack() {
        return idSnack;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Snack: " +
                "Id Snack: " + "(" + idSnack + ")" +
                " Nombre: " + nombre + " -" +
                " Precio: $" + precio;
    }

    public String escribirSnack() {
        return idSnack + "," + nombre + "," + precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return Objects.equals(idSnack, snack.idSnack) && Objects.equals(nombre, snack.nombre) && Objects.equals(precio, snack.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
