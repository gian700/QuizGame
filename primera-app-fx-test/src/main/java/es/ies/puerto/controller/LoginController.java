package es.ies.puerto.controller;

import java.sql.SQLException;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.database.UsuarioManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController extends AbstractController {

    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Text textFieldMensaje;

    @FXML
    private Button openRegistrar;

    @FXML
    private Button aceptarBoton;

    @FXML
    private Text textUsuario;
    
    UsuarioManager usuarioManager;

    @FXML
    private Text textContrasenia;

    @FXML
    protected void initialize() {
        try {
            usuarioManager = new UsuarioManager();
        } catch (Exception e) {
        }
        ConfigManager.ConfigProperties.setPath("Eng");
        openRegistrar.setText(ConfigManager.ConfigProperties.getProperty("crearCuenta"));
        aceptarBoton.setText(ConfigManager.ConfigProperties.getProperty("aceptar"));
        textUsuario.setText(ConfigManager.ConfigProperties.getProperty("usuario"));
        textContrasenia.setText(ConfigManager.ConfigProperties.getProperty("contrasenia"));
    }

    @FXML
    protected void onLoginButtonClick() throws SQLException {
        
        if (textFieldUsuario== null || textFieldUsuario.getText().isEmpty() || 
            textFieldPassword == null || textFieldPassword.getText().isEmpty() ) {
            textFieldMensaje.setText(ConfigManager.ConfigProperties.getProperty("Vacio"));
            return;
        }

        Usuario usuario = usuarioManager.obtenerUsuarioPorNombre(textFieldUsuario.getText());
        
        if (usuario == null) {
            textFieldMensaje.setText(ConfigManager.ConfigProperties.getProperty("noExistente"));
            return;
        }

        if (
            !textFieldPassword.getText().equals(usuario.getContrasenia())) {
            textFieldMensaje.setText(ConfigManager.ConfigProperties.getProperty("credencialesInvalidas"));
            return;
        } 

        textFieldMensaje.setText(ConfigManager.ConfigProperties.getProperty("credencialesValidas"));
        ConfigManager.ValoresConfig.setUsuario(usuario);
        cambiarPagina(openRegistrar, "inicio");
    }
    
    @FXML
    public void openRegistrarClick(){
        cambiarPagina(openRegistrar, "registro");
    }

}