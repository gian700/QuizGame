package es.ies.puerto.controller.abstractas;

import java.io.IOException;

import es.ies.puerto.PrincipalApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class AbstractController {

    protected Button botonInutil;

    /**
     * Metodo que permite cambiar la pagina
     * @param boton
     * @param pagina
     */
    protected void cambiarPagina(Button boton, String pagina) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource(pagina+".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) boton.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle(pagina);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
