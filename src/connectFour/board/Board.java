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
        return circles.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !circles.containsKey(coordinates);
    }

}

