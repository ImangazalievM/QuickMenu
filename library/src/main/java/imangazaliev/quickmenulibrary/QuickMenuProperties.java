package imangazaliev.quickmenulibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Mahach Imangazaliev on 15.01.2016
 */
public class QuickMenuProperties {

    public enum WidthMode {PIXELS, PERCENTAGES}

    private final WidthMode mWidthMode;
    private final int mMenuWidth;
    private final int mMenuWidthInPercentages;
    private final int mMarginTop, mMarginLeft, mMarginBottom, mMarginRight;
    private final Drawable mMenuBackground;
    private final Drawable mLayoutBackground;
    private final boolean mCancelOnTouchOutside;

    private QuickMenuProperties(Builder builder) {
        this.mWidthMode = builder.mWidthMode;
        this.mMenuWidth = builder.mMenuWidth;
        this.mMenuWidthInPercentages = builder.mMenuWidthInPercentages;
        this.mMarginLeft = builder.mMarginLeft;
        this.mMarginTop = builder.mMarginTop;
        this.mMarginRight = builder.mMarginRight;
        this.mMarginBottom = builder.mMarginBottom;
        this.mMenuBackground = builder.mMenuBackground;
        this.mLayoutBackground = builder.mLayoutBackground;
        this.mCancelOnTouchOutside = builder.mCancelOnTouchOutside;
    }

    public WidthMode getWidthMode() {
        return mWidthMode;
    }

    public int getMenuWidth() {
        return mMenuWidth;
    }

    public int getMenuWidthInPercentages() {
        return mMenuWidthInPercentages;
    }

    public int getMarginTop() {
        return mMarginTop;
    }

    public int getMarginBottom() {
        return mMarginBottom;
    }

    public int getMarginLeft() {
        return mMarginLeft;
    }

    public int getMarginRight() {
        return mMarginRight;
    }

    public Drawable getMenuBackground() {
        return mMenuBackground;
    }

    public Drawable getLayoutBackground() {
        return mLayoutBackground;
    }

    public boolean getCancelOnTouchOutside() {
        return mCancelOnTouchOutside;
    }

    public static class Builder {

        /**  */
        private WidthMode mWidthMode;
        /**
         * Ширина меню в пикселях
         */
        private int mMenuWidth;
        /**
         * Ширина меню в процентах
         */
        private int mMenuWidthInPercentages;
        /**
         * Отступы меню
         */
        private int mMarginTop, mMarginLeft, mMarginBottom, mMarginRight;
        /**
         * Фон меню
         */
        private Drawable mMenuBackground;
        /**
         * Фон лейаута, в котором находится меню
         */
        private Drawable mLayoutBackground;
        /**
         * Меню закрывается при нажатии за зону самого меню
         */
        private boolean mCancelOnTouchOutside;

        public Builder(Context context) {
            mWidthMode = WidthMode.PIXELS;
            mMenuWidth = context.getResources().getDimensionPixelSize(R.dimen.quick_menu_width);
            mMarginTop = mMarginLeft = mMarginBottom = mMarginRight = context.getResources().getDimensionPixelSize(R.dimen.quick_menu_margin);
            mMenuBackground = context.getResources().getDrawable(R.drawable.quick_menu_bg);
            mLayoutBackground = new ColorDrawable(Color.parseColor("#99000000"));
            mCancelOnTouchOutside = false;
        }

        public Builder withWidth(int width) {
            if (width < 0) {
                throw new RuntimeException("Number must be greater than or equal to zero");
            }
            this.mMenuWidth = width;
            this.mWidthMode = WidthMode.PIXELS;
            return this;
        }

        public Builder withWidthInPercentages(int widthInPercentages) {
            if (widthInPercentages < 0 || widthInPercentages > 100) {
                throw new RuntimeException("Number must be between 0 and 100");
            }
            this.mMenuWidthInPercentages = widthInPercentages;
            this.mWidthMode = WidthMode.PERCENTAGES;
            return this;
        }

        public Builder withMargins(int left, int top, int right, int bottom) {
            this.mMarginLeft = left;
            this.mMarginTop = top;
            this.mMarginRight = right;
            this.mMarginBottom = bottom;
            return this;
        }

        public Builder withBackground(Drawable background) {
            this.mMenuBackground = background;
            return this;
        }

        public Builder withLayoutBackground(Drawable background) {
            this.mLayoutBackground = background;
            return this;
        }

        public Builder withCancelOnTouchOutside(boolean cancelOnTouchOutside) {
            this.mCancelOnTouchOutside = cancelOnTouchOutside;
            return this;
        }

        public QuickMenuProperties build() {
            return new QuickMenuProperties(this);
        }

    }

}
