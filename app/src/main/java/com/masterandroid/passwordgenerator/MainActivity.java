package com.masterandroid.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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
        buttonCpyClip = findViewById(R.id.button_copy_clipboard);

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
                password.setText(generatePass(number));
            }
        });
        buttonCpyClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpy = password.getText().toString();
                ClipboardManager clipboardManager =
                        (ClipboardManager)
                                getSystemService
                                (Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Label", cpy);
                clipboardManager.setPrimaryClip(clip);
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
    public String generatePass(int nr){
        Random rand = new Random();
        char [] parola = new char[nr];
        boolean ok = true;
        for(int i=0 ; i < nr; i++){
            if(ok == false){
                ok = true;
                parola[i] = Character.forDigit(rand.nextInt(9),10);
                Log.i("nomer",String.valueOf(parola[i]));
            }
            else
                if(ok == true)
            {
                ok = false;
                parola[i] = (char)(rand.nextInt(53) + 72);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c:parola)
        {
            sb.append(c);
        }
        String txt = sb.toString();
        Log.i("Aicie",txt);
        return txt;
    }
}