package it.conway;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException {
        printTitle();
        printAbout();
        printInstructions();

        char command = 0;
        while (command != 's') {

            command = readCommand();
//            System.out.println("Command: " + command); //for debugging
            //Transform to lower case
            command = Character.toLowerCase(command);

            switch (command) {
                case 's':
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_UNDERLINED + "               Game start               " + '\u2800');
                    System.out.println("\n\nPress Ctrl+C to stop at any point");
                    System.out.println(ConsoleColors.RESET + "\n");
                    Thread.sleep(5000);
                    break;
                case 'i':
                printGameInfo();
                    break;
                case 'q':
                    Thread.sleep(500);
                    System.out.println(ConsoleColors.BLUE + "\u2726 " + "Good bye " + "\u2726");
                    Thread.sleep(1000);
                    return;
                default:
                    System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid command");
                    break;
            }
        }

        int nrow = 35; //30
        int ncol = 75; //50
        List<List<Boolean>> matrix = Utils.initializeMatrix(nrow, ncol);

        int ngenerations = 1000;
        for (int gen = 1; gen <= ngenerations; gen++) {
            Thread.sleep(700);
            //to clear console. It works only in console supporting ansi escape sequences.
            //see https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
//            System.out.print("\033[H\033[2J");
//            System.out.flush();

            //workaround to clear console
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();

            Utils.printMatrix(matrix, gen, ngenerations);
            matrix = GameLogic.nextGeneration(matrix);
        }
        System.out.println("*********************************");
        Utils.printMatrix(matrix, ngenerations, ngenerations);
    }

    private static void printTitle() {
        System.out.println(ConsoleColors.BLUE_BOLD +
                "██╗░░░░░██╗███████╗███████╗\n" +
                "██║░░░░░██║██╔════╝██╔════╝\n" +
                "██║░░░░░██║█████╗░░█████╗░░\n" +
                "██║░░░░░██║██╔══╝░░██╔══╝░░\n" +
                "███████╗██║██║░░░░░███████╗\n" +
                "╚══════╝╚═╝╚═╝░░░░░╚══════╝\n\n"
                + ConsoleColors.RESET);
    }

    private static void printInstructions() {
        System.out.println(ConsoleColors.GREEN_BOLD +
                "Welcome to the Conway's Game of Life!\n" +
                "Commands: \n" +
                "- s start the game\n" +
                "- i get some info about Conway's Game of Life\n" +
                "- q quit\n"
                + ConsoleColors.RESET);
    }

    private static void printAbout() {
        System.out.println("Developed in Italy " + ConsoleColors.GREEN + "▊" +
                ConsoleColors.WHITE + "▊" +
                ConsoleColors.RED + "▊" +
                ConsoleColors.RESET +
                " by " + ConsoleColors.PURPLE_UNDERLINED + "alessio176\n" + ConsoleColors.RESET);
    }

    private static char readCommand() {
        System.out.print(ConsoleColors.RED + "> ");
        char command = scanner.nextLine().charAt(0);
        System.out.println(ConsoleColors.RESET);
        return command;
    }

    private static void printGameInfo() {
        System.out.println(ConsoleColors.PURPLE_BRIGHT +
                "Conway's Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970.\n" +
                "The game is a zero-player game, meaning that its evolution is determined by its initial state, without involving human intervention or any external input.\n" +
                "The game is played on a grid of square cells, each of which is in one of two possible states, alive or dead.\n" +
                "The rules of the game are as follows:\n" +
                "• A live cell with less than 2 live neighbors dies (lonely!).\n" +
                "• A live cell with 2 or 3 live neighbors survives. (just right!).\n" +
                "• A live cell with more than 3 live neighbors dies (overcrowded!).\n" +
                "• An empty cell with exactly 3 live neighbors becomes alive. (birth!)\n" +
                "With these simple rules, surprisingly complex patterns and behaviors emerge.\n"+
                "In this implementation the initial configuration is generated in a pseudo-random manner:\n"+
                "This means that each run will potentially lead to different behaviors :).\n"+
                "For more info about Conway's game of life, refer to Wikipedia page:\nhttps://en.wikipedia.org/wiki/Conway's_Game_of_Life\n\n"+
                "Have fun and enjoy ✨");
        System.out.println(ConsoleColors.RESET);
    }
}
