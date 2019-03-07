package mx.edu.ittepic.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultadoP, operacionP;

    public static final String ACUMULADOR = "acumulador";
    public static final String RESULTADO = "resultado";
    public static final String RESULTADO2 = "resultado2";
    public static final String OPERACION = "operacion";

    private double acumulador=0.0;
    private Operacion operacionAct = Operacion.NONE;
    private String resultado = "0.0";
    private String resultado2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        resultadoP = findViewById( R.id.resultado );
        operacionP = findViewById( R.id.operacion );

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(ACUMULADOR, acumulador);
        outState.putString(RESULTADO, resultado);
        outState.putString(RESULTADO2, resultado2);
        outState.putString(OPERACION, operacionAct.name());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        acumulador = savedInstanceState.getDouble(ACUMULADOR);
        operacionAct = Operacion.valueOf(savedInstanceState.getString(OPERACION));
        resultado = savedInstanceState.getString(RESULTADO, "0.0");
        resultado2 = savedInstanceState.getString(RESULTADO2, "");
        updateDisplay();
    }

    private void updateDisplay() {
        resultadoP.setText(resultado);
        operacionP.setText(resultado2);
    }

    public void btnClicked(View v) {

        Button button = (Button) v;
        String value = button.getText().toString();

        switch (value) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (resultado.equals("0.0")) {
                    resultado = "";
                    resultado2 = "";
                }//END IF "NUMBERS"
                resultado += value;
                resultado2 += value;
                break;
            case ".":
                if (!resultado.contains(".")) {
                    resultado += value;
                    resultado2 += value;
                }//END IF "DOT"
                break;
            case "√":
                if (!resultado.isEmpty()) {
                    resultado2 = "SQRT(" + resultado + ")";
                    calculateRoot();
                }//END IF "SQRT"
                break;
            case "=":
                if (!resultado.isEmpty()) {
                    calculateResult();
                }//EN IF "EQUALS"
                break;
            case "←":
                clearDisplay();
                break;
            case "%":
                resultado2 = resultado + value + " of " + acumulador;
                calculatePercent();
                break;
        }
        updateDisplay();
    }
    public void btnOperacionClicked(View v) {

        Button button = (Button) v;
        String value = button.getText().toString();
        if (!resultado.isEmpty()) {
            resultado2 = resultado + value;
        }
        updateDisplay();
        calculateBasicOperation(value);
    }

    private void calculateRoot() {
        double displayValue = Double.parseDouble(resultado);
        resultado = String.valueOf(Math.sqrt(displayValue));
    }

    private void calculatePercent(){
        resultado = String.valueOf(acumulador*Double.parseDouble(resultado)/100);
    }

    private void clearDisplay() {
        acumulador = 0.0;
        operacionAct = Operacion.NONE;
        resultado = "0.0";
        resultado2 = "";
    }

    private void calculateResult() {
        double displayValue = Double.parseDouble(resultado);

        switch (operacionAct) {
            case ADD:
                resultado = String.valueOf(acumulador + displayValue);
                break;
            case SUBTRACT:
                resultado = String.valueOf(acumulador - displayValue);
                break;
            case MULTIPLY:
                resultado = String.valueOf(acumulador * displayValue);
                break;
            case DIVIDE:
                resultado = String.valueOf(acumulador / displayValue);
                break;
        }
        operacionAct = Operacion.NONE;
        acumulador = Double.parseDouble("0.0");
    }

    private void calculateBasicOperation(String value) {
        acumulador = Double.parseDouble(resultado);
        operacionAct = Operacion.operacionKey(value);
        resultado = "";
    }

}
