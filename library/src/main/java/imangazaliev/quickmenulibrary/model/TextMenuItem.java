package imangazaliev.quickmenulibrary.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import imangazaliev.quickmenulibrary.R;
import imangazaliev.quickmenulibrary.interfaces.QuickMenuItem;

/**
 * Created by Mahach Imangazaliev on 18.01.2016
 */
public class TextMenuItem implements QuickMenuItem {

    private String mText;
    private int mTextColor;
    private Drawable mBackground;
    private int mGravity;
    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    public TextMenuItem(String text) {
        this.mText = text;
        mTextColor = Color.BLACK;
        mBackground = null;
        mGravity = Gravity.CENTER;
    }

    public TextMenuItem withTextColor(int textColor) {
        this.mTextColor = textColor;
        return this;
    }

    public TextMenuItem withTextBackground(Drawable background) {
        this.mBackground = background;
        return this;
    }

    public TextMenuItem withGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    public TextMenuItem withMargins(int left, int top, int right, int bottom) {
        this.mMarginLeft = left;
        this.mMarginTop = top;
        this.mMarginRight = right;
        this.mMarginBottom = bottom;
        return this;
    }

    @Override
    public View getView(Context context, ViewGroup parent) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);

        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.text_menu_item, parent, false);
        textView.setText(mText);
        textView.setTextColor(mTextColor);
        textView.setBackgroundDrawable(mBackground);
        textView.setGravity(mGravity);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}