package xact.idea.camelia.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xact.idea.camelia.Adapter.HHAdapter.MedicineListAdapter;
import xact.idea.camelia.Interface.MedicineInterface;
import xact.idea.camelia.Interface.UccMemberClickListener;
import xact.idea.camelia.R;

public class SpinnerForMedicine implements Filterable {
    List<String> items;
    Activity context;
    String dTitle, closeTitle = "Close";
    AlertDialog alertDialog;
    int pos;
    int style;
    boolean cancellable = false;
    boolean showKeyboard = false;
    SpinnerFilterMedicine filter;
    ArrayAdapter adapter;
    MedicineListAdapter mAdapters;
    String values;
    String types;
    MedicineInterface uccMemberClickListener;

    public SpinnerForMedicine(Activity activity, List<String> items, String dialogTitle, MedicineInterface uccMemberClickListeners,String type) {
        this.items = items;
        this.context = activity;
        this.dTitle = dialogTitle;
        this.types = type;
        this.uccMemberClickListener = uccMemberClickListeners;
    }


    public void showSpinerDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        View v = context.getLayoutInflater().inflate(R.layout.layout_spinner, null);
        CorrectSizeUtil.getInstance(context).correctSize(v);

        TextView rippleViewClose = (TextView) v.findViewById(R.id.close);
        TextView title = (TextView) v.findViewById(R.id.spinerTitle);
        rippleViewClose.setText(closeTitle);
        title.setText(dTitle);
        // final ListView listView = (ListView) v.findViewById(R.id.list);
        final EditText searchBox = (EditText) v.findViewById(R.id.searchBox);
        if (isShowKeyboard()) {
            showKeyboard(searchBox);
        }
        searchBox.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
        adapter = new ArrayAdapter<>(context, R.layout.layout_medicine_item, items);
        RecyclerView rcl_this_customer_list = v.findViewById(R.id.rcl_this_customer_list);

        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_this_customer_list.setLayoutManager(lm);
        mAdapters = new MedicineListAdapter(context, items, uccMemberClickListener,types);

        rcl_this_customer_list.setAdapter(mAdapters);

        // listView.setAdapter(adapter);
        adb.setView(v);
        alertDialog = adb.create();
        alertDialog.getWindow().getAttributes().windowAnimations = style;//R.style.DialogAnimations_SmileWindow;


        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = searchBox.getText().toString();

                mAdapters.getFilter().filter(searchBox.getText().toString());
            }
        });

        rippleViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSpinerDialog();
            }
        });
        try {
            alertDialog.setCancelable(isCancellable());
            alertDialog.setCanceledOnTouchOutside(isCancellable());
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void closeSpinerDialog() {
        hideKeyboard();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    private void showKeyboard(final EditText ettext) {
        ettext.requestFocus();
        ettext.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                   keyboard.showSoftInput(ettext, 0);
                               }
                           }
                , 200);
    }

    private boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    private boolean isShowKeyboard() {
        return showKeyboard;
    }

    public void setShowKeyboard(boolean showKeyboard) {
        this.showKeyboard = showKeyboard;
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SpinnerFilterMedicine(items, mAdapters);
            Toast.makeText(context, "xvx", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "xvx", Toast.LENGTH_SHORT).show();
        }
        return filter;
    }
}

