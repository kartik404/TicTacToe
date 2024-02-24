package LLD.TicTacToe;

import LLD.TicTacToe.model.Board;
import LLD.TicTacToe.model.PieceType;
import LLD.TicTacToe.model.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> players;
    private Board gameBoard;

    TicTacToeGame() {
        init();
    }

    private void init() {
        players = new LinkedList<>();

        Player player1 = new Player("Player1", PieceType.X);
        Player player2 = new Player("Player2", PieceType.O);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean endGame = false;
        while (!endGame) {
            // Get the player whose turn is
            Player currentPlayer = players.removeFirst();

            gameBoard.printBoard();
            // Get free spaces if no then it's a tie
            if (gameBoard.getFilledPlaces() == gameBoard.getSize()*gameBoard.getSize()) {
                endGame = true;
                continue;
            }

            // Get player input
            System.out.print("Player " + currentPlayer.getName() + "'s turn, Enter row,column: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] values = input.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            // Add piece on board
            boolean success = gameBoard.addPiece(inputRow, inputColumn, currentPlayer.getPieceType());
            if (!success) {
                System.out.println("Incorrect position chosen, try again...");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);

            boolean isWinner = checkWinner(inputRow, inputColumn, currentPlayer.getPieceType());
            if (isWinner) {
                return currentPlayer.getName();
            }
        }

        return "Tie";
    }

    private boolean checkWinner(int row, int column, PieceType pieceType) {
        boolean isMatch = true;
        PieceType[][] board = gameBoard.getBoard();

        // Check row
        for (int i = 0; i < gameBoard.getSize(); i++) {
            if (board[row][i] == null || board[row][i] != pieceType) {
                isMatch = false;
                break;
            }
        }

        if (isMatch) {
            return true;
        } else {
            isMatch = true;
        }

        // Check column
        for (int i = 0; i < gameBoard.getSize(); i++) {
            if (board[i][column] == null || board[i][column] != pieceType) {
                isMatch = false;
                break;
            }
        }

        if (isMatch) {
            return true;
        } else {
            isMatch = true;
        }

        // Check diagonal
        for (int i=0, j=0; i<gameBoard.getSize(); i++, j++) {
            if (board[i][j] == null || board[i][j] != pieceType) {
                isMatch = false;
                break;
            }
        }

        if (isMatch) {
            return true;
        } else {
            isMatch = true;
        }

        // Check anti-diagonal
        for (int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize(); i++, j--) {
            if (board[i][j] == null || board[i][j] != pieceType) {
                isMatch = false;
                break;
            }
        }

        return isMatch;
    }
}
