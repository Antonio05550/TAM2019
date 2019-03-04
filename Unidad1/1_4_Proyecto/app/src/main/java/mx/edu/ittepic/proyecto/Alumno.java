package mx.edu.ittepic.proyecto;

public class Alumno {
    private int id;
    private String nombre;
    private String carrera;
    private String noControl;
    
    public Alumno(String nombre, String carrera, String noControl) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.noControl = noControl;
    }

    public Alumno(int id, String nombre, String carrera, String noControl){
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.noControl = noControl;
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

    public String getNoControl() {
        return noControl;
    }
}
