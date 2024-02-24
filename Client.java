package LLD.TicTacToe;

public class Client {

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        String result = game.startGame();
        if ("Tie".equals(result)) {
            System.out.println("The game is a tie.");
        } else {
            System.out.println("The winner is: " + result);
        }
    }
}
