package xact.idea.camelia.Activity.Household;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import io.reactivex.disposables.CompositeDisposable;
import xact.idea.camelia.Activity.CCUserActivity;
import xact.idea.camelia.Activity.CCUserHomeActivity;
import xact.idea.camelia.Activity.LanguageActivity;
import xact.idea.camelia.Activity.LoginActivity;
import xact.idea.camelia.Database.Model.Auth;
import xact.idea.camelia.Fragment.CCUserDashBoardFragment;
import xact.idea.camelia.Fragment.CCUserMemberStatusFragment;
import xact.idea.camelia.Fragment.CCUserMemberSummaryFragment;
import xact.idea.camelia.Helper.LocaleHelper;
import xact.idea.camelia.HouseHoldFragment.HHCCSendingFragment;
import xact.idea.camelia.HouseHoldFragment.HHDashboardFragment;
import xact.idea.camelia.HouseHoldFragment.HHListFragment;
import xact.idea.camelia.HouseHoldFragment.HHMembersFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.Constant;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.SharedPreferenceUtil;

import static xact.idea.camelia.Utils.Utils.hideSoftKeyboard;

public class HouseholdHomeActivity extends AppCompatActivity {
    private TextView tv_home_menu;
    private TextView text_phone_number;
    private TextView tv_dashboard_menu;
    private TextView tv_member_status_menu;
    private TextView tv_summary_menu;
    private TextView tv_user_setup_menus;
    private TextView text_username;
    private TextView text_email;
    private TextView title;
    private ImageView btn_close_drawer;
    public static final int HOME_BTN = 0;
    public static final int DASHBOARD_BTN  = 1;
    public static final int MEMBER_STATUS_BTN  = 2;
    public static final int MEMBER_SUMMARY_BTN  = 3;

    private RelativeLayout rlt_header;
    private RelativeLayout rlt_header_details;
    private RelativeLayout rlt_header_status_details;
    private RelativeLayout relativelayoutLogout;
    private TextView details_title;
    private View view_header_details;
    private View view_header_status_details;
    private ImageButton btn_header_back_;
    private ImageButton btn_header_back;
    private ImageButton btn_header_status_back;
    private ImageButton btn_header_sync;
    private ImageButton btn_header_application_create;
    private LinearLayout linear;
    private RelativeLayout rlt_root;
    private RelativeLayout relativelayoutLanguage;
    private DrawerLayout drawer_layout;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    //IRetrofitApi mService;
    private Context mContext = null;
    private CircleImageView imageView;
    private AppCompatImageView btn_footer_home;
    private AppCompatImageView btn_footer_dashboard;
    private AppCompatImageView btn_footer_member_status;
    private AppCompatImageView btn_footer_summary;
    private AppCompatImageView btn_footer_setup_user;
    private final String TAG = getClass().getSimpleName();
    private FragmentManager mFragManager;
    private FragmentTransaction fragTransaction = null;
    String language="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_home);
        CorrectSizeUtil.getInstance(this).correctSize();
        CorrectSizeUtil.getInstance(this).correctSize(findViewById(R.id.rlt_root));
        initView();
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION");

         language=SharedPreferenceUtil.getLanguage(HouseholdHomeActivity.this);
        setFooter(sessionId);
    }
    private void initView() {
        text_phone_number = findViewById(R.id.text_phone_number);
        rlt_root = findViewById(R.id.rlt_root);
        relativelayoutLanguage = findViewById(R.id.relativelayoutLanguage);
        relativelayoutLogout = findViewById(R.id.relativelayoutLogout);
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
        imageView = findViewById(R.id.imageView);
        text_username = findViewById(R.id.text_username);
        text_email = findViewById(R.id.text_email);
        Paper.init(this);
        String language=SharedPreferenceUtil.getLanguage(HouseholdHomeActivity.this);
        Paper.book().write("language",language);
        updateView((String)Paper.book().read("language"));
        btn_header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);

            }
        });
        Auth auth = Common.authRepository.getAuthNo(SharedPreferenceUtil.getUserRole(HouseholdHomeActivity.this));
        Glide.with(this).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
        text_username.setText(auth.fullname);
        text_email.setText(auth.email);
        text_phone_number.setText(auth.role_name);
