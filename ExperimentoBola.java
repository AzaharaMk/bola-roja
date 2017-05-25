package bolaRoja;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.animation.Animation;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text; 

public class ExperimentoBola extends Application
{
    private int velocidadEnX;
    private int velocidadEnY;
    private int velocidadPlataforma;
    private static int RADIO = 20;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        velocidadEnX = 1;
        velocidadEnY = 1;

        Circle circulo = new Circle();
        circulo.setFill(Color.RED);  
        circulo.setRadius(RADIO);

        Rectangle plataforma = new Rectangle();
        plataforma.setWidth(50);
        plataforma.setHeight(5);
        plataforma.setTranslateX(225);
        plataforma.setTranslateY(480);
        plataforma.setFill(Color.BLUE);
        contenedor.getChildren().add(plataforma);

        velocidadPlataforma = 1;

        Random aleatorio = new Random();

        circulo.setCenterX(20 + aleatorio.nextInt(500 - 40));
        circulo.setCenterY(50);
        contenedor.getChildren().add(circulo);

        Scene escena = new Scene(contenedor, 500, 500);
        escenario.setScene(escena);
        escenario.show();

        //Para poder pararlo tendremos que meter un KeyFrame dentro del Timeline
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), event -> {
                    circulo.setTranslateX(circulo.getTranslateX() + velocidadEnX);
                    circulo.setTranslateY(circulo.getTranslateY() + velocidadEnY);

                    plataforma.setTranslateX(plataforma.getTranslateX() + velocidadPlataforma);

                    if (circulo.getBoundsInParent().getMinX() <= 0 ||
                    circulo.getBoundsInParent().getMaxX() >= escena.getWidth()) {
                        velocidadEnX = -velocidadEnX;                              
                    }

                    if (circulo.getBoundsInParent().getMinY() <= 0 ) {
                        velocidadEnY = -velocidadEnY;  
                    }
                    else if (circulo.getBoundsInParent().getMinY() >= escena.getHeight())
                    {
                        //Cuando la bola no toque con la barra saldrá un mensaje de que hemos perdido el juego en el medio de la pantalla
                        Text perder = new Text("Game Over");
                        perder.setX(250) ; 
                        perder.setY(250) ; 
                        contenedor.getChildren().add(perder);
                        //Hacemos que se pare cuando perdemos.
                        timeline.stop();
                    }

                    //Hacemos que cuando toque los bordes la plataforma se pare.
                    if(plataforma.getBoundsInParent().getMinX() <= 0 || plataforma.getBoundsInParent().getMaxX() >= escena.getWidth())
                    {
                        //Cambiamos el valor de la velocidad a 0.
                        velocidadPlataforma = 0;
                    }

                    if (circulo.getBoundsInParent().getMaxY() == plataforma.getBoundsInParent().getMinY()) {
                        double centroEnXDeLaBola = circulo.getBoundsInParent().getMinX() + RADIO;
                        double minEnXDeLaPlataforma = plataforma.getBoundsInParent().getMinX();
                        double maxEnXDeLaPlataforma = plataforma.getBoundsInParent().getMaxX();
                        if ((centroEnXDeLaBola >= minEnXDeLaPlataforma) &&
                        (centroEnXDeLaBola <= maxEnXDeLaPlataforma)) {
                            //La bola esta sobre la plataforma
                            velocidadEnY = -velocidadEnY;
                        }
                    }
                });  

        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        escena.setOnKeyPressed(event ->
            {
                //Hacemos que el rectangulo se pare cuando llegue a los lados
                if (event.getCode() == KeyCode.LEFT) 
                {
                    if(plataforma.getBoundsInParent().getMinX() == 0)
                    {
                        velocidadPlataforma = 0;
                    }
                    else
                    {
                        velocidadPlataforma = -1;
                    }
                }

                if (event.getCode() == KeyCode.RIGHT) 
                {
                        if(plataforma.getBoundsInParent().getMaxX() == escena.getWidth())
                    {
                        velocidadPlataforma = 0;
                    }
                    else
                    {
                        velocidadPlataforma = 1;
                    }
                }
            });

    }
}

    