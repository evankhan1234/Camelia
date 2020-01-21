package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xact.idea.camelia.Database.Model.MemberMyself;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class HHMemberListAdapter extends RecyclerView.Adapter<HHMemberListAdapter.CCDashboardListiewHolder> {


    private Activity mActivity = null;
    int row_index = 0;
    List<MemberMyself> memberMyselfes;
    public HHMemberListAdapter(Activity activity, List<MemberMyself> memberMyselfe) {
        mActivity = activity;
        memberMyselfes=memberMyselfe;
    }


    @Override
    public HHMemberListAdapter.CCDashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_member_list_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new HHMemberListAdapter.CCDashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HHMemberListAdapter.CCDashboardListiewHolder holder, final int position) {

        holder.text_name.setText(memberMyselfes.get(position).FullName);
        holder.text_phone.setText(memberMyselfes.get(position).MobileNumber);

        if (memberMyselfes.get(position).GenderId==1){
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
    }

    @Override
    public int getItemCount() {
        return memberMyselfes.size();
    }

    public class CCDashboardListiewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_avatar;
        private Button btn_department;
        private TextView text_name;
        private TextView text_phone;


        public CCDashboardListiewHolder(View itemView) {
            super(itemView);
            img_avatar=itemView.findViewById(R.id.img_avatar);
            text_name=itemView.findViewById(R.id.text_name);
            text_phone=itemView.findViewById(R.id.text_phone);
        }
    }
}