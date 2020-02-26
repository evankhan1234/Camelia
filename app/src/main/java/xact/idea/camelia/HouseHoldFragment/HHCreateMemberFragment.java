package xact.idea.camelia.HouseHoldFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.View.CustomViewPager;
import xact.idea.camelia.View.SwipeDirection;
import xact.idea.camelia.ViewPager.ViewPagerAdapter;


public class HHCreateMemberFragment extends Fragment {
    Activity mActivity;
    CorrectSizeUtil correctSizeUtil;
    View view;
    public static CustomViewPager vpg_home;
    public static ViewPagerAdapter mPagerAdapter = null;
    LinearLayout lnl_category;
    HorizontalScrollView horizontalScrollView;
    public static Button btn_back;
    static Button btn_next;
    String[] categories;
    String uniquKey;
    String frag;
    String update;
    Message message = null;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hhcreate_member, container, false);
        mActivity=getActivity();
        correctSizeUtil= correctSizeUtil.getInstance(getActivity());
        correctSizeUtil.setWidthOriginal(1080);
        correctSizeUtil.correctSize(view);
        setRetainInstance(true);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            uniquKey = bundle.getString("Id", "");
            frag = bundle.getString("frag", "");
            update = bundle.getString("update", "");
            Log.e("UniqueId","uniquKey"+uniquKey);
            Log.e("update","update"+update);
        }
        else{
            frag="";
        }
        initView();
        // display();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        lnl_category = view.findViewById(R.id.lnl_category);
        btn_next = view.findViewById(R.id.btn_next);
        btn_back = view.findViewById(R.id.btn_back);
        vpg_home = view. findViewById(R.id.vpg_home_group);
        vpg_home.setAllowedSwipeDirection(SwipeDirection.none);
        try {
            categories = getResources().getStringArray(R.array.group_step);
            for (int i = 0; i < lnl_category.getChildCount(); i++) {
                LinearLayout rlt = (LinearLayout) lnl_category.getChildAt(i);
                TextView step_no =  rlt.findViewById(R.id.step_no);
                final TextView title = rlt.findViewById(R.id.title);
                step_no.setText("" + (i + 1));
                title.setText("" + categories[i]);
                // final int category = i;

            }
        } catch (Exception e) {
            Log.e("Exception", "" + e.getMessage());
        }
        initPager();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vpg_home.getCurrentItem() == 0) {
                    message = new Message();
                    message.what = 0;
                    HHMyselfFragment.handler.sendMessage(message);
                    //btn_back.setVisibility(View.VISIBLE);
                } else if (vpg_home.getCurrentItem() == 1) {
                    message = new Message();
                    message.what = 1;
                    HHMedicineFragment.handler.sendMessage(message);
                   // btn_back.setVisibility(View.VISIBLE);
                    // vpg_home.setCurrentItem(vpg_home.getCurrentItem() + 1);
                } else if (vpg_home.getCurrentItem() == 2) {
                    message = new Message();
                    message.what = 2;
                    HHHabitFragment.handler.sendMessage(message);
                  //  btn_back.setVisibility(View.VISIBLE);
                }
//                else if (vpg_home.getCurrentItem() == 3) {
//                    btn_back.setVisibility(View.VISIBLE);
//                } else if (vpg_home.getCurrentItem() == 4) {
//
//                    btn_back.setVisibility(View.VISIBLE);
//                }

                //btn_back.setVisibility(View.VISIBLE);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = new Message();
                if (vpg_home.getCurrentItem() == 1) {
                    message.what = 0;
                    HHMedicineFragment.handler.sendMessage(message);

                    btn_back.setVisibility(View.GONE);
                    // vpg_home.setCurrentItem(vpg_home.getCurrentItem() + 1);
                } else if (vpg_home.getCurrentItem() == 2) {
                    message.what = 1;
                    HHHabitFragment.handler.sendMessage(message);
                    btn_back.setVisibility(View.VISIBLE);
                }
