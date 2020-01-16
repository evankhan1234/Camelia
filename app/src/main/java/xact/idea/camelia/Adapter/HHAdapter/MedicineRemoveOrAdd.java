package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class MedicineRemoveOrAdd extends RecyclerView.Adapter<MedicineRemoveOrAdd.TagListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<String> messageEntities;

    public MedicineRemoveOrAdd(Activity activity, ArrayList<String> messageEntitie) {
        mActivity = activity;
        messageEntities = messageEntitie;

    }


    @Override
    public MedicineRemoveOrAdd.TagListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_tag_list_layout, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new MedicineRemoveOrAdd.TagListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicineRemoveOrAdd.TagListiewHolder holder, final int position) {

        holder.mButtonTagList.setText(messageEntities.get(position));
        holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEntities.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        Log.e("dsd", "sd" + messageEntities.size());
        return messageEntities.size();
    }

    public class TagListiewHolder extends RecyclerView.ViewHolder {
        private TextView mButtonTagList = null;
        private AppCompatImageButton mButtonSelectImage = null;


        public TagListiewHolder(View itemView) {
            super(itemView);
            mButtonTagList = (TextView) itemView.findViewById(R.id.btn_tag);
            mButtonSelectImage = (AppCompatImageButton) itemView.findViewById(R.id.btn_select_image);


        }
    }
}
