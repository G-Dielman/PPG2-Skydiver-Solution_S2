import javafx.scene.paint.Color;

public class SolidBrick  extends Brick{
    private int hits =0;

    private static Color DEFAUT_COLOR = Color.DARKGREEN;

    public SolidBrick(double x, double y, double with, double height) {
        super(x, y, with, height,  DEFAUT_COLOR);
    }

    @Override
    public void onCollisionWith(Ball ball) {
        super.onCollisionWith(ball);
        if(this.hits == 0){
            ++hits;
            this.setColor(Color.GREEN);
        } else if (this.hits == 1) {
            ++hits;
            this.setColor(Color.GREENYELLOW);
        }
        else {
            breakThis();
        }

    }
}
