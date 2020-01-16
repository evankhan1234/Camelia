package xact.idea.camelia.Adapter.HHAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;
import xact.idea.camelia.Utils.CorrectSizeUtil;
import xact.idea.camelia.Utils.SpinnerFilterMedicine;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.PlaceTagListiewHolder> implements Filterable {


    SpinnerFilterMedicine filter;
    private Activity mActivity = null;
    public List<String> messageEntities;
    UccMemberClickListener bookItemInterfaces;


    public MedicineListAdapter(Activity activity, List<String> messageEntitie, UccMemberClickListener bookItemInterface) {
        mActivity = activity;
        this.messageEntities = messageEntitie;
        this.bookItemInterfaces = bookItemInterface;

    }


    @Override
    public MedicineListAdapter.PlaceTagListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_medicine_item, null);
        CorrectSizeUtil.getInstance(mActivity).correctSize(view);


        return new MedicineListAdapter.PlaceTagListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MedicineListAdapter.PlaceTagListiewHolder holder, final int position) {
        // UserList messageEntitie= messageEntities.get(position);
        holder.text1.setText(messageEntities.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookItemInterfaces.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {

        return messageEntities.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SpinnerFilterMedicine(messageEntities, this);
        }
        return filter;
    }

    public class PlaceTagListiewHolder extends RecyclerView.ViewHolder {

        private TextView text1;



        public PlaceTagListiewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.text1);



        }
    }




}