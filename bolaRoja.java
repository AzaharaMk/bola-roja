package bolaRoja;
/*import javafx.animation.Timeline;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.*; 
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.control.Button;*/
import javafx.scene.shape.*;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.paint.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
    import javafx.animation.Timeline;

public class bolaRoja extends Application
{

    public static double ballSpeed = 1;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario) 
    {

        //Creamos un objeto de tipo circulo.
        Circle circulo = new Circle();

        //Creamos un contenedor de tipo Group
        Group contenedor = new Group(circulo);

        //Creamos la escena con unas dimensiones
        Scene scene =new Scene(contenedor,500,500);

        //Configuamos las propiedades del circulo.
        circulo.setCenterX(250.0f) ; 
        circulo.setCenterY(250.0f) ; 
        circulo.setRadius(20) ;
        circulo.setFill(Color.RED);
        
        

        //hacemos que la bola se mueva

        Timeline timeline = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent ae) {
                            //hacemos que se mueva horizontalmente y desaparezca
                            circulo.setTranslateX(circulo.getTranslateX() + 5);
                            //
                            circulo.setTranslateY(circulo.getTranslateY() + 5);
                        }
                    })
            );
                        timeline.setCycleCount(60);
        timeline.play();  

        //Creamos un botón para poder parar la animaión.
         //Creamos el botón de enviar y lo añadimos al contenedor.
        Button botonParar = new Button("Parar");
        contenedor.getChildren().add(botonParar);
        
         botonParar.setOnAction(event ->{
            if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
                timeline.stop();
            } else {
                timeline.play();
            }


        });
        
        
        //Añadimos la etapa al escenario.     
        escenario.setScene(scene);

        //Mostramos la escena
        escenario.show();
    }
}
