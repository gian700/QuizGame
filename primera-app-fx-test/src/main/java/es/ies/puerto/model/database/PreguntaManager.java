package es.ies.puerto.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.model.Pregunta;


public class PreguntaManager extends DatabaseManager{

    public PreguntaManager() throws SQLException {
        super();

    }

    public ArrayList<Pregunta> obtenerPreguntas() throws SQLException {
        String sql = "SELECT * FROM preguntas" + ConfigManager.ValoresConfig.getUsuario().getIdioma();
        return obtenerPregunta(sql);
    }

    protected ArrayList<Pregunta> obtenerPregunta(String sql) throws SQLException {
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {

                String preguntaStr = resultado.getString("pregunta");
                String falsa1 = resultado.getString("falsa1");
                String falsa2 = resultado.getString("falsa2"); 
                String verdadera1 = resultado.getString("verdadera1");
                String verdadera2 = resultado.getString("verdadera2");
                
                Pregunta pregunta = new Pregunta(preguntaStr, falsa1, falsa2, verdadera1, verdadera2);
                preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   
            cerrar();
        }
        return preguntas;
    }
}
