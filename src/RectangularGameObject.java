
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class RectangularGameObject extends GameObject implements Collidable{

    protected double width, height;

    public RectangularGameObject(double x, double y, double width, double height, Color color) {
        super(new Rectangle(x,y,width,height), x, y, color);
        this.width = width;
        this.height = height;
    }


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void setX(double x) {
        this.x = x;
        this.getShape().setX(x);
    }
    @Override
    public void setY(double y) {
        this.y = y;
        this.getShape().setY(y);
    }

    @Override
    public Rectangle getShape() {
        return (Rectangle)shape;
    }

    public void onCollisionWith(Ball ball){
        if( collidedLeftBy(ball) || collidedRightBy(ball) )
            ball.switchVx();

        if( collidedToptBy(ball) || collidedBotomBy(ball) )
            ball.switchVy();
    }

    public boolean collideWith(Ball ball){
        return  collidedToptBy(ball)
                ||collidedBotomBy(ball)
                ||collidedLeftBy(ball)
                ||collidedRightBy(ball);
    }


    protected boolean collidedLeftBy(Ball ball){
        return  ball.getX() + ball.getRadius() >= this.getX()
                && ball.getX() + ball.getRadius() <= this.getX()+this.getWidth()
                && ball.getY() >= this.getY()
                && ball.getY() <= this.getY() + this.getHeight();
    }

    protected boolean collidedRightBy(Ball ball){
        return  ball.getX() - ball.getRadius() >= this.getX()
                && ball.getX() - ball.getRadius() <= this.getX()+this.getWidth()
                && ball.getY() >= this.getY()
                && ball.getY() <= this.getY() + this.getHeight();
    }

    protected boolean collidedToptBy(Ball ball){
        return  ball.getY() + ball.getRadius() >= this.getY()
                && ball.getY() + ball.getRadius() <= this.getY() + this.getHeight()
                && ball.getX() >= this.getX()
                && ball.getX() <= this.getX() + this.getWidth();

    }
    protected boolean collidedBotomBy(Ball ball){
        return  ball.getY() - ball.getRadius() >= this.getY()
                && ball.getY() - ball.getRadius() <= this.getY() + this.getHeight()
                && ball.getX() >= this.getX()
                && ball.getX() <= this.getX() + this.getWidth();
    }
}
