package es.ies.puerto.controller;


import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Pregunta;
import es.ies.puerto.model.database.PreguntaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController extends AbstractController{
    @FXML 
    private Button ajustesBoton;

    @FXML 
    private Button jugarBoton;

    @FXML 
    private Button puntuaciones;

    @FXML 
    private Button cerrarSecionBoton;


    @FXML
    protected void initialize() {
        ConfigManager.ConfigProperties.setPath(ConfigManager.ValoresConfig.getUsuario().getIdioma());
        ajustesBoton.setText(ConfigManager.ConfigProperties.getProperty("ajustes"));
        jugarBoton.setText(ConfigManager.ConfigProperties.getProperty("jugar"));
        puntuaciones.setText(ConfigManager.ConfigProperties.getProperty("puntuacion"));
        cerrarSecionBoton.setText(ConfigManager.ConfigProperties.getProperty("cerrarSesion"));
    }

    @FXML
    protected void ajustesClick(){
        cambiarPagina(ajustesBoton, "ajustes");
    }

    @FXML
    protected void jugarClick() throws SQLException{
        List<Pregunta> pregunta = new PreguntaManager().obtenerPreguntas();
        Collections.shuffle(pregunta);
        ConfigManager.ValoresConfig.setPreguntas(pregunta);
        cambiarPagina(jugarBoton, "quiz");
    }

    @FXML
    protected void puntuacionesClick(){
        cambiarPagina(puntuaciones,"Posicion");
    }

    @FXML
    protected void cerrarClick(){
        cambiarPagina(cerrarSecionBoton, "login");
    }
}