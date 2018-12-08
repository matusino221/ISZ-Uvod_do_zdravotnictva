package myapp.uvoddozdravotnictva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.emailEditText);
                if (!isValidEmail(email.getText()))
                    return;
                RadioButton muz = findViewById(R.id.muzRadioButton);
                RadioButton zena = findViewById(R.id.zenaRadioButton);

                if (!muz.isChecked() && !zena.isChecked())
                    return;
                EditText vek = findViewById(R.id.vekEditText);

                if (TextUtils.isEmpty(vek.getText()))
                    return;
                Intent intent = new Intent(MainActivity.this, next.class);
                intent.putExtra("EMAIL", email.getText().toString());
                if (muz.isChecked())
                    intent.putExtra("POHLAVIE", "muž");
                if (zena.isChecked())
                    intent.putExtra("POHLAVIE", "žena");
                intent.putExtra("VEK", vek.getText().toString());
                startActivity(intent);
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


}
