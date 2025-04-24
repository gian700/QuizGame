package es.ies.puerto.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pregunta {
    String pregunta;
    String opcionFalsa1;
    String opcionFalsa2;
    String opcionVerdadera1;
    String opcionVerdadera2;


    public Pregunta() {
    }

    public Pregunta(String pregunta, String opcionFalsa1, String opcionFalsa2, String opcionVerdadera1, String opcionVerdadera2) {
        this.pregunta = pregunta;
        this.opcionFalsa1 = opcionFalsa1;
        this.opcionFalsa2 = opcionFalsa2;
        this.opcionVerdadera1 = opcionVerdadera1;
        this.opcionVerdadera2 = opcionVerdadera2;
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcionFalsa1() {
        return this.opcionFalsa1;
    }

    public void setOpcionFalsa1(String opcionFalsa1) {
        this.opcionFalsa1 = opcionFalsa1;
    }

    public String getOpcionFalsa2() {
        return this.opcionFalsa2;
    }

    public void setOpcionFalsa2(String opcionFalsa2) {
        this.opcionFalsa2 = opcionFalsa2;
    }

    public String getOpcionVerdadera1() {
        return this.opcionVerdadera1;
    }

    public void setOpcionVerdadera1(String opcionVerdadera1) {
        this.opcionVerdadera1 = opcionVerdadera1;
    }

    public String getOpcionVerdadera2() {
        return this.opcionVerdadera2;
    }

    public void setOpcionVerdadera2(String opcionVerdadera2) {
        this.opcionVerdadera2 = opcionVerdadera2;
    }

    public Pregunta pregunta(String pregunta) {
        setPregunta(pregunta);
        return this;
    }

    public Pregunta opcionFalsa1(String opcionFalsa1) {
        setOpcionFalsa1(opcionFalsa1);
        return this;
    }

    public Pregunta opcionFalsa2(String opcionFalsa2) {
        setOpcionFalsa2(opcionFalsa2);
        return this;
    }

    public Pregunta opcionVerdadera1(String opcionVerdadera1) {
        setOpcionVerdadera1(opcionVerdadera1);
        return this;
    }

    public Pregunta opcionVerdadera2(String opcionVerdadera2) {
        setOpcionVerdadera2(opcionVerdadera2);
        return this;
    }

    public List<String> getLista() {
            List<String> lista = new ArrayList<>(Arrays.asList(opcionFalsa1, opcionFalsa2, opcionVerdadera1, opcionVerdadera2));
            return lista;
        }
        
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pregunta)) {
            return false;
        }
        Pregunta pregunta = (Pregunta) o;
        return Objects.equals(pregunta, pregunta.pregunta) && Objects.equals(opcionFalsa1, pregunta.opcionFalsa1) && Objects.equals(opcionFalsa2, pregunta.opcionFalsa2) && Objects.equals(opcionVerdadera1, pregunta.opcionVerdadera1) && Objects.equals(opcionVerdadera2, pregunta.opcionVerdadera2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pregunta, opcionFalsa1, opcionFalsa2, opcionVerdadera1, opcionVerdadera2);
    }

    @Override
    public String toString() {
        return "{" +
            " pregunta='" + getPregunta() + "'" +
            ", opcionFalsa1='" + getOpcionFalsa1() + "'" +
            ", opcionFalsa2='" + getOpcionFalsa2() + "'" +
            ", opcionVerdadera1='" + getOpcionVerdadera1() + "'" +
            ", opcionVerdadera2='" + getOpcionVerdadera2() + "'" +
            "}";
    }
    
   

    
    
}
