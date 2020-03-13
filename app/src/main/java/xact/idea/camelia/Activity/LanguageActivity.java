package xact.idea.camelia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import io.paperdb.Paper;
import xact.idea.camelia.Activity.Household.HouseHoldActivity;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

public class LanguageActivity extends AppCompatActivity {

    RelativeLayout relative_bangla;
    RelativeLayout relative_english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        relative_bangla = findViewById(R.id.relative_bangla);
        relative_english = findViewById(R.id.relative_english);
        Paper.init(this);
        String language= Paper.book().read("language");
        if (language==null){
            Paper.book().write("language","en");
        }
        relative_bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.saveShared(LanguageActivity.this, SharedPreferenceUtil.LANG,  "bn");

                startActivity(new Intent(LanguageActivity.this, LoginActivity.class));
                finish();
            }
        });
        relative_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.saveShared(LanguageActivity.this, SharedPreferenceUtil.LANG, "en");
                startActivity(new Intent(LanguageActivity.this, LoginActivity.class));
                finish();
            }
        });
        relative_english = findViewById(R.id.relative_english);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }
}
