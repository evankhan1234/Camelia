package xact.idea.camelia.Activity.Household;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Fragment.CCUserDashBoardFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCUserMemberSummaryFragment;
import xact.idea.camelia.HouseHoldFragment.HHCCSendingFragment;
import xact.idea.camelia.HouseHoldFragment.HHDashboardFragment;
import xact.idea.camelia.HouseHoldFragment.HHMembersFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HouseholdHomeActivity extends AppCompatActivity {
    private TextView tv_home_menu;
    private TextView tv_dashboard_menu;
    private TextView tv_member_status_menu;
    private TextView tv_summary_menu;
    private TextView tv_user_setup_menus;
    private TextView title;
    private ImageView btn_close_drawer;
    public static final int HOME_BTN = 0;
    public static final int DASHBOARD_BTN  = 1;
    public static final int MEMBER_STATUS_BTN  = 2;
    public static final int MEMBER_SUMMARY_BTN  = 3;

    private RelativeLayout rlt_header;
    private RelativeLayout rlt_header_details;
    private RelativeLayout rlt_header_status_details;
    private TextView details_title;
    private View view_header_details;
    private View view_header_status_details;
    private ImageButton btn_header_back_;
    private ImageButton btn_header_back;
    private ImageButton btn_header_status_back;
    private ImageButton btn_header_sync;
    private ImageButton btn_header_application_create;
    private LinearLayout linear;
    private RelativeLayout relative;
    private DrawerLayout drawer_layout;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    //IRetrofitApi mService;
    private Context mContext = null;
    private AppCompatImageView btn_footer_home;
    private AppCompatImageView btn_footer_dashboard;
    private AppCompatImageView btn_footer_member_status;
    private AppCompatImageView btn_footer_summary;
    private AppCompatImageView btn_footer_setup_user;
    private final String TAG = getClass().getSimpleName();
    private FragmentManager mFragManager;
    private FragmentTransaction fragTransaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_home);
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.rlt_root));
        initView();
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION");


        setFooter(sessionId);
    }
    private void initView() {
        btn_header_status_back = findViewById(R.id.btn_header_status_back);
        rlt_header_status_details = findViewById(R.id.rlt_header_status_details);
        view_header_status_details = findViewById(R.id.view_header_status_details);
        btn_close_drawer = findViewById(R.id.btn_close_drawer);
        btn_header_back = findViewById(R.id.btn_header_back);
        title = findViewById(R.id.title);
        btn_header_back_ = findViewById(R.id.btn_header_back_);
        view_header_details = findViewById(R.id.view_header_details);
        drawer_layout = findViewById(R.id.drawer_layout);
        rlt_header = findViewById(R.id.rlt_header);
        rlt_header_details = findViewById(R.id.rlt_header_details);
        details_title = findViewById(R.id.details_title);
        btn_footer_home = findViewById(R.id.btn_footer_home);
        tv_home_menu = findViewById(R.id.tv_home_menu);
        btn_footer_dashboard = findViewById(R.id.btn_footer_dashboard);
        tv_dashboard_menu = findViewById(R.id.tv_dashboard_menu);
        btn_footer_member_status = findViewById(R.id.btn_footer_member_status);
        tv_member_status_menu = findViewById(R.id.tv_member_status_menu);
        btn_footer_summary = findViewById(R.id.btn_footer_summary);
        tv_summary_menu = findViewById(R.id.tv_summary_menu);
        btn_header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);

            }
        });
        btn_close_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
                drawer_layout.closeDrawer(GravityCompat.START);
            }
        });
        btn_header_status_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  back();
            }
        });
        btn_header_back_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   backForDetails();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }
    private void setFooter(String value) {

        switch (value) {

            case "dashboard":

                title.setText("Dashboard");
                btn_footer_dashboard.setSelected(true);
                tv_dashboard_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_DASHBOARD, null);


                break;
            case "status":
                title.setText("Household Members");
                btn_footer_member_status.setSelected(true);
                tv_member_status_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_MEMBER_STATUS, null);
                break;
            case "summary":
                title.setText("CC Sending Information");
                btn_footer_summary.setSelected(true);
                tv_summary_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_MEMBER_SUMMARY, null);
                break;


            default:
                btn_footer_home.setSelected(true);
                tv_home_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_HOME, null);
        }
    }

    public void afterClickTabItem(int fragId, Object obj) {
        addFragment(fragId, false);
    }

    public void addFragment(int fragId, boolean isHasAnimation) {

        mFragManager = getSupportFragmentManager();
        // create transaction
        fragTransaction = mFragManager.beginTransaction();


        Fragment newFrag = null;
        // identify which fragment will be called
        switch (fragId) {

            case Constant.FRAG_DASHBOARD:

                newFrag = new HHDashboardFragment();

                break;
            case Constant.FRAG_MEMBER_STATUS:
                newFrag = new HHMembersFragment();


                break;

            case Constant.FRAG_MEMBER_SUMMARY:


                newFrag = new HHCCSendingFragment();


                break;

            default:
                break;
        }


        // param 1: container id, param 2: new fragment, param 3: fragment id
        fragTransaction.add(R.id.main_container, newFrag, newFrag.getClass().getSimpleName());
        // prevent showed when user press back fabReview
        fragTransaction.addToBackStack(newFrag.getClass().getSimpleName());
        fragTransaction.commit();

    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = HouseholdHomeActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        Collections.reverse(fragments);
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    private void setUnselectAllmenu() {
        tv_home_menu.setSelected(false);
        tv_summary_menu.setSelected(false);
        tv_dashboard_menu.setSelected(false);
        tv_member_status_menu.setSelected(false);



        btn_footer_home.setSelected(false);
        btn_footer_member_status.setSelected(false);
        btn_footer_summary.setSelected(false);
        btn_footer_dashboard.setSelected(false);


    }

    public void setUpFooter(int type) {
        setUnselectAllmenu();
        switch (type) {
            case 0:
                tv_home_menu.setSelected(true);
                btn_footer_home.setSelected(true);
                break;
            case 1:
                tv_dashboard_menu.setSelected(true);
                btn_footer_dashboard.setSelected(true);
                break;
            case 2:
                tv_member_status_menu.setSelected(true);
                btn_footer_member_status.setSelected(true);
                break;
            case 3:
                tv_summary_menu.setSelected(true);
                btn_footer_summary.setSelected(true);
                break;

        }

    }

    //    }
    public void btn_home_clicked(View view) {

        Log.e(TAG, "Home Button Clicked");

        setUpFooter(HOME_BTN);
        //show the initial home page


        startActivity(new Intent(HouseholdHomeActivity.this, CCUserActivity.class));
        finish();

        rlt_header_details.setVisibility(View.GONE);
        view_header_details.setVisibility(View.GONE);

        rlt_header_status_details.setVisibility(View.GONE);
        view_header_status_details.setVisibility(View.GONE);
        rlt_header.setVisibility(View.VISIBLE);



    }

    public void btn_dashboard_clicked(View view) {

        Log.e(TAG, "Home Button Clicked");

        setUpFooter(DASHBOARD_BTN);

        afterClickTabItem(Constant.FRAG_DASHBOARD, null);

        rlt_header_status_details.setVisibility(View.GONE);
        view_header_status_details.setVisibility(View.GONE);
        title.setText("Dashboard");
        rlt_header_details.setVisibility(View.GONE);
        view_header_details.setVisibility(View.GONE);
        rlt_header.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        // btn_header_application_create.setVisibility(View.GONE);
    }

    public void btn_member_status_clicked(View view) {

        Log.e(TAG, "Home Button Clicked");
        rlt_header_status_details.setVisibility(View.GONE);
        view_header_status_details.setVisibility(View.GONE);
        setUpFooter(MEMBER_STATUS_BTN);
        //show the initial home page
        afterClickTabItem(Constant.FRAG_MEMBER_STATUS, null);
        // checkToGetTicket(false);
        title.setText("Household Members");
        rlt_header_details.setVisibility(View.GONE);
        view_header_details.setVisibility(View.GONE);
        rlt_header.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);


    }

    public void btn_summary_clicked(View view) {

        Log.e(TAG, "Home Button Clicked");
        rlt_header_status_details.setVisibility(View.GONE);
        view_header_status_details.setVisibility(View.GONE);
        setUpFooter(MEMBER_SUMMARY_BTN);
        //show the initial home page
        afterClickTabItem(Constant.FRAG_MEMBER_SUMMARY, null);
        // checkToGetTicket(false);
        title.setText("CC Sending Information");
        rlt_header_details.setVisibility(View.GONE);
        view_header_details.setVisibility(View.GONE);
        rlt_header.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);

    }
    public void ShowText(String name) {
        details_title.setText(name);
    }

    public void openStatusDetails(int position) {
        Fragment fragment= getSupportFragmentManager().findFragmentByTag(CCUserMemberStatusFragment.class.getSimpleName());

        if (fragment instanceof CCUserMemberStatusFragment){
            ((CCUserMemberStatusFragment) fragment).data(position);
        }

    }

    public void showHeaderDetail(String titles) {

        if (titles.equals("main")) {
            rlt_header.setVisibility(View.VISIBLE);
            // / rlt_header_details.setVisibility(View.VISIBLE);
            //view_header_details.setVisibility(View.VISIBLE);

            title.setVisibility(View.VISIBLE);
            rlt_header_details.setVisibility(View.GONE);
            view_header_details.setVisibility(View.GONE);
//            btn_header_application_create.setVisibility(View.GONE);

            rlt_header_status_details.setVisibility(View.GONE);
            view_header_status_details.setVisibility(View.GONE);

        } else if (titles.equals("status")) {
            rlt_header.setVisibility(View.GONE);
            rlt_header_details.setVisibility(View.GONE);
            view_header_details.setVisibility(View.GONE);
            rlt_header_status_details.setVisibility(View.VISIBLE);
            view_header_status_details.setVisibility(View.VISIBLE);
            // btn_header_application_create.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
        } else if (titles.equals("Measurements")) {
            rlt_header.setVisibility(View.GONE);
            rlt_header_details.setVisibility(View.VISIBLE);
            view_header_details.setVisibility(View.VISIBLE);
//            btn_header_application_create.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            rlt_header_status_details.setVisibility(View.GONE);
            view_header_status_details.setVisibility(View.GONE);
        }


    }
}
