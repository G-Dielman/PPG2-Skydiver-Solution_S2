import javafx.scene.paint.Color;

public class Paddle extends RectangularGameObject {

    private static Color DEFAUT_COLOR = Color.PURPLE;
    public Paddle(double x, double y, double width, double height) {
        super(x, y, width, height, DEFAUT_COLOR);

    }
}
