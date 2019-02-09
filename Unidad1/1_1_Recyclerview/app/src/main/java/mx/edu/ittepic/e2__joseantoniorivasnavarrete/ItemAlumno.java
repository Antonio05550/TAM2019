package mx.edu.ittepic.e2__joseantoniorivasnavarrete;

public class ItemAlumno {
    private int image;
    private String nombre;
    private String carrera;
    private String noControl;
    
    public ItemAlumno(int image, String nombre, String carrera, String noControl) {
        this.image = image;
        this.nombre = nombre;
        this.carrera = carrera;
        this.noControl = noControl;
    }

    public int getImage() {
        return image;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }
    public String getNoControl() {
        return noControl;
    }
}
