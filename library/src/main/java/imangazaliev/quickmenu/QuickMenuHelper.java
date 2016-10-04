package imangazaliev.quickmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    private RelativeLayout mParentLayout;
    /* Лейаут меню */
    private LinearLayout mMenuLayout;
    /* Лейаут пунктов меню */
    private LinearLayout mMenuItemsContainer;

    public QuickMenuHelper(Activity activity, List<QuickMenuItem> items, int layoutId, QuickMenuProperties properties) {
        this.mActivity = activity;
        this.mItems = items;

        initContainerLayout(layoutId);
        setupMenuLayout(properties);
        addItemsToMenu();
        hideMenu();
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
        mParentLayout = (RelativeLayout) mMenuContainerLayout.findViewById(R.id.menuParentLayout);
        mMenuLayout = (LinearLayout) mParentLayout.findViewById(R.id.menuLayout);
        mMenuItemsContainer = (LinearLayout) mParentLayout.findViewById(R.id.menuItemsContainer);

        mParentLayout.setBackgroundDrawable(properties.getLayoutBackground());
        mMenuLayout.setBackgroundDrawable(properties.getMenuBackground());

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

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mMenuLayout.getLayoutParams();
                layoutParams.setMargins(properties.getMarginLeft(), properties.getMarginTop(), properties.getMarginRight(), properties.getMarginBottom());
                layoutParams.width = menuWidth;
                mMenuLayout.setLayoutParams(layoutParams);
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
        mParentLayout.setVisibility(View.VISIBLE);
    }

    public void hideMenu() {
        mParentLayout.setVisibility(View.GONE);
    }


}
