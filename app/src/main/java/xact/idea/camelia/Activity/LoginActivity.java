package xact.idea.camelia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class LoginActivity extends AppCompatActivity {

    Button sign_in;
    EditText edit_text_email;
    EditText edit_text_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.rlt_root));
        sign_in=findViewById(R.id.sign_in);
        edit_text_email=findViewById(R.id.edit_text_email);
        edit_text_password=findViewById(R.id.edit_text_password);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_text_email.getText().toString().equals("evankhan1234@gmail.com")){
                    Intent i = new Intent(LoginActivity.this, HouseHoldActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i = new Intent(LoginActivity.this, CCUserActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
}
