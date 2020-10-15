package com.example.adaptadoresenandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Spinner modulosProfesionales, spinnerCoches;
    String[] coches ={"Mercedes", "Bmw", "Maseratti"};
    String[] cochesAño = {"2005", "1998", "20116"};
    int[] fotosCoches = {R.drawable.coche0, R.drawable.coche1, R.drawable.coche2};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para poner a pantalla completa
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        modulosProfesionales = findViewById(R.id.spinnerModullos);
        spinnerCoches = findViewById(R.id.spinnerCoches);

        String[] modulos = {"Desarrolo de interfaces", "Programación multimedia", "FOL", "Educación física"};



        ArrayAdapter<String> adaptadorModulos;
        adaptadorModulos = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,modulos);

        modulosProfesionales.setAdapter(adaptadorModulos);

        modulosProfesionales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Has seleccionado el módulo"+
                        adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        AdaptadorParaCoches adaptadorParaCoches = new AdaptadorParaCoches(this,R.layout.vista_coches,coches);
        spinnerCoches.setAdapter(adaptadorParaCoches);




    }

    private class AdaptadorParaCoches extends ArrayAdapter<String>{

        public AdaptadorParaCoches(@NonNull Context context, int resource) {
            super(context, resource);
        }

        public AdaptadorParaCoches(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position,convertView,parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position,convertView,parent);
        }

        public View crearFila(int posicion, View view, ViewGroup padre){
            LayoutInflater inflater = getLayoutInflater();
            View miCoche =  inflater.inflate(R.layout.vista_coches, padre,false);
            TextView titulo =  miCoche.findViewById(R.id.textViewNombre);
            TextView año = miCoche.findViewById(R.id.textViewAño);
            ImageView imagen  = miCoche.findViewById(R.id.imageView);


            titulo.setText(coches[posicion]);
            año.setText(cochesAño[posicion]);
            imagen.setImageResource(fotosCoches[posicion]);

            return miCoche;
        }
    }
}