package connectFour.game;

import connectFour.circle.Circle;
import connectFour.circle.Color;
import connectFour.Coordinates;
import connectFour.board.Board;

public class DetermineGameState {
    private final static int NUMBER_CIRCLES_FOR_WIN = 4;

    public GameState checkGameState(Board board) {
        if(checkVerticalCells(board) != GameState.PLAYING){
            return checkVerticalCells(board);
        }
        else if(checkHorizontalCells(board) != GameState.PLAYING){
            return checkHorizontalCells(board);
        }
        else if(checkDiagonalLeftToRightCells(board) != GameState.PLAYING){
            return checkDiagonalLeftToRightCells(board);
        }
        else if(checkDiagonalRightToLeftCells(board) != GameState.PLAYING){
            return checkDiagonalRightToLeftCells(board);
        }
        return GameState.PLAYING;
    }

    private GameState checkVerticalCells(Board board) {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                if(checkWin(row,column, 1, 0, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkHorizontalCells(Board board) {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 0, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalLeftToRightCells(Board board) {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth() - 3; column++) {
                if(checkWin(row,column, 1, 1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private GameState checkDiagonalRightToLeftCells(Board board) {
        for (int row = 0; row < board.getHeight() - 3; row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                if(checkWin(row,column, 1, -1, board)){
                    return board.getCircle(new Coordinates(row,column)).getColor() == Color.RED? GameState.RED_WINS : GameState.YELLOW_WINS;
                }
            }
        }
        return GameState.PLAYING;
    }

    private boolean checkWin(int startRow, int startColumn, int deltaRow, int deltaColumn, Board board) {
        Coordinates firstCoordinates = new Coordinates(startRow, startColumn);
        if (!board.isCellEmpty(firstCoordinates)) {
            Circle firstCircle = board.getCircle(firstCoordinates);

            for (int i = 1; i < NUMBER_CIRCLES_FOR_WIN; i++) {
                int newRow = startRow + i * deltaRow;
                int newColumn = startColumn + i * deltaColumn;
                Coordinates nextCoordinates = new Coordinates(newRow, newColumn);

                if (board.isCellEmpty(nextCoordinates)) {
                    return false;
                }

                Circle nextCircle = board.getCircle(nextCoordinates);
                if (!nextCircle.getColor().equals(firstCircle.getColor())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
