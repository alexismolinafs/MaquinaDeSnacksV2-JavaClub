package service;

import entity.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceSnacksArchivos implements ISnacksServ {

    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Crear lista de snacks
    private List<Snack> snacks = new ArrayList<Snack>();

    //Constructor de clase
    public ServiceSnacksArchivos() {
        //Creación del archivo por si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if (existe) {
                this.snacks = obtenerSnacks();
            } else {
                //Creamos archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close(); //Guardamos el archivo en disco
                System.out.println("Se ha creado el archivo");
            }
        } catch (Exception e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
        //Si no existe el archivo, cargamos algunos snacks iniciales
        if (!existe) {
            cargarSnacksIniciales();
        }
    }

    private List<Snack> obtenerSnacks() {
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea : lineas) {
                String[] lineaSnack = linea.split(",");//Parse separado por una coma
                var idSnack = lineaSnack[0]; //No se usa
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack);//Se agrega el snack
            }
        } catch (Exception e) {
            System.out.println("Error al leer archivo de snacks: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    private void cargarSnacksIniciales() {
        this.agregarSnack(new Snack("Papas Adobadas", 15.50));
        this.agregarSnack(new Snack("Coca - Cola 500ml", 14.50));
        this.agregarSnack(new Snack("Yogurt Fresa", 12.50));
        this.agregarSnack(new Snack("Pepsi 600ml", 13.50));

    }

    @Override
    public void agregarSnack(Snack snack) {
        //Agregamos el nuevo snack,
        //1- A la lista de memoria
        this.snacks.add(snack);
        //2- Guardamos el nuevo snack
        this.agregarSnackArchivo(snack);
    }

    private void agregarSnackArchivo(Snack snack) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();
        } catch (Exception e) {
            System.out.println("Error al agregar snack: " + e.getMessage());
        }
    }

    @Override
    public void mostrarSnacks() {
        System.out.println(" _-_ Snacks en el Inventario _-_");
        //Mostramos la lista de snacks
        var inventarioSnacks = "";
        for (var snack : this.snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
