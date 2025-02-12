package connectFour.board;

import connectFour.circle.Circle;
import connectFour.Coordinates;

public class BoardConsoleRenderer {

    public void render(Board board) {

        StringBuilder header = new StringBuilder("   ");
        for (int column = 0; column < board.getWidth(); column++) {
            header.append(column).append("    ");
        }
        System.out.println(header);

        for (int row = 0; row < board.getHeight(); row++) {
            StringBuilder line = new StringBuilder();
            line.append(row);
            for (int column = 0; column < board.getWidth(); column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (board.isCellEmpty(coordinates)) {
                    line.append(renderEmptyCell());
                } else {
                    line.append(getCircleSquare(board.getCircle(coordinates)));
                }
            }
            System.out.println(line);
        }
    }

    private String renderEmptyCell() {
        return ("  .  ");
    }

    private String selectPictureForCircle(Circle circle) {
        switch (circle.getClass().getSimpleName()) {
            case "RedCircle":
                return "\uD83D\uDD34";
            case "YellowCircle":
                return "\uD83D\uDFE1";
            default:
                throw new IllegalArgumentException("Unexpected circle type: " + circle.getClass().getSimpleName());
        }
    }

    private String getCircleSquare(Circle circle) {
        return (" " + selectPictureForCircle(circle) + "  ");
    }

}