//                else if (vpg_home.getCurrentItem() == 3) {
//
//                    btn_back.setVisibility(View.VISIBLE);
//                } else if (vpg_home.getCurrentItem() == 4) {
//
//                    btn_back.setVisibility(View.VISIBLE);
//                }
                vpg_home.setCurrentItem(vpg_home.getCurrentItem() - 1);
            }
        });
        setStepValue(0);
    }
    public static void nextPage(int pos) {

        vpg_home.setCurrentItem(pos);
    }

    public static void nextPages(int pos) {
        btn_next.setText("Complete");
        vpg_home.setCurrentItem(pos);
    }

    public static void prevPage(int pos) {
        btn_next.setText("Next");
        vpg_home.setCurrentItem(pos);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setStepValue(int pos) {
        try {


            categories = getResources().getStringArray(R.array.group_step);
            for (int i = 0; i < lnl_category.getChildCount(); i++) {
                LinearLayout rlt = (LinearLayout) lnl_category.getChildAt(i);
                TextView step_no = (TextView) rlt.findViewById(R.id.step_no);
                final TextView title = rlt.findViewById(R.id.title);
                step_no.setText("" + (i + 1));
                title.setText("" + categories[i]);
                if (pos < i) {
                    step_no.setTextColor(getResources().getColor(R.color.gray_black));
                    title.setTextColor(getResources().getColor(R.color.gray_black));
                    step_no.setBackground(getResources().getDrawable(R.drawable.white_circle_drawable));
                } else {
                    title.setTextColor(getResources().getColor(R.color.black));
                    step_no.setTextColor(getResources().getColor(R.color.white));
                    step_no.setBackground(getResources().getDrawable(R.drawable.green_circle_drawable));
                }
                if (pos == i) {
                    lnl_category.getChildAt(pos).post(new Runnable() {
                        @Override
                        public void run() {
                            initRunner();
                        }
                    });
                }

            }
        } catch (Exception e) {
            Log.e("Exception", "" + e.getMessage());
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vpg_home.setAdapter(null);
    }
    private void initPager() {
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPagerAdapter.addFragment(new HHMyselfFragment(uniquKey,update), "");
     //   mPagerAdapter.addFragment(new HHFamilyDiseaseFragment(), "");
        mPagerAdapter.addFragment(new HHMedicineFragment(update), "");
        mPagerAdapter.addFragment(new HHHabitFragment(frag,update), "");
     //   mPagerAdapter.addFragment(new HHReasonFragment(), "");
        // mPagerAdapter.addFragment(new ReviewFragment(), "");
       // vpg_home.beginFakeDrag();
        vpg_home.setOffscreenPageLimit(3);
        // vpg_home.setOffscreenPageLimit(5);
        vpg_home.setAdapter(mPagerAdapter);

//
        vpg_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (mArrCategoryWidthOffset.size() > 0) {
//                    if (position < mArrCategoryWidth.size() - 1) {
//                        int left = mArrCategoryWidthOffset.get(position) + (int) (mArrCategoryWidth.get(position) * positionOffset);
//
//                        int width = (int) Math.ceil((mArrCategoryWidth.get(position) * (1 - positionOffset))) + (int) Math.ceil((mArrCategoryWidth.get(position + 1) * positionOffset));
//                        setRunnerStateWidth(width, left);
//                    } else {
//                        // ignore
//                    }
//                } else {
//                    // ignore
//                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int position) {

                Log.e("onPageSelected", "-- " + position);
                setStepValue(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.e("state", "-- " + state);
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    mPagerAdapter.getItem(vpg_home.getCurrentItem());
//                }
            }
        });

    }

    public void showFragment(int fragment) {
        vpg_home.setCurrentItem(fragment);

    }
    private void initRunner() {
        initOffsetState();
        int currentItem = vpg_home.getCurrentItem();
        if (vpg_home.getCurrentItem() != 0) {
            int left = mArrCategoryWidthOffset.get(currentItem);
            int width = mArrCategoryWidth.get(currentItem);
            setRunnerStateWidth(width, left);
        } else {
            // run at first onCreate()
            int width = lnl_category.getChildAt(0).getWidth();
            setRunnerStateWidth(width, 0); // start at zero
        }

    }

    private void setRunnerStateWidth(int width, int left) {
        int scrWidth = horizontalScrollView.getWidth();
        if (width + left > scrWidth + horizontalScrollView.getScrollX()) {
            horizontalScrollView.scrollTo(width + left - scrWidth, 0); // scroll content to left
        }
        if (left < horizontalScrollView.getScrollX()) {
            horizontalScrollView.scrollTo(left, 0); // scroll content to right
        }
    }

    private ArrayList<Integer> mArrCategoryWidthOffset = new ArrayList();
    private ArrayList<Integer> mArrCategoryWidth = new ArrayList();

    private void initOffsetState() {
        mArrCategoryWidth.clear();
        mArrCategoryWidthOffset.clear();
        mArrCategoryWidthOffset.add(0); // first offset is zero
        for (int i = 0; i < lnl_category.getChildCount(); i++) {
            LinearLayout rlt = (LinearLayout) lnl_category.getChildAt(i);
            int width = rlt.getWidth();
            mArrCategoryWidth.add(width);
            if (i == 0) {
                mArrCategoryWidthOffset.add(width);
            } else {
                mArrCategoryWidthOffset.add(width + mArrCategoryWidthOffset.get(mArrCategoryWidthOffset.size() - 1));
            }
        }

        if (mArrCategoryWidth.get(0) == 0) {
            mArrCategoryWidth.clear();
            mArrCategoryWidthOffset.clear();
            mArrCategoryWidthOffset.add(0); // first offset is zero
            for (int i = 0; i < lnl_category.getChildCount(); i++) {
                LinearLayout rlt = (LinearLayout) lnl_category.getChildAt(i);
                rlt.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int width = rlt.getMeasuredWidth();
                mArrCategoryWidth.add(width);
                if (i == 0) {
                    mArrCategoryWidthOffset.add(width);
                } else {
                    mArrCategoryWidthOffset.add(width + mArrCategoryWidthOffset.get(mArrCategoryWidthOffset.size() - 1));
                }
            }
        } else {
            // ignore
        }
    }
}
