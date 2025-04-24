package es.ies.puerto.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import es.ies.puerto.model.Pregunta;
import es.ies.puerto.model.Usuario;

public class ConfigManager {

    /**
    * Clase estatica que maneja el idioma y guarda el usuario activo
    */
    public static class ConfigProperties {
        static String path = "src/main/resources/idiomas/Esp.properties";
        private static final Properties properties = new Properties();

        /**
         * Metodo estatico para obtener una propiedad
         **/
        public static String getProperty(String key) {
            return properties.getProperty(key);
        }


        /**
         * Metodo estatico para cambiar la ruta de propiedades
         **/
        public static void setPath(String idioma) {
            File file = new File("src/main/resources/idiomas/"+idioma+".properties");

            if (!file.exists() || !file.isFile()) {
                System.out.println("Path:"+file.getAbsolutePath());
            }
            path = "src/main/resources/idiomas/"+idioma+".properties";
            try {
                FileInputStream input = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(input, "UTF-8");
                properties.load(isr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String getPath() {
            return path;
        }
    }

    public static class ValoresConfig{
        static Usuario usuario;
        static List<Pregunta> preguntas;

        public static void setPreguntas(List<Pregunta> preguntas){
            ValoresConfig.preguntas = preguntas;
        }

        public static Pregunta getPregunta(){
            return preguntas.get(0);
        }

        public static void eliminarPregunta(){
            ValoresConfig.preguntas.remove(0);
        }

        public static List<Pregunta> getPreguntas() {
            return preguntas;
        }

        public static Usuario getUsuario() {
            return usuario;
        }

        public static void setUsuario(Usuario usuario) {
            ValoresConfig.usuario = usuario;
        }
    
    }
}
