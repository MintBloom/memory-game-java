// base code for student Memory Card Game assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
//The cards are A B C D E F G H.
// A player can pick card’s by entering row and column. For example, for the first pick 'row: 2 column: 0'
import java.util.*;

public class MemoryGameJava {
    private static final int SIZE = 4; // 4x4 board
    private static final String HIDDEN = " * ";
    private static String[][] board = new String[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];
    private static int attemptCounter = 0;
    private static int row1 = 0;
    private static int column1 = 0;
    private static int row2 = 0;
    private static int column2 = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Memory Pair Game (4x4 : 0,1,2,3)\n");
        initBoard();
        userInput();
    }
    public static void clearScreen() {   
        // this function clears the terminal
        System.out.print("\033[H\033[2J"); // function found on stack https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-javaoverflow 
        System.out.flush();
    }
    // ===== Game setup =====
    private static void initBoard() {
        // assigns values to each position on the board
        List<String> cards = new ArrayList<>();
        for (char c = 'A'; c < 'A' + (SIZE * SIZE) / 2; c++) {
            cards.add(" " + c + " ");
            cards.add(" " + c + " ");
        }
        Collections.shuffle(cards);
        Iterator<String> it = cards.iterator();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = it.next(); // value for each board position
                revealed[r][c] = false; // determines whether a board position is revealed to the user or not
            }
        }
    }
    private static void printBoard() {
        // this function creates the board with its row and col. numbers in the terminal 
        // header
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.printf(" %d ", c);
        System.out.println();
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.print("---");
        System.out.println();

        for (int r = 0; r < SIZE; r++) {
            System.out.printf(" %d |", r);
            for (int c = 0; c < SIZE; c++) {
                System.out.print(revealed[r][c] ? board[r][c] : HIDDEN);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void userInput() {
        // this function will take the users choice of which cards to reveal
        int matchesFound = 0; // number of card matches found by the user 
        int totalMatchesFound = 8; // obtained via. (SIZE*SIZE/2)
        
        while (matchesFound < totalMatchesFound) {
            printBoard();
            Scanner sc = new Scanner(System.in);
            System.out.print("Column number1: ");
            MemoryGameJava.column1 = sc.nextInt();
            System.out.print("Row number1: ");
            MemoryGameJava.row1 = sc.nextInt();
            checkInput1();//sends user inputs to be validated, and if necessary, redone
            
            printBoard();
            System.out.print("Column number2: ");
            MemoryGameJava.column2 = sc.nextInt();
            System.out.print("Row number2: ");
            MemoryGameJava.row2 = sc.nextInt();
            checkInput2(); //sends user inputs to be validated, and if necessary, redone

            if (board[MemoryGameJava.row1][MemoryGameJava.column1].equals(board[MemoryGameJava.row2][MemoryGameJava.column2])) {
                printBoard();
                matchesFound++;
                System.out.println("Match found! Keep going!");
            }
            else {
                printBoard();
                System.out.println("Not a match unfortunately, keep going!");
                revealed[MemoryGameJava.row1][MemoryGameJava.column1] = false;
                revealed[MemoryGameJava.row2][MemoryGameJava.column2] = false;
            }
            MemoryGameJava.attemptCounter++;
        }
        System.out.println(String.format("Congratulations, You have found all the matching pairs :OOOOO, it took you %d attempts", MemoryGameJava.attemptCounter)); // win game statement
        
    }
    
    public static void  checkInput2(){
        // corrects user choice for column2 and row2 
        Scanner sc = new Scanner(System.in);
        while (column2 < 0 || column2 > 3 || row2 < 0 || row2 > 3 || revealed[row2][column2] == true) { 
            //should any of these conditions be true then the user input is FALSE and must be redone
            //if ALL CONDITIONS ARE FALSE then the user input is TRUE and passes
            clearScreen();
            System.out.println("Incorrect input or the card you have chosen has aleady been revealed! Try Again.");
            printBoard();
            System.out.print("\nColumn number: ");
            column2 = sc.nextInt();
            System.out.print("Row number: ");
            row2 = sc.nextInt();
        }
        revealed[row2][column2] = true;
    }

    public static void  checkInput1(){
        // corrects user choice for column1 and row1 
        Scanner sc = new Scanner(System.in);
        while (column1 < 0 || column1 > 3 || row1 < 0 || row1 > 3 || revealed[row1][column1] == true) { 
            //should any of these conditions be true then the user input is FALSE and must be redone
            //if ALL CONDITIONS ARE FALSE then the user input is TRUE and passes
            clearScreen();
            System.out.println("Incorrect input or the card you have chosen has aleady been revealed! Try Again.");
            printBoard();
            System.out.print("\nColumn number: ");
            column1 = sc.nextInt();
            System.out.print("Row number: ");
            row1 = sc.nextInt();
        }
        revealed[row1][column1] = true;
    }
}