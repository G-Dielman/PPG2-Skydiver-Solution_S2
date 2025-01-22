import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Random;

public class BreakoutGame extends Application {

    private AnimationTimer timer;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 700);

        // Création de la balle et de la raquette
        Ball ball = new Ball(scene.getWidth()/2, scene.getHeight()-100, 10, 2, 2);
        Paddle paddle = new Paddle(scene.getWidth()/2, scene.getHeight()-50 , 100, 20);

        // Ajouter les formes au pane
        root.getChildren().addAll(ball.getShape() , paddle.getShape());

        // Création et affichage des briques. Les briques sont ajoutées au Pane par la fonction et
        // leurs taille varie en fonction des dimenssions de la scene
        Brick[] bricks = createBricks(root, scene);

        //Déplacement de la raquette
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    paddle.setX(Math.max(0, paddle.getX() - 5)); // Empêche de dépasser le bord gauche
                    break;
                case RIGHT:
                    // Empêche de dépasser le bord droit
                    paddle.setX(Math.min(scene.getWidth() - paddle.getWidth(), paddle.getX() + 5));
                    break;
            }
        });

        // AnimationTimer pour déplacer la balle
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                ball.move(); // Met à jour la position de la balle

                // Rebondir sur les bords de la fenêtre

                if (ball.getX()- ball.getRadius() <= 0                          // Si la balle touche le mur de gauche
                        || ball.getX() + ball.getRadius() >= scene.getWidth())  // ou le mur de Droite
                    ball.switchVx();                                            // Inverser la direction horizontale

                if (ball.getY() - ball.getRadius() <= 0)                        // Si la balle touche le plafont
                    ball.switchVy();                                            // Inverser la direction verticale

                // Vérifier si la balle tombe sous la raquette
                if (ball.getY() > scene.getHeight()) {
                    stopGame("Game Over!", root);
                }

                // Collision avec la raquette
                if ( paddle.collideWith(ball) ) {
                    paddle.onCollisionWith(ball); // Inverser la direction verticale
                }

                // Collision avec les briques
                boolean allBrickBroken = true;
                for (Brick brick : bricks){
                    if ( !brick.isBroken() ){
                        allBrickBroken = false;
                        if ( brick.collideWith(ball) )
                            brick.onCollisionWith(ball);
                    }
                }
                if(allBrickBroken)
                    stopGame("YOU WIN !!!", root);
            }
        };

        this.timer.start(); // Démarrer l'animation

        primaryStage.setTitle("Casse-briques - Itération 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Brick[] createBricks(Pane root, Scene scene){
        int nbBrique = 20;
        double space = 2;
        int BriquesParLigne = 8;
        double longueurBrique = scene.getWidth()/BriquesParLigne - space ;
        double hauteurBrique = 30 ;

        Brick[] bricks = new Brick[nbBrique];

        for (int row = 0; row <= bricks.length/BriquesParLigne ; row++) {
            for (int colum = 0; colum < BriquesParLigne && row*BriquesParLigne + colum < bricks.length; colum++) {
                int idx = row * BriquesParLigne + colum;
                //Determine si la brique courante sera une brique normale ou une brique solide
                Random r = new Random();
                int random = r.nextInt(0,2);
                Brick brickToAdd;
                if(random == 0)
                    brickToAdd = new NormalBrick(colum * longueurBrique + colum * space + space/2, row * hauteurBrique + row *space,longueurBrique,hauteurBrique);
                else
                    brickToAdd = new SolidBrick(colum * longueurBrique + colum * space + space/2, row * hauteurBrique + row *space,longueurBrique,hauteurBrique);

                random = r.nextInt(0,3);
                if(random <2)
                    brickToAdd.breakThis();

                bricks[idx] = brickToAdd; // Ajoute la brique au tableau

                root.getChildren().add(bricks[idx].getShape()); // Ajouter chaque brique à l'interface
            }
        }
        return bricks;
    }

    private void stopGame(String message, Pane root) {
        // Arrêter l'animation
        timer.stop();

        // Créer un Label pour afficher le message
        Label endMessage = new Label(message);
        endMessage.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        endMessage.setTextFill(Color.INDIANRED);
        endMessage.setTextAlignment(TextAlignment.CENTER);

        // Centrer le Label dans la fenêtre
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(endMessage);
        stackPane.setPrefSize(root.getWidth(), root.getHeight());
        stackPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Fond semi-transparent
        root.getChildren().add(stackPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
