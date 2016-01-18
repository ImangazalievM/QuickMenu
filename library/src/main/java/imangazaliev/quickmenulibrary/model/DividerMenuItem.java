package imangazaliev.quickmenulibrary.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import imangazaliev.quickmenulibrary.interfaces.QuickMenuItem;

public class DividerMenuItem implements QuickMenuItem {

    private int mDividerColor;
    private int mDividerWidth;
    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    public DividerMenuItem(Context context) {
        mDividerColor = Color.LTGRAY;
        mDividerWidth = (int) context.getResources().getDisplayMetrics().density;
        mMarginLeft = mMarginTop = mMarginRight = mMarginBottom = 0;

    }

    public DividerMenuItem withColor(int color) {
        this.mDividerColor = color;
        return this;
    }

    public DividerMenuItem withMargins(int left, int top, int right, int bottom) {
        this.mMarginLeft = left;
        this.mMarginTop = top;
        this.mMarginRight = right;
        this.mMarginBottom = bottom;
        return this;
    }

    public DividerMenuItem withWidth(int width) {
        this.mDividerWidth = width;
        return this;
    }

    @Override
    public View getView(Context context, ViewGroup parent) {
        View divider = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mDividerWidth);
        layoutParams.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
        divider.setLayoutParams(layoutParams);
        divider.setBackgroundDrawable(new ColorDrawable(mDividerColor));
        divider.setEnabled(false);
        return divider;
    }
}
