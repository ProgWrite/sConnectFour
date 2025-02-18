package connectFour.board;

import connectFour.circle.Circle;
import connectFour.Coordinates;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Coordinates, Circle> circles = new HashMap<>();
    private final int height;
    private final int width;


    public Board(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCircle(Coordinates coordinates, Circle circle) {
        circles.put(coordinates, circle);
    }

    public Circle getCircle(Coordinates coordinates) {
        if(!isCoordinatesValid(coordinates)) {
            throw new IllegalArgumentException("Coordinates is out of bounds");
        }
        Circle circle = circles.get(coordinates);
        if(circle == null) {
            throw new IllegalArgumentException("There is no circle with coordinates " + coordinates);
        }
        return circles.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !circles.containsKey(coordinates);
    }

    private boolean isCoordinatesValid(Coordinates coordinates) {
        return coordinates.row() >= 0 && coordinates.row() < height && coordinates.column() >= 0 && coordinates.column() < width;
    }

}

