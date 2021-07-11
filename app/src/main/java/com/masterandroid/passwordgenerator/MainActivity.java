package com.masterandroid.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonGenPass;
    Button buttonCpyClip;
    Spinner spinnerNumber;
    TextView password;
    Integer number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGenPass = findViewById(R.id.button_generate_password);
        password = findViewById(R.id.textPassGenerated);

        spinnerNumber = (Spinner) findViewById(R.id.spinnerNumber);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.number,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumber.setAdapter(adapter);
        spinnerNumber.setOnItemSelectedListener(this);
        buttonGenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char [] parola = new char[number];
                for( int i = 0;i < number ;i++) {
                    parola [i] = (char)(65+i);
                }
                String txt = "";
                for(int i = 0 ;i<number ;i++)
                    txt = txt + parola[i];
                password.setText(txt);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        number = Integer.valueOf(spinnerNumber.getSelectedItem().toString());
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}