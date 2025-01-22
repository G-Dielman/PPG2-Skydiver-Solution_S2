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

public class SkydiverGame extends Application {

    private AnimationTimer timer;
    private int points;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 700);

        // Création de la balle, du bateau et de la mer
        Ball ball = new Ball(scene.getWidth()/2, 0, 10, 2);
        Rectangle mer = new Rectangle(0, scene.getHeight() - 100,scene.getWidth(), 100);
        mer.setStroke(Color.LIGHTBLUE);
        mer.setFill(Color.LIGHTBLUE);
        Boat boat = new Boat(scene.getWidth()/2, scene.getHeight()-125,100,50 );

        // Ajouter les formes au pane
        root.getChildren().addAll(mer,ball.getShape() , boat.getShape());



        //Déplacement de la raquette
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    boat.setX(Math.max(0, boat.getX() - 5)); // Empêche de dépasser le bord gauche
                    break;
                case RIGHT:
                    // Empêche de dépasser le bord droit
                    boat.setX(Math.min(scene.getWidth() - boat.getWidth(), boat.getX() + 5));
                    break;
            }
        });

        // AnimationTimer pour déplacer la balle
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                ball.move(); // Met à jour la position de la balle

                // Vérifier si la balle tombe sous la raquette
                if (ball.getY() > boat.getY()+boat.getHeight()/2 ) {
                    stopGame("Game Over with " + points+" points!", root);
                }

                // Collision avec le bateau
                if ( boat.collideWith(ball) ) {
                    ball.respawnIn(scene);
                    points += 10;
                }
            }
        };

        this.timer.start(); // Démarrer l'animation

        primaryStage.setTitle("Skydiver - Session 2");
        primaryStage.setScene(scene);
        primaryStage.show();
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
