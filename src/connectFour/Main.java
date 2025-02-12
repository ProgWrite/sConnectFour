package connectFour;

import connectFour.board.Board;
import connectFour.game.Game;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(7, 7);
        Game game = new Game(board);
        game.startMenu();
    }
}
