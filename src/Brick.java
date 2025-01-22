import javafx.scene.paint.Color;

public abstract class Brick extends RectangularGameObject {

    protected boolean broken;

    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        this.broken = false;
    }

    public boolean isBroken() { return broken; }

    public void breakThis(){
        this.broken = true;
        this.shape.setVisible(false);
    }


}
