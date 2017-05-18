package bolaRoja;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.*;

public class bolaRoja extends Application
{

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

        //Configuamos las propiedades del circulo.
        circulo.setCenterX(250.0f) ; 
        circulo.setCenterY(250.0f) ; 
        circulo.setRadius(20) ;
        circulo.setFill(Color.RED);
        
        //Creamos la escena con unas dimensiones
        Scene scene =new Scene(contenedor,500,500);

        //Añadimos la etapa al escenario.     
        escenario.setScene(scene);

        //Mostramos la escena
        escenario.show();
    }
}
