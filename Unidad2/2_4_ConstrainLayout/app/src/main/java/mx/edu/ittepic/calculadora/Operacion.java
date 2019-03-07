package mx.edu.ittepic.calculadora;

public enum Operacion {
    NONE(""),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String KEY;

    Operacion(String key) {
        this.KEY = key;
    }


    /*public String getKEY() {
        return KEY;
    }*/

    /*@Override
    public String toString() {
        return  this.KEY;
    }*/


    public static Operacion operacionKey(String value) {
        for (Operacion op : values()) {
            if (op.KEY.equals(value)) {
                return op;
            }
        }
        return NONE;
    }

}