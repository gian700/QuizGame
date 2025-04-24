package es.ies.puerto.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.ies.puerto.model.Usuario;

public class UsuarioManager extends DatabaseManager{


    public UsuarioManager() throws SQLException {
        super();

    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        try {
            String sql = "SELECT * FROM usuario " + "where nombre='"+nombre+"'";
        ArrayList<Usuario> usuarios = obtenerUsuario(sql);
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public ArrayList<Usuario> obtenerUsarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        return obtenerUsuario(sql);
    }

    public ArrayList<Usuario> obtenerUsuario(String sql) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre");
                String contraseniaStr = resultado.getString("contrasenia");
                String idiomaStr = resultado.getString("idioma");
                int puntuacion = resultado.getInt("puntuacion");
                Usuario usuarioModel = new Usuario(nombreStr, contraseniaStr, idiomaStr, puntuacion);
                usuarios.add(usuarioModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            cerrar();
        }
        return usuarios;
    }

    public boolean crearUsuario(Usuario usuario) throws SQLException{
        if (usuario == null) {
            return false;
        }
        String query = "INSERT INTO usuario(nombre, contrasenia, idioma, puntuacion) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pStatement = conectar().prepareStatement(query);
            pStatement.setString(1, usuario.getNombre());
            pStatement.setString(2, usuario.getContrasenia());
            pStatement.setString(3, "Eng");
            pStatement.setInt(4, 0);
            if (pStatement.executeUpdate() == 1) {
                cerrar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cerrar();
        }
        return false;
    }

    public boolean updatePuntuacion(Usuario usuario) throws SQLException{
        if (usuario == null) {
            return false;
        }
        String query = "update usuario set puntuacion=? where nombre=?";

        try {
            PreparedStatement pStatement = conectar().prepareStatement(query);
            pStatement.setString(2, usuario.getNombre());
            pStatement.setInt(1, usuario.getPuntuacionMaxima());
            if (pStatement.executeUpdate() == 1) {
                cerrar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cerrar();
        }
        return false;
    }

    public boolean updateIdioma(Usuario usuario) throws SQLException{
        if (usuario == null) {
            return false;
        }
        String query = "update usuario set idioma=? where nombre=?";

        try {
            PreparedStatement pStatement = conectar().prepareStatement(query);
            pStatement.setString(2, usuario.getNombre());
            pStatement.setString(1, usuario.getIdioma());
            if (pStatement.executeUpdate() == 1) {
                cerrar();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cerrar();
        }
        return false;
    }
}
