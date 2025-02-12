package connectFour.circle;

abstract public class Circle {
    private final Color color;

    public Circle(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
