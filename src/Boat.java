import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Boat extends GameObject {

    private static final Color DEFAUT_COLOR = Color.PURPLE;

    private double width, height;

    public Boat(double x, double y, double width, double height) {
        super(new Rectangle(x, y, width, height),x,y, DEFAUT_COLOR);
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getShape() {
        return (Rectangle) super.getShape();
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    @Override
    public void setX(double x) {
        this.x = x;
        getShape().setX(x);
    }

    @Override
    public void setY(double y) {
        this.x = x;
        getShape().setX(x);
    }

    protected boolean collideWith(Ball ball){
        return  ball.getY() + ball.getRadius() >= this.getY()
                && ball.getY() + ball.getRadius() <= this.getY() + this.getHeight()
                && ball.getX() >= this.getX()
                && ball.getX() <= this.getX() + this.getWidth();

    }
}
