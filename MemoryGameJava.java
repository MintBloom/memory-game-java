// base code for student Memory Card Game assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
//The cards are A B C D E F G H.
// A player can pick card’s by entering row and column. For example, ‘First pick (row col): 0 2‘.
import java.util.*;

public class MemoryGameJava {
    private static final int SIZE = 4; // 4x4 board
    private static final String HIDDEN = " * ";
    private static String[][] board = new String[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initBoard();
        printBoard();
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
        System.out.println("Memory Pair Game (4x4 : 0,1,2,3)\n");
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
            Scanner sc = new Scanner(System.in);
            System.out.print("Column number: ");
            int column1 = sc.nextInt();
            System.out.print("Row number: ");
            int row1 = sc.nextInt();
            checkUserInput(row1, column1); //sends user inputs to be validated, and if necessary, redone


            System.out.print("Column number: ");
            int column2 = sc.nextInt();
            System.out.print("Row number: ");
            int row2 = sc.nextInt();
            checkUserInput(row2, column2); //sends user inputs to be validated, and if necessary, redone

            checkUserPair(row1, column1, row2, column2);
        }
        System.out.println("Congratulations, You have found all the matching pairs :OOOOO, it took you {} attempts "); // win game statement
    }
    
    public static boolean checkUserInput(int row, int column){
        // if anything is wrong with the user's input it will be caught here and told to try another coordinate input
        Scanner sc = new Scanner(System.in);
        while (column < 0 || column > 3 || row < 0 || row > 3 || revealed[row][column] == true) { 
            /*should any of these conditions be true then the user input is FALSE and must be redone if ALL CONDITIONS ARE FALSE then the user input is TRUE and passses validation*/
            clearScreen();
            System.out.print("Incorrect input or the card you have chosen has aleady been revealed! Try Again.");
            printBoard();
            System.out.print("\nColumn number: ");
            column = sc.nextInt();
            System.out.print("Row number: ");
            row = sc.nextInt();
        }
        revealed[row][column] = true;
        return true;
    }

    public static void checkUserPair(int row1, int column1, int row2, int column2) {
        //this function will check whether the user has guessed correctly and matched a pair or not
        if (board[row1][column1] == board[row2][column2]) {
            
        }
    }

    public static void attemptCounter() {
        // keeps track of attempts needed to complete a game
        int attempts = 0;
    }

    public static void timer() {
        // keeps track of time taken to complete a game
    }
}