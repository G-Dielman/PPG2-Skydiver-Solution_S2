import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private Circle shape;
    private double x, y;
    private double vx, vy;
    private double radius;

    private static final Color DEFAUT_COLOR = Color.BLUE;

    public Ball(double x, double y, double radius, double vx, double vy){
        this(x,y,radius,vx,vy,DEFAUT_COLOR);
    }
    public Ball(double x, double y, double radius, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;

        this.radius = radius;

        this.vx = vx;
        this.vy = vy;

        this.shape = new Circle(x,y,radius);
        this.shape.setFill(color);
    }

    public Circle getShape() {
        return shape;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
        this.shape.setCenterX(x);
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
        this.shape.setCenterY(y);
    }

    public double getRadius() {
        return radius;
    }

    public double getVx() { return vx; }
    public void setVx(double vx) { this.vx = vx; }
    public void switchVx(){

        this.vx = -this.vx;
    }
    public void switchVy(){
        this.vy = -this.vy;
    }

    public double getVy() { return vy; }
    public void setVy(double vy) { this.vy = vy; }

    // Méthode pour déplacer la balle
    public void move() {
        setX( getX() + vx);
        setY( getY() + vy);
    }






    public void onCollisionWith(Brick brick) {


    }
}
