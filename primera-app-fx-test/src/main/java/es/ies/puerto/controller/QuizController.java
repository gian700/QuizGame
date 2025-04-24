package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Pregunta;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.database.UsuarioManager;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class QuizController extends AbstractController{

    @FXML
    private Button opcion1;

    @FXML
    private Button opcion2;

    @FXML
    private Button opcion3;

    @FXML
    private Button opcion4;

    @FXML
    private Button continuar;

    @FXML
    private TextField preguntaText;

    private Pregunta pregunta;
    private List<Button> botones;
    private Usuario usuario;

    @FXML
    protected void initialize() {
        continuar.setText(ConfigManager.ConfigProperties.getProperty("continuar"));
        usuario = ConfigManager.ValoresConfig.getUsuario();
        pregunta = ConfigManager.ValoresConfig.getPregunta();
        preguntaText.setText(pregunta.getPregunta());
        botones = new ArrayList<>(Arrays.asList(opcion1, opcion2, opcion3, opcion4));
        List<String> preguntas = pregunta.getLista();
        Collections.shuffle(preguntas);
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setText(preguntas.get(i));
        }
    }

    @FXML
    protected void pulsar(ActionEvent event){
        Button boton = (Button) event.getSource();
        if (!boton.getStyle().equals("")) {
            boton.setStyle("");
            continuar.setVisible(false);
            
            return;
        }
        if (comprobarPulsados() >= 2) {
            return;
        }
        boton.setStyle("-fx-background-color: Yellow");
        if (comprobarPulsados() == 2) {
            continuar.setVisible(true);
        }
    }

    protected int comprobarPulsados(){
        int contador = 0;
        for (Button boton : botones) {
            if (!boton.getStyle().equals("")) {
                contador++;
            }
        }
        return contador;
    }

    @FXML
    protected void comprobar(){
        boolean correcta;
        usuario.setPreguntasRespondidas(usuario.getPreguntasRespondidas()+1);
        usuario.setPreguntasRespondidasTotal(usuario.getPreguntasRespondidasTotal()+1);

        for (Button boton : botones) {
            correcta = false;
            if (boton.getText().equals(pregunta.getOpcionVerdadera1()) || 
                boton.getText().equals(pregunta.getOpcionVerdadera2())) {
                correcta = true;
            }
            if (correcta && boton.getStyle().equals("")) {
                boton.setStyle("-fx-background-color: Orange");
            }
            if (correcta && boton.getStyle().equals("-fx-background-color: Yellow")) {
                boton.setStyle("-fx-background-color: Green");
            }
            if (!correcta && boton.getStyle().equals("-fx-background-color: Yellow")) {
                boton.setStyle("-fx-background-color: Red");
            }
        }
        ConfigManager.ValoresConfig.eliminarPregunta();
        if (comprobarPulsados() == 2) {
            usuario.setCorrectas(usuario.getCorrectas()+1);
            usuario.sumarPuntuacion(100);
        }
        if (comprobarPulsados() == 3) {
            usuario.sumarPuntuacion(50);
        }

        if (ConfigManager.ValoresConfig.getPreguntas().isEmpty()) {
            return;
        }
        if (usuario.getPreguntasRespondidas()==1) {
            continuar();
            usuario.remove();
        }
        PauseTransition pausa = new PauseTransition(Duration.millis(550));
        pausa.setOnFinished(event -> cambiarPagina(continuar, "quiz"));
        pausa.play();
    }

    protected void continuar(){
        Alert alerta = new Alert(Alert.AlertType.NONE);

        alerta.setTitle("Continuar");
        alerta.setHeaderText("Puntuacion actual:" + usuario.getPuntuacion());
        alerta.setContentText("Preguntas acertadas: " + usuario.getCorrectas() + "\n" +
                            "Preguntas falladas: " + (7-usuario.getCorrectas()));
        ButtonType botonRendirse = new ButtonType("Rendirse");
        ButtonType botonApostar = new ButtonType("Continuar");
        
        alerta.getButtonTypes().setAll(botonRendirse, botonApostar);
        alerta.initOwner(opcion1.getScene().getWindow());
    
        alerta.showAndWait().ifPresent(resultado -> {
            if (resultado == botonRendirse) {
                usuario.sumarPuntuacion(500);
                perder();
                return;
            }  
            List<Boolean> listaBooleans = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                listaBooleans.add(false);   
            }

            for (int i = 0; i < 6+usuario.getCorrectas(); i++) {
                listaBooleans.set(i, true);
            }
            Collections.shuffle(listaBooleans);
            /*if (!listaBooleans.get(0)) {
                perder();
                return;
            }
            usuario.sumarPuntuacion(1000);*/
            mostrarRuletaYSorteo(listaBooleans);
        });
    }

    protected void perder(){
        boolean nuevoRecord = false;
        if (usuario.getPuntuacionMaxima() < usuario.getPuntuacion()) {
            usuario.setPuntuacionMaxima(usuario.getPuntuacion());
            nuevoRecord = true;
        }
            
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setTitle("Perdiste");
        alerta.setHeaderText("Perdiste");
        alerta.setContentText("Puntuacion maxima conseguida: " + usuario.getPuntuacionMaxima() + (nuevoRecord ? " Nuevo record":"")+ "\n" +
                            "Puntuacion conseguida: " + usuario.getPuntuacion() + "\n" +
                            "Preguntas respondidas: " + usuario.getPreguntasRespondidasTotal());
        ButtonType botonRendirse = new ButtonType("Menu pricipal");
        
        alerta.getButtonTypes().setAll(botonRendirse);
        alerta.initOwner(opcion1.getScene().getWindow());
        alerta.showAndWait();

        try {
            new UsuarioManager().updatePuntuacion(usuario);
        } catch (SQLException e) {
        }    
        usuario.remove();
        usuario.setPreguntasRespondidasTotal(0);
        cambiarPagina(opcion1, "inicio");
    }























    /**
     * Codigo que muestra una ruleta y da el resultado, no se como pero funciona
     * @param sectores
     */
    public void mostrarRuletaYSorteo(List<Boolean> sectores) {
    Alert ruleta = new Alert(Alert.AlertType.INFORMATION);
    ruleta.setTitle("Resultado de la Ruleta");

    HBox visual = new HBox(10);
    visual.setAlignment(Pos.CENTER);

    for (Boolean sector : sectores) {
        Circle c = new Circle(20);
        c.setFill(sector ? Color.LIMEGREEN : Color.CRIMSON);
        visual.getChildren().add(c);
    }

    // Elegir sector ganador
    int indiceElegido = new Random().nextInt(sectores.size());
    Circle elegido = (Circle) visual.getChildren().get(indiceElegido);
    elegido.setStroke(Color.GOLD);
    elegido.setStrokeWidth(4);

    // Crear un mensaje visual combinado
    Label mensaje = new Label();
    mensaje.setFont(Font.font(16));
    mensaje.setTextAlignment(TextAlignment.CENTER);

    if (!sectores.get(indiceElegido)) {
        mensaje.setText("¡Perdiste!");
    } else {
        usuario.sumarPuntuacion(1000);
        mensaje.setText("Continuas");
    }

    VBox contenido = new VBox(15, mensaje, visual);
    contenido.setAlignment(Pos.CENTER);
    ruleta.getDialogPane().setContent(contenido);

    ruleta.showAndWait();

    if (!sectores.get(indiceElegido)) {
        perder(); // Después de mostrar la ruleta
    }
}
}