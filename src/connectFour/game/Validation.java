package connectFour.game;

import connectFour.Coordinates;
import connectFour.board.Board;

import java.util.Scanner;

public class Validation {
    private final static Scanner SCANNER = new Scanner(System.in);
    private static final int REQUIRED_LENGTH_FOR_INPUT = 2;

    public boolean isCoordinatesCorrect(Coordinates coordinates, Board board) {
        return checkGameLogic(coordinates, board) && isCellAvailableForMove(coordinates, board);
    }

    public String isInputValid() {
        String input;
        while(true){
            input = SCANNER.nextLine();
            if (input.isEmpty()) {
                System.out.println("Ввод не может быть пустым");
                continue;
            }
            String[] parts = input.split("");
            if (parts.length != REQUIRED_LENGTH_FOR_INPUT) {
                System.out.println("Введите 2 числа без пробелов");
                continue;
            }

            if (!isInteger(parts[0]) || !isInteger(parts[1])) {
                System.out.println("Это не число!");
                continue;
            }
            return input;
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkGameLogic(Coordinates coordinates, Board board) {
        int checkRow = coordinates.row();
        int checkColumn = coordinates.column();
        Coordinates checkCoordinates = new Coordinates(checkRow + 1, checkColumn);

        if (checkRow == board.getHeight() - 1) {
            return true;
        } else if (!board.isCellEmpty(checkCoordinates)) {
            return true;
        } else {
            System.out.println("Правила игры не позволяют занять ячейку по данным координатам!");
            System.out.println();
            return false;
        }
    }

    private boolean isCellAvailableForMove(Coordinates coordinates, Board board) {
        if (board.isCellEmpty(coordinates)) {
            return true;
        } else {
            System.out.println("На таких координатах уже располагается круг");
            return false;
        }
    }

}
