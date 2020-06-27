package xact.idea.camelia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import okhttp3.internal.Util;
import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SharedPreferenceUtil;
import xact.idea.camelia.Utils.Utils;

import static xact.idea.camelia.Utils.Utils.dismissLoadingProgress;

public class SpalashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.rlt_root));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToLoginPage();
            }
        }, 3000);
    }
    private void goToLoginPage() {
        if (SharedPreferenceUtil.getUserID(SpalashActivity.this)==null||SharedPreferenceUtil.getUserID(SpalashActivity.this).equals("")) {
            Intent i = new Intent(SpalashActivity.this, LanguageActivity.class);
            startActivity(i);
            finish();
        } else {
            if (SharedPreferenceUtil.getUserRole(SpalashActivity.this).equals("hh")){
                startActivity(new Intent(SpalashActivity.this, HouseHoldActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(SpalashActivity.this, CCUserActivity.class));
                finish();

            }

        }

    }



}
