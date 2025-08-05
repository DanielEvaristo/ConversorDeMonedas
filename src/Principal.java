
import modelos.Moneda;
import modelos.ResponseApi;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("---------------------------");
            System.out.println("Bienvenido al conversor de monedas");
            System.out.println("Seleccione una opción:");
            System.out.println("1.- Dolar -> Peso Argentino");
            System.out.println("2.- Peso Argentino -> Dolar");
            System.out.println("3.- Dolar -> Peso Mexicano");
            System.out.println("4.- Peso Mexicano -> Dolar");
            System.out.println("5.-Dolar -> Peso Colombiano");
            System.out.println("6.- Peso Colombiano -> Dolar");
            System.out.println("0.- Salir");
            opcion = scanner.nextInt();
            ResponseApi responseApi = new ResponseApi();

            String from = "";
            String to = "";

          switch (opcion) {
                case 1 -> { from = "USD"; to = "ARS"; }
                case 2 -> { from = "ARS"; to = "USD"; }
                case 3 -> { from = "USD"; to = "MXN"; }
                case 4 -> { from = "MXN"; to = "USD"; }
                case 5 -> { from = "USD"; to = "COP"; }
                case 6 -> { from = "COP"; to = "USD"; }
                case 0 -> {
                    flag = false;
                    System.out.println("Gracias por usar el conversor.");
                }
                default -> {
                    System.out.println("Opción inválida.");
                }
          }
          System.out.println("Ingrese la cantidad a convertir: ");
          double cantidad = scanner.nextDouble();

            try {
                Moneda moneda = responseApi.respuestaApi(from);
                Double tasa = moneda.conversion_rates().get(to);

                if (tasa == null) {
                    System.out.println("No se encontró la tasa de cambio para " + from + " -> " + to);
                } else {
                    double resultado = cantidad * tasa;
                    System.out.printf("%.2f %s = %.2f %s%n", cantidad, from, resultado, to);
                }

            } catch (Exception e) {
                System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            }
        }

    }
}
