package xact.idea.camelia.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

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

        Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.backwhite)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.img_avatar.setImageDrawable(resource);
                    }
                });


        String agent_no = "<b><font color=#000 >Agent No :  </font></b> <font color=#444444> camella041</font>";
        String name = "<b><font color=#000 >Agent No :  </font></b> <font color=#444444> Rajia Sultana</font>";
        String code = "<b><font color=#000 >Code:  </font></b> <font color=#444444>cfb9894-a</font>";
        String member_id = "<b><font color=#000 >Member Id :  </font></b> <font color=#444444> 131415586325</font>";
        String village = "<b><font color=#000 >Agent No :  </font></b> <font color=#444444> Bsahura</font>";
        String date = "<b><font color=#000 >Date :  </font></b> <font color=#444444> 30-09-2019</font>";
        String block = "<b><font color=#000 >Block :  </font></b> <font color=#444444> E</font>";
        holder.text_name.setText(Html.fromHtml(name));
        holder.text_agent.setText(Html.fromHtml(agent_no));
        holder.text_phone_number.setText(Html.fromHtml(code));
        holder.text_member_id.setText(Html.fromHtml(member_id));
        holder.text_date.setText(Html.fromHtml(date));
        holder.text_village.setText(Html.fromHtml(village));
        holder.text_block.setText(Html.fromHtml(block));
        holder.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uccMemberClickListener.postion(memberMyself.get(position).id,memberMyself.get(position).MobileNumber);
                //Toast.makeText(mActivity, messageEntities.get(position).FullName, Toast.LENGTH_SHORT).show();
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


        }
    }
}