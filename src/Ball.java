import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends GameObject{

    private double vy = 2;
    private double radius;
    private static final Color DEFAUT_COLOR = Color.BLUE;


    public Ball(double x, double y, double radius,  double vy) {
        super(new Circle(x,y,radius),x,y,DEFAUT_COLOR);

        this.radius = radius;

        this.vy = vy;
    }

    public Circle getShape() {
        return (Circle) shape;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
        getShape().setCenterX(x);
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
        getShape().setCenterY(y);
    }

    public double getRadius() {
        return radius;
    }


    // Méthode pour déplacer la balle
    public void move() {
        setY( getY() + vy);
    }



    public void respawnIn(Scene scene) {
        Random r = new Random();
        int x = r.nextInt(0, (int)scene.getWidth());
        setX(x);
        setY(0);
    }
}
