package presentation;

import entity.Snack;
import service.ISnacksServ;
import service.ServiceSnacksArchivos;
import service.ServiceSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaDeSnacks {
    public static void main(String[] args) {
        maquinaDeSnacks();
    }

    public static void maquinaDeSnacks() {
        var salir = false;
        var consola = new Scanner(System.in);
        //Se crea el objeto para obtener el servicio de snacks(Lista)
        //ISnacksServ service = new ServiceSnacksLista();
        ISnacksServ service = new ServiceSnacksArchivos();

        List<Snack> productos = new ArrayList<>();
        System.out.println(" *** Maquina de Snacks ***");
        service.mostrarSnacks();
        while (!salir) {
            try {
                var option = mostrarMenu(consola);
                salir = ejecutarOpciones(option, consola, productos, service);

            } catch (Exception e) {
                System.out.println("Ocurrió un error; " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                Menu: 
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar Nuevo snacks
                4. Mostrar Inventario
                5. Salir
                                
                Elige una opción:
                """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int option, Scanner consola, List<Snack> productos, ISnacksServ service) {
        var salir = false;
        switch (option) {
            case 1 -> comprarSnack(consola, productos, service);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, service);
            case 4 -> listarInventario(consola, service);
            case 5 -> {
                System.out.println("¡Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opción invalida" + option);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, ISnacksServ service) {
        System.out.println("¿Que snacks deseas comprar (Id)?");
        var idSnack = Integer.parseInt(consola.nextLine());
        var snackEncontrado = false;
        for (var snack : service.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                productos.add(snack);
                System.out.println("Ok, Snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado) {
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = " *** Ticket de Venta *** ";
        var total = 0.0;
        for (var producto : productos) {
            ticket += "\n\t-" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola, ISnacksServ service) {
        System.out.print("Nombre del Snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del Snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        service.agregarSnack(new Snack(nombre, precio));
        System.out.println("El snack se ha agregado correctamente");
        service.mostrarSnacks();
    }

    private static void listarInventario(Scanner consola, ISnacksServ service) {
        service.mostrarSnacks();
    }
}
