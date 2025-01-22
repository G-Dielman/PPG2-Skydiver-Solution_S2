import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class GameObject {
    protected Shape shape;
    protected double x,y;
    Color color;

    public GameObject(Shape shape, double x, double y, Color color) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        setColor(color);
    }


    protected void setColor(Color color) {
        this.color = color;
        this.shape.setStroke(Color.GREY);
        this.shape.setFill(this.color);
    }

    public double getX() { return x; }
    public abstract void setX(double x) ;

    public abstract void setY(double y) ;
    public double getY() { return y; }

    public Shape getShape() {
        return shape;
    }
}
