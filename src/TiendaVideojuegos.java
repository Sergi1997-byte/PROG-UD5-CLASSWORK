import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TiendaVideojuegos {

    private static final int MAX_VIDEOJUEGOS = 10;
    private static final int MAX_VENTAS = 1000;
    private static final int MAX_STOCK = 20;

    private static String[][] videojuegos = new String[MAX_VIDEOJUEGOS][3]; // Codi, Nom, Preu
    private static int[][] ventas = new int[MAX_VENTAS][3]; // Codi de venta, Codi de videojoc, Unitats venudes
    private static int[] stock = new int[MAX_VIDEOJUEGOS];

    static {
        inicialitzarVideojocs();
        inicialitzarEstoc();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcio;

        do {
            mostrarMenu();
            opcio = demanarOpcio(scanner);

            switch (opcio) {
                case 1 -> mostrarCatlegVideojocs();
                case 2 -> mostrarEstoc();
                case 3 -> mostrarCatlegOrdenatPerEstoc();
                case 4 -> mostrarCatlegOrdenatPerPreu();
                case 5 -> realitzarVenda(scanner);
                case 6 -> retornarVenda(scanner);
                case 7 -> System.out.println("Adéu!");
                default -> System.out.println("Opció incorrecta. Torna a intentar-ho.");
            }

        } while (opcio != 7);

        scanner.close();
    }

    private static void inicialitzarVideojocs() {
        String[][] dadesVideojocs = {
                {"1", "Super Mario Bros", "19.99"},
                {"2", "The Legend of Zelda", "24.99"},
                {"3", "Sonic the Hedgehog", "14.99"},
                {"4", "Tetris", "9.99"},
                {"5", "Pac-Man", "4.99"},
                {"6", "Street Fighter II", "29.99"},
                {"7", "Doom", "39.99"},
                {"8", "Minecraft", "19.99"},
                {"9", "The Sims", "34.99"},
                {"10", "Grand Theft Auto V", "49.99"}
        };

        for (int i = 0; i < MAX_VIDEOJUEGOS; i++) {
            videojuegos[i] = Arrays.copyOf(dadesVideojocs[i], dadesVideojocs[i].length);
        }
    }

    private static void inicialitzarEstoc() {
        Random random = new Random();
        for (int i = 0; i < MAX_VIDEOJUEGOS; i++) {
            stock[i] = random.nextInt(MAX_STOCK + 1);
        }
    }
    /*
            for (String[] videojuego : videojuegos) {
            for (String s : videojuego) {
                System.out.printf("|\t" + s);
            }
            System.out.println("|");
        }
     */

    private static void mostrarMenu() {
        System.out.println("\n** Menú **");
        System.out.println("1. Consultar el catàleg complet de videojocs");
        System.out.println("2. Consultar l'estoc de cada videojoc");
        System.out.println("3. Mostrar el catàleg de videojocs ordenat per estoc");
        System.out.println("4. Mostrar el catàleg de videojocs ordenat per preu");
        System.out.println("5. Registrar les vendes d'un o diversos videojocs");
        System.out.println("6. Registrar la devolució d'una venda");
        System.out.println("7. Eixir de l'aplicació");
        System.out.print("Tria una opció: ");
    }

    private static int demanarOpcio(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Opció incorrecta. Introdueix un número.");
            System.out.print("Tria una opció: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void mostrarCatlegVideojocs() {
        System.out.println("\nCatàleg de Videojocs:");
        System.out.println("Codi\tNom\tPreu (€)");
        for (String[] videojoc : videojuegos) {
            System.out.println(videojoc[0] + "\t" + videojoc[1] + "\t" + videojoc[2]);
        }
    }

    private static void mostrarEstoc() {
        System.out.println("\nEstoc de Videojocs:");
        System.out.println("Codi\tNom\tEstoc");
        for (int i = 0; i < MAX_VIDEOJUEGOS; i++) {
            System.out.println(videojuegos[i][0] + "\t" + videojuegos[i][1] + "\t" + stock[i]);
        }
    }

    private static void mostrarCatlegOrdenatPerEstoc() {
        // Ordena el catàleg per estoc i crida mostrarCatlegVideojocs()
        ordenarCatlegPerEstoc();
        mostrarCatlegVideojocs();
    }

    private static void mostrarCatlegOrdenatPerPreu() {
        // Ordena el catàleg per preu i crida mostrarCatlegVideojocs()
        ordenarCatlegPerPreu();
        mostrarCatlegVideojocs();
    }

    private static void ordenarCatlegPerEstoc() {
        for (int i = 0; i < MAX_VIDEOJUEGOS - 1; i++) {
            for (int j = 0; j < MAX_VIDEOJUEGOS - i - 1; j++) {
                if (stock[j] > stock[j + 1]) {
                    // Intercambia les files del catàleg
                    String[] temp = videojuegos[j];
                    videojuegos[j] = videojuegos[j + 1];
                    videojuegos[j + 1] = temp;

                    // Intercambia l'estoc
                    int tempStock = stock[j];
                    stock[j] = stock[j + 1];
                    stock[j + 1] = tempStock;
                }
            }
        }
    }

    private static void ordenarCatlegPerPreu() {
        for (int i = 0; i < MAX_VIDEOJUEGOS - 1; i++) {
            for (int j = 0; j < MAX_VIDEOJUEGOS - i - 1; j++) {
                if (Double.parseDouble(videojuegos[j][2]) < Double.parseDouble(videojuegos[j + 1][2])) {
                    // Intercambia les files del catàleg
                    String[] temp = videojuegos[j];
                    videojuegos[j] = videojuegos[j + 1];
                    videojuegos[j + 1] = temp;

                    // Intercambia l'estoc
                    int tempStock = stock[j];
                    stock[j] = stock[j + 1];
                    stock[j + 1] = tempStock;
                }
            }
        }
    }

    private static void realitzarVenda(Scanner scanner) {
        mostrarCatlegVideojocs();
        System.out.print("Introdueix el codi del videojoc que vols vendre: ");
        int codiVideojoc = scanner.nextInt();

        if (codiVideojoc < 1 || codiVideojoc > MAX_VIDEOJUEGOS) {
            System.out.println("Codi de videojoc incorrecte. Torna a intentar-ho.");
            return;
        }

        int estocActual = stock[codiVideojoc - 1];
        if (estocActual == 0) {
            System.out.println("Aquest videojoc està esgotat. No es pot vendre.");
            return;
        }

        System.out.print("Introdueix la quantitat de unitats a comprar: ");
        int quantitat = scanner.nextInt();

        if (quantitat > estocActual) {
            System.out.println("No hi ha prou estoc per a la quantitat desitjada.");
            return;
        }

        // Registra la venda
        int codiVenda = obtenirCodiVendaDisponible();
        ventas[codiVenda - 1][0] = codiVenda;
        ventas[codiVenda - 1][1] = codiVideojoc;
        ventas[codiVenda - 1][2] = quantitat;

        // Actualitza l'estoc
        stock[codiVideojoc - 1] -= quantitat;

        // Mostrem l'import total a cobrar al client
        double importTotal = quantitat * Double.parseDouble(videojuegos[codiVideojoc - 1][2]);
        System.out.println("Import total a cobrar: " + importTotal + "€");
    }

    private static int obtenirCodiVendaDisponible() {
        for (int i = 0; i < MAX_VENTAS; i++) {
            if (ventas[i][0] == 0) {
                return i + 1;
            }
        }
        return -1; // No hi ha codis de venda disponibles (hauria de gestionar-se millor en una aplicació real)
    }

    private static void retornarVenda(Scanner scanner) {
        System.out.print("Introdueix el codi de la venda a retornar: ");
        int codiVenda = scanner.nextInt();

        if (codiVenda < 1 || codiVenda > MAX_VENTAS || ventas[codiVenda - 1][0] == 0) {
            System.out.println("Codi de venda incorrecte o no existeix. Torna a intentar-ho.");
            return;
        }

        // Mostrar l'import total a retornar al client
        int codiVideojoc = ventas[codiVenda - 1][1];
        int quantitatVendida = ventas[codiVenda - 1][2];
        double importTotal = quantitatVendida * Double.parseDouble(videojuegos[codiVideojoc - 1][2]);
        System.out.println("Import total a retornar: " + importTotal + "€");

        // Actualitza l'estoc
        stock[codiVideojoc - 1] += quantitatVendida;

        // Elimina la venda del llistat de vendes
        ventas[codiVenda - 1] = new int[3];
    }
}

