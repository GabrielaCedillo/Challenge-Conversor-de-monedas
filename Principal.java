import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaApi consulta = new ConsultaApi();

        div();
        System.out.println("Bienvenido a tu conversor de Monedas");
        div();

        byte opc = 0;
        do {
            menu();
            if (lectura.hasNextByte()) {
                opc = lectura.nextByte();
                if (opc >= 1 && opc <= 6) {
                    conversion(opc, consulta, lectura);
                } else if (opc == 7) {
                    System.out.println("Saliendo del programa...");
                } else {
                    System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número entre 1 y 7.");
                lectura.next(); // Limpiar la entrada no válida
            }
        } while (opc != 7);
    }

    public static void div() {
        System.out.println("********************************************");
    }

    public static void menu() {
        System.out.println("\n1) Dólar =>> Peso mexicano");
        System.out.println("2) Peso mexicano =>> Dólar");
        System.out.println("3) Dólar ==> Peso dominicano");
        System.out.println("4) Peso dominicano ==> Dólar");
        System.out.println("5) Dólar ==> Real brasileño");
        System.out.println("6) Real brasileño ==> Dólar");
        System.out.println("7) Salir");
        System.out.println("Elije la opción que deseas: ");
        div();
    }

    public static void conversion(byte opc, ConsultaApi consulta, Scanner lectura) {
        System.out.print("Ingrese la cantidad que desea convertir: ");
        if (lectura.hasNextDouble()) {
            double cantidad = lectura.nextDouble();

            String moneda = "";
            String convertir = "";

            switch (opc) {
                case 1:
                    moneda = "USD";
                    convertir = "MXN";
                    break;
                case 2:
                    moneda = "MXN";
                    convertir = "USD";
                    break;
                case 3:
                    moneda = "USD";
                    convertir = "DOP";
                    break;
                case 4:
                    moneda = "DOP";
                    convertir = "USD";
                    break;
                case 5:
                    moneda = "USD";
                    convertir = "BRL";
                    break;
                case 6:
                    moneda = "BRL";
                    convertir = "USD";
                    break;
            }

            try {
                Monedas monedas6 = consulta.buscarMoneda(moneda, convertir, cantidad);
                System.out.println("El valor " + cantidad + " " + monedas6.toString());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando aplicación");
            }
        } else {
            System.out.println("Entrada no válida. Por favor ingrese un número válido.");
            lectura.next(); // Limpiar la entrada no válida
        }
    }
}
