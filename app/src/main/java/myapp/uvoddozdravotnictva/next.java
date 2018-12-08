package myapp.uvoddozdravotnictva;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class next extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Button nextButton = findViewById(R.id.sendButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tlak1 = findViewById(R.id.tlak1EditText);
                EditText tlak2 = findViewById(R.id.tlak2EditText);
                EditText teplota = findViewById(R.id.teplotaEditText);
                EditText tep = findViewById(R.id.tepEditText);
                EditText hmotnost = findViewById(R.id.hmotnostEditText);
                EditText lieky = findViewById(R.id.liekyEditText);
                EditText popis = findViewById(R.id.popisEditText);
                EditText termin = findViewById(R.id.terminEditText);
                String email = getIntent().getStringExtra("EMAIL");
                String pohlavie = getIntent().getStringExtra("POHLAVIE");
                String vek = getIntent().getStringExtra("VEK");
                SimpleDateFormat formatcasu = new SimpleDateFormat("dd:MM:yyyy HH:mm");
                String currentDateandTime = formatcasu.format(new Date());


                if (TextUtils.isEmpty(hmotnost.getText())) {
                    Toast.makeText(next.this, "Musíte vyplniť hmotnosť.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sprava = "Pohlavie: " + pohlavie +"\n"+
                        "Vek: " + vek +"\n";
                if (!TextUtils.isEmpty(tlak1.getText()))
                    sprava += "Systolicky tlak: " + tlak1.getText().toString() +"\n";
                if (!TextUtils.isEmpty(tlak2.getText()))
                    sprava += "Diastolicky tlak: " + tlak2.getText().toString() +"\n";
                if (!TextUtils.isEmpty(teplota.getText()))
                    sprava += "Teplota: " + teplota.getText().toString() +"\n";
                if (!TextUtils.isEmpty(tep.getText()))
                    sprava += "Tep: " + tep.getText().toString() +"\n";
                if (!TextUtils.isEmpty(hmotnost.getText()))
                    sprava += "Hmotnosť: " + hmotnost.getText().toString() +"\n";
                if (!TextUtils.isEmpty(lieky.getText()))
                    sprava += "Lieky: " + lieky.getText().toString() +"\n";
                if (!TextUtils.isEmpty(popis.getText()))
                    sprava += "Popis: " + popis.getText().toString() +"\n";
                if (!TextUtils.isEmpty(termin.getText()))
                    sprava += "termin: " + termin.getText().toString() +"\n";

                sprava += "Datum: " + currentDateandTime;

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email} );
                i.putExtra(Intent.EXTRA_SUBJECT, "Objednavka");
                i.putExtra(Intent.EXTRA_TEXT   , sprava);
                try {
                    startActivity(Intent.createChooser(i, "Posli mail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(next.this, "Nemate ziadny emailovy klient nainstalovny.", Toast.LENGTH_SHORT).show();
                }
            }
        });

       }
}
