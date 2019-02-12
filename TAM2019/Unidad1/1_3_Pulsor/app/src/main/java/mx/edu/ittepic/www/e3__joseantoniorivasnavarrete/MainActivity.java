package mx.edu.ittepic.www.e3__joseantoniorivasnavarrete;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    byte juego=0;
    CountDownTimer tiempo;
    double numero=1;
    Button noAle;
    TextView noFijoA;
    DecimalFormat formato = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noFijoA = findViewById(R.id.noFijoA);
        noAle = findViewById(R.id.noAle);

        noAle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(juego==0){
                    Numero();
                    Numeros();
                    juego=1;
                }
                else{
                    if((noFijoA.getText()).equals( noAle.getText() )){
                        Toast.makeText(MainActivity.this, "Correcto !" + noFijoA.getText()+" : "+noAle.getText(), Toast.LENGTH_SHORT).show();
                        tiempo.cancel();
                        noAle.setText( "REINTENTAR" );
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Incorrecto!" + noFijoA.getText()+" : "+noAle.getText(), Toast.LENGTH_SHORT).show();
                        tiempo.cancel();
                        noAle.setText( "REINTENTAR" );
                    }
                    juego=0;
                }
            }
        });
    }
    public void Numero(){
        Random r = new Random();
        float decimal = 0 + (1 - 0) * r.nextFloat();
        double numero = 1 + ( 3 - 1 ) * decimal;


        noFijoA.setText(formato.format(numero));
        Toast.makeText(MainActivity.this, "Comienza!", Toast.LENGTH_SHORT).show();
    }

    public void Numeros(){
        tiempo = new CountDownTimer(10000,300)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                if(numero<=3){
                    numero=numero+.1;
                    noAle.setText(""+formato.format( numero ));
                }
                else
                    numero=1;
            }
            @Override
            public void onFinish()
            {
                tiempo.start();
            }
        }; tiempo.start();
    }

}
