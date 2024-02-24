package LLD.TicTacToe.model;

public class Board {
    private int size;
    private int filledPlaces;
    private PieceType[][] board;

    public Board(int size) {
        this.size = size;
        filledPlaces = 0;
        board = new PieceType[size][size];
    }

    public int getSize() {
        return size;
    }

    public int getFilledPlaces() {
        return filledPlaces;
    }

    public PieceType[][] getBoard() {
        return board;
    }

    public boolean addPiece(int row, int col, PieceType piece) {
        if (row<0 || row>=size || col<0 || col>=size || board[row][col] != null) {
            return false;
        }

        board[row][col] = piece;
        filledPlaces++;
        return true;
    }

    public void printBoard() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (board[i][j] != null) {
                    System.out.print(" " + board[i][j].name() + " ");
                } else {
                    System.out.print("   ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
