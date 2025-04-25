package es.ies.puerto.model;
import java.util.Objects;


public class Usuario implements Comparable<Usuario>{

    String nombre;
    String contrasenia;
    String idioma;
    int puntuacionMaxima;
    int puntuacion;
    int preguntasRespondidasTotal;
    int preguntasRespondidas;
    int correctas;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, String idioma, int puntuacionMaxima) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.idioma = idioma;
        this.puntuacionMaxima = puntuacionMaxima;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getPuntuacionMaxima() {
        return this.puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = puntuacionMaxima;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void sumarPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion + this.puntuacion;
    }

    public int getPreguntasRespondidasTotal() {
        return this.preguntasRespondidasTotal;
    }

    public void setPreguntasRespondidasTotal(int preguntasRespondidasTotal) {
        this.preguntasRespondidasTotal = preguntasRespondidasTotal;
    }

    public int getPreguntasRespondidas() {
        return this.preguntasRespondidas;
    }

    public void setPreguntasRespondidas(int preguntasRespondidas) {
        this.preguntasRespondidas = preguntasRespondidas;
    }

    public int getCorrectas() {
        return this.correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public void remove(){
        this.preguntasRespondidas = 0;
        this.correctas = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuarioEntity = (Usuario) o;
        return Objects.equals(nombre, usuarioEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", contrasenia='" + getContrasenia() + "'" +
            ", idioma='" + getIdioma() + "'" +
            ", puntuacionMaxima='" + getPuntuacionMaxima() + "'" +
            ", puntuacion='" + getPuntuacion() + "'" +
            ", preguntasRespondidasTotal='" + getPreguntasRespondidasTotal() + "'" +
            ", preguntasRespondidas='" + getPreguntasRespondidas() + "'" +
            ", correctas='" + getCorrectas() + "'" +
            "}";
    }

    @Override
    public int compareTo(Usuario otroUsuario) {
        if (this.puntuacionMaxima > otroUsuario.getPuntuacionMaxima()) {
            return -1; 
         } 
         if (this.puntuacionMaxima < otroUsuario.getPuntuacionMaxima()) {
            return 1; 
         } 
         return 0; 
    }
}