package mx.edu.ittepic.laboratorio;

public class Alumno {
    private int id;
    private String nombre;
    private String carrera;
    private String noControl;
    private String celular;
    private String correo;
    
    public Alumno(String nombre, String carrera, String noControl, String celular, String correo) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.noControl = noControl;
        this.celular = celular;
        this.correo = correo;
    }

    public Alumno(int id, String nombre, String carrera, String noControl, String celular, String correo){
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.noControl = noControl;
        this.celular = celular;
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNoControl() {
        return noControl;
    }
}
