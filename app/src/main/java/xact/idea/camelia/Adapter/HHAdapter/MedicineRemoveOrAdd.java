package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xact.idea.camelia.HouseHoldFragment.HHMedicineFragment;
import xact.idea.camelia.HouseHoldFragment.HHMyselfFragment;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;

public class MedicineRemoveOrAdd extends RecyclerView.Adapter<MedicineRemoveOrAdd.TagListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<String> messageEntities;
    String types;

    public MedicineRemoveOrAdd(Activity activity, ArrayList<String> messageEntitie,String type) {
        mActivity = activity;
        messageEntities = messageEntitie;
        types=type;
    }


    @Override
    public MedicineRemoveOrAdd.TagListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_tag_list_layout, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new MedicineRemoveOrAdd.TagListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicineRemoveOrAdd.TagListiewHolder holder, final int position) {

        if (types.equals("D")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.Show();
                }
            });
        }
        else if (types.equals("B")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowBloodPressure();
                }
            });
        }
        else if (types.equals("Heart")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowHeartAttack();
                }
            });
        }
        else if (types.equals("Brain")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowBrainStroke();
                }
            });
        }
        else if (types.equals("Lung")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowLungDisease();
                }
            });
        }
        else if (types.equals("Ashma")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowAshma();
                }
            });
        }
        else if (types.equals("Kidney")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowKidney();
                }
            });
        }
        else if (types.equals("Cancer")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowCancer();
                }
            });
        } else if (types.equals("Mental")){
            holder.mButtonTagList.setText(messageEntities.get(position));
            holder.mButtonSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEntities.remove(position);
                    notifyDataSetChanged();
                    HHMedicineFragment.ShowMentalDisorder();
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        Log.e("dsd", "sd" + messageEntities.size());
        return messageEntities.size();
    }

    public class TagListiewHolder extends RecyclerView.ViewHolder {
        private TextView mButtonTagList = null;
        private ImageButton mButtonSelectImage = null;


        public TagListiewHolder(View itemView) {
            super(itemView);
            mButtonTagList = (TextView) itemView.findViewById(R.id.btn_tag);
            mButtonSelectImage = (ImageButton) itemView.findViewById(R.id.btn_select_image);


        }
    }
}
