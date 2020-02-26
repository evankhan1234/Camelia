package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.Common;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.CustomDialog;
import xact.idea.camelia.Utils.Utils;

public class CCIncompleteStatusAdapter extends RecyclerView.Adapter<CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder> {


    private Activity mActivity = null;
    //  private List<Department> messageEntities;

    private MedicineInterface uccMemberClickListener;
    int row_index = 0;
    List<MemberMyself> memberMyself;

    public CCIncompleteStatusAdapter(Activity activity, List<MemberMyself> memberMyselfes,MedicineInterface uccMemberClickListeners) {
        mActivity = activity;
        uccMemberClickListener=uccMemberClickListeners;
        //Toast.makeText(mActivity, "sdfsdf", Toast.LENGTH_SHORT).show();
        //messageEntities = messageEntitie;
        //mClick = mClicks;
        memberMyself=memberMyselfes;
        // clickInterface=clickInterfaces;
    }


    @Override
    public CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_status_list, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CCIncompleteStatusAdapter.CCIncompleteStatusListiewHolder holder, final int position) {



        //    Log.e("Evan", "SDfs" + messageEntities.get(position));
        //  holder.btn_department.setHint(messageEntities.get(position).DepartmentName);



        if (memberMyself.get(position).GenderId==1){
            Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            holder.img_avatar.setImageDrawable(resource);
                        }
                    });
        }
        else{
            Glide.with(mActivity).load("https://previews.123rf.com/images/thesomeday123/thesomeday1231712/thesomeday123171200008/91087328-default-avatar-profile-icon-for-female-grey-photo-placeholder-illustrations-vector.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            holder.img_avatar.setImageDrawable(resource);
                        }
                    });
        }
        String agent_no = "<b><font color=#000 >Patient Name :  </font></b> <font color=#444444> "+memberMyself.get(position).FullName+"</font>";
        String name = "<b><font color=#000 >Member ID :  </font></b> <font color=#444444>"+memberMyself.get(position).MemberId+"</font>";
        String code = "<b><font color=#000 >Khana ID:  </font></b> <font color=#444444>"+memberMyself.get(position).UniqueId+"</font>";
        String member_id = "<b><font color=#000 >Contact No :  </font></b> <font color=#444444>"+memberMyself.get(position).MobileNumber+"</font>";
        String village = "<b><font color=#000 >Visit Date :  </font></b> <font color=#444444>"+memberMyself.get(position).VisitDate+"</font>";
        String date = "<b><font color=#000 >Ref.CC :  </font></b> <font color=#444444>N/A</font>";
        String block = "<b><font color=#000 >Condition :  </font></b> <font color=#444444> N/A</font>";
        holder.text_name.setText(Html.fromHtml(name));
        holder.text_agent.setText(Html.fromHtml(agent_no));
        holder.text_phone_number.setText(Html.fromHtml(code));
        holder.text_member_id.setText(Html.fromHtml(member_id));
        holder.text_date.setText(Html.fromHtml(date));
        holder.text_village.setText(Html.fromHtml(village));
        holder.text_block.setText(Html.fromHtml(block));
        if(Utils.isEmpty(memberMyself.get(position).VisitDate)){
            holder.text_visit.setText("Visit");
        }
        else{
            holder.text_visit.setText("Visited");
        }
        holder.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListener.postion(memberMyself.get(position).id,memberMyself.get(position).MemberId);
                //Toast.makeText(mActivity, messageEntities.get(position).FullName, Toast.LENGTH_SHORT).show();
            }
        });
        holder.text_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.text_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialog(mActivity,memberMyself.get(position).MemberId);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Log.e("evan", "sd" + messageEntities.size());
        return memberMyself.size();
    }

    public class CCIncompleteStatusListiewHolder extends RecyclerView.ViewHolder {

        private ImageView img_avatar;
        private ImageView img_next;
        private TextView text_agent;
        private TextView text_name;
        private TextView text_phone_number;
        private TextView text_member_id;
        private TextView text_village;
        private TextView text_date;
        private TextView text_block;
        private TextView text_profile;
        private TextView text_visit;


        public CCIncompleteStatusListiewHolder(View itemView) {
            super(itemView);

            img_next = itemView.findViewById(R.id.img_next);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            text_agent = itemView.findViewById(R.id.text_agent);
            text_name = itemView.findViewById(R.id.text_name);
            text_phone_number = itemView.findViewById(R.id.text_phone_number);
            text_member_id = itemView.findViewById(R.id.text_member_id);
            text_village = itemView.findViewById(R.id.text_village);
            text_date = itemView.findViewById(R.id.text_date);
            text_block = itemView.findViewById(R.id.text_block);
            text_profile = itemView.findViewById(R.id.text_profile);
            text_visit = itemView.findViewById(R.id.text_visit);


        }
    }
    public  void showInfoDialog(final Context mContext, final String member) {

        final CustomDialog infoDialog = new CustomDialog(mContext, R.style.CustomDialogTheme);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.layout_confirm, null);

        infoDialog.setContentView(v);
        infoDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout main_root = infoDialog.findViewById(R.id.main_root);
        Button btn_yes = infoDialog.findViewById(R.id.btn_ok);
        Button btn_no = infoDialog.findViewById(R.id.btn_cancel);

        CorrectSizeUtil.getInstance((Activity) mContext).correctSize(main_root);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(date);
                Common.memberMyselfRepository.updateReciver(currentDate,member);
                infoDialog.dismiss();

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }
}