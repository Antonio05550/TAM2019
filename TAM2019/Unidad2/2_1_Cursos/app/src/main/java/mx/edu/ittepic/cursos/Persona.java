package mx.edu.ittepic.cursos;

public class Persona {
    private int id;
    private String clave;
    private String nombre;
    private String salario;
    
    public Persona(String clave, String nombre, String salario) {
        this.clave = clave;
        this.nombre = nombre;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Persona(int id, String clave, String nombre, String salario){
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.salario = salario;
    }
}
