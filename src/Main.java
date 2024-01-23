import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner teclado;
    public static final int TOTAL_VIDEOJUEGOS = 10;
    public static final int CODIGO_MAX_VIDEOJUEGO = 10;
    public static final Double CODIGOUNO = 19.99;
    public static final Double CODIGODOS = 24.99;
    public static final Double CODIGOTRES = 14.99;
    public static final Double CODIGOCUATRO = 9.99;
    public static final Double CODIGOCINCO = 4.99;
    public static final Double CODIGOSEIS = 29.99;
    public static final Double CODIGOSIETE = 39.99;
    public static final Double CODIGONUEVE = 34.99;
    public static final Double CODIGODIEZ = 49.99;
    public static String[][] videojuegos = new String[TOTAL_VIDEOJUEGOS][4];
    public static int[][] ventas = new int[CODIGO_MAX_VIDEOJUEGO][3];
    //public static final int[] stock = new int[TOTAL_VIDEOJUEGOS+1];

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        mostrarTodo();
    }

    public static int mostrarMenu() {

        catalogoVideoJuegos();
        int opcion;
        do {
            System.out.println("""
                    Bienvenido a la tienda de videojuegos. Elija una opción:
                                    
                    1. Consultar catálogo de videojuegos
                    2. Consultar stock de videojuegos
                    3. Mostrar catálogo ordenado por stock
                    4. Mostrar catálogo ordenado por precio
                    5. Registrar venta de videojuegos
                    6. Registrar devolución de videojuegos
                    7. Salir de la aplicación
                    """);
            opcion = obtenerOpcion();
        } while (opcion < 1 || opcion > 7);
        return opcion;
    }

    public static void seleccion(int opcion) {
        switch (opcion) {
            case 1 -> {
                mostrarCatalogo();
            }
            case 2 -> {
                mostrarStock(videojuegos);
            }
            case 3 -> {
                mostrarOrdenPorStock(mostrarStock(videojuegos));
                break;
            }
            case 4 -> {
                mostrarOrdenPorPrecio(videojuegos);
            }
            case 5 -> {
                registrarVentas(teclado, videojuegos);
            }
            case 7 -> {
                finPrograma();
            }
            default -> {
                System.out.println("Opcion incorrecta");
            }
        }
    }
    /*
    catalogo de videojuegos
     */

    public static void catalogoVideoJuegos() {
        String[][] datosVideojuegos = {
                {"1", "Super Mario Bros", String.valueOf(CODIGOUNO), String.valueOf(stockRango())},
                {"2", "The Legend of Zelda", String.valueOf(CODIGODOS), String.valueOf(stockRango())},
                {"3", "Sonic the Hedgehog", String.valueOf(CODIGOTRES), String.valueOf(stockRango())},
                {"4", "Tetris", String.valueOf(CODIGOCUATRO), String.valueOf(stockRango())},
                {"5", "Pac-Man", String.valueOf(CODIGOCINCO), String.valueOf(stockRango())},
                {"6", "Street Fighter II", String.valueOf(CODIGOSEIS), String.valueOf(stockRango())},
                {"7", "Doom", String.valueOf(CODIGOSIETE), String.valueOf(stockRango())},
                {"8", "Minecraft", String.valueOf(CODIGOUNO), String.valueOf(stockRango())},
                {"9", "The Sims", String.valueOf(CODIGONUEVE), String.valueOf(stockRango())},
                {"10", "Grand Theft Auto V", String.valueOf(CODIGODIEZ), String.valueOf(stockRango())}
        };

        for (int i = 0; i < TOTAL_VIDEOJUEGOS; i++) {
            videojuegos[i] = Arrays.copyOf(datosVideojuegos[i], datosVideojuegos[i].length);

        }

    }

    /*
    Catalogo nivel - 1
     */
    public static void mostrarCatalogo() {
        System.out.println("""
                Catálogo de videojuegos:
                +--------+---------------------+--------+
                | Código | Nombre              | Precio |
                +--------+---------------------+--------+""");
        for (String[] videoChueko : videojuegos) {
            System.out.printf("| \t  %2s | %19s |  %5s |", videoChueko[0], videoChueko[1], videoChueko[2]);
            System.out.println();
        }
        System.out.println("+--------+---------------------+-------+");

    }

    /*
    Stock disponible  nivel - 2
     */
    public static String[][] mostrarStock(String[][] videojuegos) {
        System.out.println("""
                Stock de videojuegos:
                +--------+---------------------+-------+
                | Código | Nombre              | Stock |
                +--------+---------------------+-------+""");
        for (String[] stockJuegos : videojuegos) {
            System.out.printf("| \t  %2s | %19s |\t%2s |", stockJuegos[0], stockJuegos[1], stockJuegos[3]);
            System.out.println();
        }
        System.out.println("+--------+---------------------+-------+");

        return videojuegos;
    }

    public static int stockRango() {
        return stockAleatorio(20, 0);
    }

    public static int stockAleatorio(int max, int min) {
        return (int) ((Math.random() * max - min + 1) + min);
    }

    /*
    Stock Ordenado de > <
     */
    public static String[][] mostrarOrdenPorStock(String[][] videojuegos) {
        System.out.println("""
                Stock de videojuegos:
                +--------+---------------------+-------+
                | Código | Nombre              | Stock |
                +--------+---------------------+-------+""");

        String[][] stockVideojuegos = videojuegos;
        for (int i = 0; i < stockVideojuegos.length - 1; i++) {
            for (int j = i + 1; j < stockVideojuegos.length; j++) {
                if (Integer.parseInt(stockVideojuegos[j][3]) < Integer.parseInt(stockVideojuegos[i][3])) {
                    String[] aux = stockVideojuegos[i];
                    stockVideojuegos[i] = stockVideojuegos[j];
                    stockVideojuegos[j] = aux;
                }
            }
        }
        for (String[] videojuego : videojuegos) {
            System.out.printf("| \t  %2s | %19s |  %5s |", videojuego[0], videojuego[1], videojuego[3]);
            System.out.println();
        }
        System.out.println("+--------+---------------------+-------+");

        return stockVideojuegos;
    }

    /*
    Precio ordenado por precio
     */
    public static String[][] mostrarOrdenPorPrecio(String[][] videojuegos) {
        System.out.println("""
                Stock de videojuegos:
                +--------+---------------------+--------+
                | Código | Nombre              | Precio |
                +--------+---------------------+--------+""");

        String[][] precioVideojuegos = videojuegos;
        for (int i = 0; i < precioVideojuegos.length - 1; i++) {
            for (int j = i + 1; j < precioVideojuegos.length; j++) {
                if (Double.parseDouble(String.valueOf(precioVideojuegos[j][2])) < Double.parseDouble(String.valueOf(precioVideojuegos[i][2]))) {
                    String[] aux = precioVideojuegos[i];
                    precioVideojuegos[i] = precioVideojuegos[j];
                    precioVideojuegos[j] = aux;
                }
            }
        }
        for (String[] videojuego : videojuegos) {
            System.out.printf("| \t  %2s | %19s |  %5s |", videojuego[0], videojuego[1], videojuego[2]);
            System.out.println();
        }
        System.out.println("+--------+---------------------+--------+");
        return precioVideojuegos;
    }

    /*
    Registrar las ventas
     */
    public static void registrarVentas(Scanner teclado, String[][] videojuegos) {
        boolean[] codigosValidos = new boolean[CODIGO_MAX_VIDEOJUEGO]; // Array para marcar códigos válidos

        int codigoVideojuego;
        boolean codigoValido = false;
        do {
            System.out.print("Introduzca el código del videojuego que desea comprar: ");
            codigoVideojuego = teclado.nextInt();

            for (String[] codigo : videojuegos) {
                if (codigoVideojuego == Integer.parseInt(codigo[0])) {
                    codigoValido = true;
                    codigosValidos[codigoVideojuego - 1] = true; // Marca el código como válido
                    break;
                }
            }

            if (!codigoValido) {
                System.out.println("Código incorrecto. Inténtalo de nuevo.");
                return;
            }

        } while (!codigoValido);
        System.out.print("Introduzca la cantidad de unidades que desea comprar: ");
        int cantidad = teclado.nextInt();

        int stockDisponible = Integer.parseInt(videojuegos[codigoVideojuego - 1][3]);
        if (cantidad > stockDisponible) {
            System.out.println("No hay stock suficiente.");
            realizarMasCompras();
        } else {
            int nuevoStock = stockDisponible - cantidad;
            videojuegos[codigoVideojuego -1][3] = String.valueOf(nuevoStock);
            int codigoVenta = codigoVentaDisponible();
            ventas[codigoVenta - 1][0] = codigoVenta;
            ventas[codigoVenta - 1][1] = codigoVideojuego;
            ventas[codigoVenta - 1][2] = cantidad;
            realizarMasCompras();
            cabezeraResumenVenta();
            for (int i = 0; i < codigosValidos.length; i++) {
                if (codigosValidos[i]) {
                    System.out.printf("| \t  %2s | %19s |\t   %2s |", videojuegos[i][0], videojuegos[i][1], videojuegos[i][3]);
                    System.out.println();
                }
            }
            System.out.println("+--------+---------------------+----------+");
        }
    }
    public static void realizarMasCompras() {
        System.out.print("¿Desea comprar algún videojuego más? (S/N): ");
        String siONo = teclado.next();
        if (siONo.equals("S") || siONo.equals("s")) {
            registrarVentas(teclado,videojuegos);
        } else if (siONo.equals("N") || siONo.equals("n")){
            System.out.println();
        }
    }
    public static void cabezeraResumenVenta() {
        System.out.println("""
                +--------+---------------------+----------+
                | Código | Nombre              | Unidades |
                +--------+---------------------+----------+""");
    }
    public static int codigoVentaDisponible() {
        for (int i = 0; i < CODIGO_MAX_VIDEOJUEGO; i++) {
            if (ventas[i][0] == 0) {
                return i + 1;
            }
        }
        return -1;
    }


    /*
    Registrar la devolucion de una venta
     */

    public static void registrarDevolucion() {

    }


    /*
    Salida por pantalla
     */
    public static void mostrarTodo() {
        int opcion;
        do {
            opcion = mostrarMenu();
            seleccion(opcion);
        } while (opcion != 7);
    }

    /*
    Fin de programa
     */
    public static void finPrograma() {
        System.out.println("Chao Chao Chao Chao!");

    }

    public static int obtenerOpcion() {
        System.out.println();
        do {
            System.out.print("Introduce opción: ");
            while (!teclado.hasNextInt()) {
                System.out.println("Pero tu eres tonto? aprende a poner números");
                teclado.next();
                System.out.print("Introduce opción: ");
            }
            int num = teclado.nextInt();
            if (num < 0 || num > 7) {
                System.out.println("Estas a NADA, para que te den una subvención por retraso, venga que tu puedes");
            } else {
                return num;
            }
        } while (true);
    }
}

