package xact.idea.camelia.Activity.Household;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HouseHoldActivity extends AppCompatActivity {

    LinearLayout linear_dashboard;
    LinearLayout linear_member_status;
    LinearLayout linear_summary;
    TextView tv_store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold);
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.root_rlt_dashboard));
        linear_dashboard=findViewById(R.id.linear_dashboard);
        linear_member_status=findViewById(R.id.linear_member_status);
        linear_summary=findViewById(R.id.linear_summary);
        tv_store=findViewById(R.id.tv_store);
        tv_store.setSelected(true);
        linear_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
                //  finish();
            }
        });
        linear_member_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "status");
                startActivity(intent);
                //  finish();
            }
        });
        linear_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseHoldActivity.this, HouseholdHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXTRA_SESSION", "summary");
                startActivity(intent);
                //  finish();
            }
        });
    }
}
