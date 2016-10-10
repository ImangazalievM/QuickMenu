package imangazaliev.quickmenu;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

import imangazaliev.quickmenulibrary.R;
import imangazaliev.quickmenu.interfaces.QuickMenuItem;

/**
 * Этот класс устанавливает меню в лейаут и заполняет меню пунктами
 */
public class QuickMenuHelper {

    private Activity mActivity;
    private List<QuickMenuItem> mItems;
    /* Лейаут, в который будет устанавливаться родительский лейаут меню */
    private FrameLayout mMenuContainerLayout;
    /* Родительский лейаут меню */
    private LinearLayout mParentLayout;
    /* Лейаут меню */
    private LinearLayout mMenuLayout;
    /* Лейаут пунктов меню */
    private LinearLayout mMenuItemsContainer;
    private Animation mShowAnimation;
    private Animation mHideAnimation;
    private QuickMenuListener mQuickMenuListener;
    private boolean isAnimationActive;

    public QuickMenuHelper(Activity activity, List<QuickMenuItem> items, int layoutId, QuickMenuProperties properties) {
        this.mActivity = activity;
        this.mItems = items;

        this.isAnimationActive = false;

        initContainerLayout(layoutId);
        setupMenuLayout(properties);
        initAnimations(properties);
        addItemsToMenu();
    }

    public void setQuickMenuListener(QuickMenuListener quickMenuListener) {
        mQuickMenuListener = quickMenuListener;
    }

    /**
     * Устанавливаем меню в лейаут
     */
    private void initContainerLayout(int layoutId) {
        try {
            mMenuContainerLayout = (FrameLayout) mActivity.findViewById(layoutId);
            if (mMenuContainerLayout == null) {
                throw new RuntimeException("Menu's container layout not found");
            }
        } catch (ClassCastException e) {
            throw new RuntimeException("Menu's container layout must be FrameLayout");
        }
    }

    /**
     * Устанавливаем меню в лейаут
     */
    private boolean childIsTouched;
    private void setupMenuLayout(final QuickMenuProperties properties) {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        inflater.inflate(R.layout.quick_menu_layout, mMenuContainerLayout, true);
        mParentLayout = (LinearLayout) mMenuContainerLayout.findViewById(R.id.menuParentLayout);
        mMenuLayout = (LinearLayout) mParentLayout.findViewById(R.id.menuLayout);
        mMenuItemsContainer = (LinearLayout) mParentLayout.findViewById(R.id.menuItemsContainer);

        mParentLayout.setVisibility(View.GONE);
        mParentLayout.setBackgroundDrawable(properties.getLayoutBackground());
        mMenuLayout.setBackgroundDrawable(properties.getMenuBackground());
        mParentLayout.setGravity(properties.getGravity());

        mParentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int menuWidth;
                if (properties.getWidthMode() == QuickMenuProperties.WidthMode.PIXELS) {
                    menuWidth = properties.getMenuWidth();
                } else {
                    //устанавливаем ширину в процентах от ширины родителя
                    float widthInPercentages = properties.getMenuWidthInPercentages() * 0.01f;
                    menuWidth = (int) (mParentLayout.getWidth() * widthInPercentages);
                }

                LinearLayout.LayoutParams menuLayoutParams = (LinearLayout.LayoutParams) mMenuLayout.getLayoutParams();
                menuLayoutParams.setMargins(properties.getMarginLeft(), properties.getMarginTop(), properties.getMarginRight(), properties.getMarginBottom());
                menuLayoutParams.width = menuWidth;

                mMenuLayout.setLayoutParams(menuLayoutParams);
                mMenuLayout.setPadding(properties.getPaddingLeft(), properties.getPaddingTop(), properties.getPaddingRight(), properties.getPaddingBottom());

            }
        });

        if (properties.getCanceledOnTouchOutside()) {
            mParentLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (childIsTouched) {
                        childIsTouched = false;
                        return true;
                    }
                    hideMenu();
                    return true;
                }
            });

            mMenuLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    childIsTouched = true;
                    return true;
                }
            });
        }

    }

    private void initAnimations(QuickMenuProperties properties) {
        this.mShowAnimation = properties.getOnShowAnimation();
        this.mHideAnimation = properties.getOnHideAnimation();

        if (mShowAnimation != null) {
            mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isAnimationActive = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationActive = false;
                    if (mQuickMenuListener != null) {
                        mQuickMenuListener.onMenuShowed();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }

        if (mHideAnimation != null) {
            mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isAnimationActive = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationActive = false;
                    mParentLayout.setVisibility(View.GONE);
                    if (mQuickMenuListener != null) {
                        mQuickMenuListener.onMenuHided();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
    }

    /**
     * Добавляем элементы меню в лейаут
     */
    private void addItemsToMenu() {
        for (QuickMenuItem item : mItems) {
            mMenuItemsContainer.addView(item.getView(mActivity, mMenuLayout));
        }
    }

    public boolean isShowing() {
        return mParentLayout.getVisibility() == View.VISIBLE;
    }

    public void showMenu() {
        if (isAnimationActive) {
            return;
        }

        mParentLayout.setVisibility(View.VISIBLE);

        if (mShowAnimation != null) {
            mMenuLayout.startAnimation(mShowAnimation);
        }
    }

    public void hideMenu() {
        if (isAnimationActive) {
            return;
        }

        if (mHideAnimation != null) {
            mMenuLayout.startAnimation(mHideAnimation);
        } else {
            mParentLayout.setVisibility(View.GONE);
        }
    }


}
