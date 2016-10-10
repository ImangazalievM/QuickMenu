package imangazaliev.quickmenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.animation.Animation;

import imangazaliev.quickmenulibrary.R;

/**
 * Created by Mahach Imangazaliev on 15.01.2016
 */
public class QuickMenuProperties {

    public enum WidthMode {PIXELS, PERCENTAGES}

    private final WidthMode mWidthMode;
    private final int mMenuWidth;
    private int mGravity;
    private final int mMenuWidthInPercentages;
    private final int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;
    private final int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    private final Drawable mMenuBackground;
    private final Drawable mLayoutBackground;
    private final boolean mCanceledOnTouchOutside;
    private final Animation mShowAnimation;
    private final Animation mHideAnimation;

    private QuickMenuProperties(Builder builder) {
        this.mWidthMode = builder.mWidthMode;
        this.mMenuWidth = builder.mMenuWidth;
        this.mGravity = builder.mGravity;
        this.mMenuWidthInPercentages = builder.mMenuWidthInPercentages;
        this.mMarginLeft = builder.mMarginLeft;
        this.mMarginTop = builder.mMarginTop;
        this.mMarginRight = builder.mMarginRight;
        this.mMarginBottom = builder.mMarginBottom;
        this.mPaddingLeft = builder.mPaddingLeft;
        this.mPaddingTop = builder.mPaddingTop;
        this.mPaddingRight = builder.mPaddingRight;
        this.mPaddingBottom = builder.mPaddingBottom;
        this.mMenuBackground = builder.mMenuBackground;
        this.mLayoutBackground = builder.mLayoutBackground;
        this.mCanceledOnTouchOutside = builder.mCanceledOnTouchOutside;
        this.mShowAnimation = builder.mShowAnimation;
        this.mHideAnimation = builder.mHideAnimation;
    }

    public WidthMode getWidthMode() {
        return mWidthMode;
    }

    public int getMenuWidth() {
        return mMenuWidth;
    }

    public int getGravity() {
        return mGravity;
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

    public int getPaddingTop() {
        return mPaddingTop;
    }

    public int getPaddingBottom() {
        return mPaddingBottom;
    }

    public int getPaddingLeft() {
        return mPaddingLeft;
    }

    public int getPaddingRight() {
        return mPaddingRight;
    }

    public Drawable getMenuBackground() {
        return mMenuBackground;
    }

    public Drawable getLayoutBackground() {
        return mLayoutBackground;
    }

    public boolean getCanceledOnTouchOutside() {
        return mCanceledOnTouchOutside;
    }

    public Animation getOnShowAnimation() {
        return mShowAnimation;
    }

    public Animation getOnHideAnimation() {
        return mHideAnimation;
    }

    public static class Builder {

        /**  */
        private WidthMode mWidthMode;
        /**
         * Ширина меню в пикселях
         */
        private int mMenuWidth;
        /**
         * Позиция на экране
         */
        private int mGravity;
        /**
         * Ширина меню в процентах
         */
        private int mMenuWidthInPercentages;
        /**
         * Внешние отступы меню
         */
        private int mMarginTop, mMarginLeft, mMarginBottom, mMarginRight;
        /**
         * Внутренние отступы меню
         */
        private int mPaddingTop, mPaddingLeft, mPaddingBottom, mPaddingRight;
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
        private boolean mCanceledOnTouchOutside;
        /**
         * Анимация при появлении меню
         */
        private Animation mShowAnimation;
        /**
         * Анимация при скрытии меню
         */
        private Animation mHideAnimation;

        public Builder(Context context) {
            this.mWidthMode = WidthMode.PIXELS;
            this.mMenuWidth = context.getResources().getDimensionPixelSize(R.dimen.quick_menu_width);
            this.mGravity = Gravity.TOP | Gravity.RIGHT;
            this.mMarginTop = mMarginLeft = mMarginBottom = mMarginRight = context.getResources().getDimensionPixelSize(R.dimen.quick_menu_margin);
            this.mPaddingTop = mPaddingLeft = mPaddingBottom = mPaddingRight = context.getResources().getDimensionPixelSize(R.dimen.quick_menu_padding);
            this.mMenuBackground = context.getResources().getDrawable(R.drawable.quick_menu_bg);
            this.mLayoutBackground = new ColorDrawable(Color.parseColor("#99000000"));
            this.mCanceledOnTouchOutside = false;
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

        public Builder withGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        public Builder withMargins(int left, int top, int right, int bottom) {
            this.mMarginLeft = left;
            this.mMarginTop = top;
            this.mMarginRight = right;
            this.mMarginBottom = bottom;
            return this;
        }

        public Builder withPaddings(int left, int top, int right, int bottom) {
            this.mPaddingLeft = left;
            this.mPaddingTop = top;
            this.mPaddingRight = right;
            this.mPaddingBottom = bottom;
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
            this.mCanceledOnTouchOutside = cancelOnTouchOutside;
            return this;
        }

        public Builder withOnShowAnimation(Animation showAnimation) {
            this.mShowAnimation = showAnimation;
            return this;
        }

        public Builder withOnHideAnimation(Animation hideAnimation) {
            this.mHideAnimation = hideAnimation;
            return this;
        }

        public QuickMenuProperties build() {
            return new QuickMenuProperties(this);
        }

    }

}
