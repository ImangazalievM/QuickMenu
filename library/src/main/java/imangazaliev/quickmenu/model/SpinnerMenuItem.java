package imangazaliev.quickmenu.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import imangazaliev.quickmenulibrary.R;
import imangazaliev.quickmenu.interfaces.QuickMenuItem;

public class SpinnerMenuItem implements QuickMenuItem {

    public interface OnItemClickListener {

        void onItemClick(View view, int position, long id);

    }

    private String[] mItems;
    private OnItemClickListener mItemClickListener;

    public SpinnerMenuItem(String[] items) {
        this.mItems = items;
    }

    public SpinnerMenuItem withListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
        return this;
    }

    @Override
    public View getView(Context context, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        Spinner spinner = (Spinner) inflater.inflate(R.layout.spinner_menu_item, parent, false);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, mItems);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(selectedItemView, position, id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        return spinner;
    }
}
