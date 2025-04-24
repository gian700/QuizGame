package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.database.UsuarioManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * @author Giancarlo
 * @version 1.0.0
 */
public class AjustesController extends AbstractController{

    @FXML
    private ComboBox comboIdioma;

    @FXML 
    private Button  volverBoton;

    @FXML
    protected void initialize() {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("Esp");
        idiomas.add("Eng");
        comboIdioma.getItems().addAll(idiomas);
        volverBoton.setText(ConfigManager.ConfigProperties.getProperty("volver"));
        comboIdioma.setPromptText(ConfigManager.ConfigProperties.getProperty("idioma"));
    }

    @FXML
    protected void cambiarIdioma() throws SQLException {
        ConfigManager.ValoresConfig.getUsuario().setIdioma(comboIdioma.getValue().toString());
        ConfigManager.ConfigProperties.setPath(comboIdioma.getValue().toString());
        new UsuarioManager().updateIdioma(ConfigManager.ValoresConfig.getUsuario());
        volverBoton.setText(ConfigManager.ConfigProperties.getProperty("volver"));
        comboIdioma.setPromptText(ConfigManager.ConfigProperties.getProperty("idioma"));
    }

    @FXML
    protected void volver() {
        cambiarPagina(volverBoton, "inicio");
    }

}
