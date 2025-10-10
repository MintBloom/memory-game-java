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
    }

    // ===== Game setup =====
    private static void initBoard() {
        List<String> cards = new ArrayList<>();
        for (char c = 'A'; c < 'A' + (SIZE * SIZE) / 2; c++) {
            cards.add(" " + c + " ");
            cards.add(" " + c + " ");
        }
        Collections.shuffle(cards);
        Iterator<String> it = cards.iterator();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = it.next();
                revealed[r][c] = false;
            }
        }
    }

    private static void printBoard() {
        System.out.println("   Memory (4x4) — find the pairs\n");
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
}