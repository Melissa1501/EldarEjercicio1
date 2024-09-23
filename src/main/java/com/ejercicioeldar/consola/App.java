package com.ejercicioeldar.consola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.ejercicioeldar.consola.model.Persona;
import com.ejercicioeldar.consola.model.Tarjeta;
import com.ejercicioeldar.consola.repository.PersonaRepository;
import com.ejercicioeldar.consola.repository.TarjetaRepository;
import com.ejercicioeldar.consola.repository.PersonaRepositoryArrayListImpl;
import com.ejercicioeldar.consola.repository.TarjetaRepositoryArrayListImpl;
import com.ejercicioeldar.consola.service.PersonaService;
import com.ejercicioeldar.consola.service.TarjetaService;

/**
 * Hello world!
 *
 */
public class App 
{
    private static PersonaRepository personaRepository = new PersonaRepositoryArrayListImpl();
    private static PersonaService personaService = new PersonaService(personaRepository);

    private static TarjetaRepository tarjetaRepository = new TarjetaRepositoryArrayListImpl();
    private static TarjetaService tarjetaService = new TarjetaService(tarjetaRepository, personaRepository);

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main( String[] args )
    {
        while (true) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    registrarTarjeta();
                    break;
                case 3:
                    mostrarTarjetasPorDni();
                    break;
                case 4:
                    consultarTasasPorFecha();
                    break;
                case 5:
                    salir();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("1. Registrar Persona");
        System.out.println("2. Registrar Tarjeta");
        System.out.println("3. Mostrar Tarjetas de un Usuario");
        System.out.println("4. Consultar Tasas por Fecha");
        System.out.println("5. Salir");
    }

    private static void registrarPersona() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (dd-MM-yyyy): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Email: ");
        String email = scanner.nextLine();

        personaService.registrarPersona(new Persona(nombre, apellido, dni, fechaNacimiento, email));
        System.out.println("Persona registrada exitosamente.");
    }

    private static void registrarTarjeta() {
        System.out.print("DNI del titular: ");
        String dniTitular = scanner.nextLine();
        System.out.print("Marca de la tarjeta (VISA, NARA, AMEX): ");
        String marca = scanner.nextLine();
        System.out.print("Número de la tarjeta: ");
        String numero = scanner.nextLine();
        System.out.print("Fecha de vencimiento (dd-MM-yyyy): ");
        LocalDate fechaVencimiento = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("CVV: ");
        String cvv = scanner.nextLine();

        try {
            tarjetaService.registrarTarjeta(dniTitular, new Tarjeta(numero, fechaVencimiento, cvv, marca));
            System.out.println("Tarjeta registrada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar la tarjeta: " + e.getMessage());
        }
    }

    private static void mostrarTarjetasPorDni() {
        System.out.print("DNI del usuario: ");
        String dniConsulta = scanner.nextLine();

        try {
            tarjetaService.buscarTarjetasPorDni(dniConsulta).forEach(System.out::println); 
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void consultarTasasPorFecha() {
        System.out.print("Fecha (dd-MM-yyyy) [Presiona Enter para usar fecha actual]: ");
        String fechaInput = scanner.nextLine();
        LocalDate fecha = fechaInput.isEmpty() ? LocalDate.now() : LocalDate.parse(fechaInput, formatter);

        // Llamar al servicio para obtener las tasas
        List<String> tasas = tarjetaService.consultarTasasPorFecha(fecha);

        // Mostrar las tasas en consola
        tasas.forEach(System.out::println);
    }

    private static void salir() {
        System.out.println("Saliendo...");
        scanner.close();
    }
}
