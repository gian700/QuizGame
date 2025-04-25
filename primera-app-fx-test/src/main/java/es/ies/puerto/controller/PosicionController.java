package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.database.UsuarioManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PosicionController extends AbstractController{

    @FXML
    private Text posicion1;

    @FXML
    private Text posicion2;

    @FXML
    private Text posicion3;

    @FXML
    private Button volverBoton;

    @FXML
    protected void initialize() throws SQLException{
        List<Usuario> usuarios = new UsuarioManager().obtenerUsarios();
        Collections.sort(usuarios);
        volverBoton.setText(ConfigManager.ConfigProperties.getProperty("volver"));
        posicion1.setText(usuarios.get(0).getNombre() + ": " + usuarios.get(0).getPuntuacionMaxima());
        posicion2.setText(usuarios.get(1).getNombre() + ": " + usuarios.get(1).getPuntuacionMaxima());
        posicion3.setText(usuarios.get(2).getNombre() + ": " + usuarios.get(2).getPuntuacionMaxima());
    }

    @FXML
    protected void volver(){
        cambiarPagina(volverBoton, "inicio");
    }
}