//        rlt_root.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get the input method manager
//
//            }
//        });
        btn_close_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
                drawer_layout.closeDrawer(GravityCompat.START);
            }
        });

        btn_header_back_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                 inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
             backForDetails();
            }
        });

        relativelayoutLogout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseholdHomeActivity.this, LoginActivity.class);
                intent.putExtra("EXTRA_SESSION", "dashboard");
                startActivity(intent);
                finishAffinity();
            }
        });
        relativelayoutLanguage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                showInfoDialog();

            }
        });
    }
    public  void showInfoDialog() {

        final CustomDialog infoDialog = new CustomDialog(HouseholdHomeActivity.this, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_language, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        RelativeLayout relative_english = infoDialog.findViewById(R.id.relative_english);
        RelativeLayout relative_bangla = infoDialog.findViewById(R.id.relative_bangla);
        Button create = infoDialog.findViewById(R.id.create);

        CorrectSizeUtil.getInstance(HouseholdHomeActivity.this).correctSize(main_root);
        relative_english.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                SharedPreferenceUtil.saveShared(HouseholdHomeActivity.this, SharedPreferenceUtil.LANG,  "en");
                Common.blockRepository.updateLanguage("en");
                Common.bloodGroupRepository.updateLanguage("en");
                Common.districtRepository.updateLanguage("en");
                Common.divisionRepository.updateLanguage("en");
                Common.femaleRepository.updateLanguage("en");
                Common.maritialStatusRepository.updateLanguage("en");
                Common.occupationRepository.updateLanguage("en");
                Common.studyClassRepository.updateLanguage("en");
                Common.unionRepository.updateLanguage("en");
                Common.upazilaRepository.updateLanguage("en");
                Common.wardRepository.updateLanguage("en");
                finish();
                startActivity(getIntent());
                infoDialog.dismiss();

            }
        });
        relative_bangla.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                SharedPreferenceUtil.saveShared(HouseholdHomeActivity.this, SharedPreferenceUtil.LANG,  "bn");
                Common.blockRepository.updateLanguage("bn");
                Common.bloodGroupRepository.updateLanguage("bn");
                Common.districtRepository.updateLanguage("bn");
                Common.divisionRepository.updateLanguage("bn");
                Common.femaleRepository.updateLanguage("bn");
                Common.maritialStatusRepository.updateLanguage("bn");
                Common.occupationRepository.updateLanguage("bn");
                Common.studyClassRepository.updateLanguage("bn");
                Common.unionRepository.updateLanguage("bn");
                Common.upazilaRepository.updateLanguage("bn");
                Common.wardRepository.updateLanguage("bn");
                finish();
                startActivity(getIntent());
                infoDialog.dismiss();

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
    private void updateView(String language) {
        Context context1= LocaleHelper.setLocale(this,language);
        Resources resources1= context1.getResources();
        tv_dashboard_menu.setText(resources1.getString(R.string.dashboard));
        tv_home_menu.setText(resources1.getString(R.string.home));
        tv_member_status_menu.setText(resources1.getString(R.string.members));
        tv_summary_menu.setText(resources1.getString(R.string.cc_sending));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
        backForDetails();
    }
    public void backForDetails() {
        // Toast.makeText(mContext, "dsfdsf", Toast.LENGTH_SHORT).show();
        Fragment f = getVisibleFragment();
        Log.e("frag111", "frag" + f);
        if (f != null) {

            if (f instanceof HHListFragment) {


                int handle = ((HHListFragment) f).handels();

                if (handle==2){
                    showHeaderDetail("main");
                }

            }
        }
    }
    private void setFooter(String value) {
        String language=SharedPreferenceUtil.getLanguage(HouseholdHomeActivity.this);
        switch (value) {

            case "dashboard":

                Context context1= LocaleHelper.setLocale(this,language);
                Resources resources1= context1.getResources();
                title.setText(resources1.getString(R.string.dashboard));
                btn_footer_dashboard.setSelected(true);
                tv_dashboard_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_DASHBOARD, null);


                break;
            case "status":
                Context context2= LocaleHelper.setLocale(this,language);
                Resources resources2= context2.getResources();
                title.setText(resources2.getString(R.string.household_member));
                btn_footer_member_status.setSelected(true);
                tv_member_status_menu.setSelected(true);
                afterClickTabItem(Constant.FRAG_MEMBER_STATUS, null);
                break;
            case "summary":
                Context context3= LocaleHelper.setLocale(this,language);
                Resources resources3= context3.getResources();
                title.setText(resources3.getString(R.string.cc_information));
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
                newFrag = new HHListFragment();


                break;

            case Constant.FRAG_MEMBER_SUMMARY:


                newFrag = new HHCCSendingFragment();


                break;

            default:
                break;
        }


        // param 1: container id, param 2: new fragment, param 3: fragment id
        fragTransaction.replace(R.id.main_container, newFrag, newFrag.getClass().getSimpleName());
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


        startActivity(new Intent(HouseholdHomeActivity.this, HouseHoldActivity.class));
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
        Context context= LocaleHelper.setLocale(this,language);
        Resources resources= context.getResources();
        title.setText(resources.getString(R.string.dashboard));
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
        Context context= LocaleHelper.setLocale(this,language);
        Resources resources= context.getResources();
        title.setText(resources.getString(R.string.household_member));
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
        Context context= LocaleHelper.setLocale(this,language);
        Resources resources= context.getResources();
        title.setText(resources.getString(R.string.cc_information));
        rlt_header_details.setVisibility(View.GONE);
        view_header_details.setVisibility(View.GONE);
        rlt_header.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);

    }
    public void ShowText(String name) {
        details_title.setText(name);
    }

//    public void openStatusDetails(int position) {
//        Fragment fragment= getSupportFragmentManager().findFragmentByTag(CCUserMemberStatusFragment.class.getSimpleName());
//
//        if (fragment instanceof CCUserMemberStatusFragment){
//            ((CCUserMemberStatusFragment) fragment).data(position);
//        }
//
//    }

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
