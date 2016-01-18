package imangazaliev.quickmenulibrary;

import android.app.Activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import imangazaliev.quickmenulibrary.interfaces.QuickMenuItem;

/**
 * Меню быстрых настроек
 */
public class QuickMenu {

    private final Activity mActivity;
    private final List<QuickMenuItem> mItems;
    private final QuickMenuHelper mHelper;

    private QuickMenu(Builder builder) {
        mActivity = builder.mActivity;
        mItems = builder.mItems;
        int layoutId = builder.mLayoutId;

        mHelper = new QuickMenuHelper(mActivity, mItems, layoutId, builder.mMenuProperties);
    }

    public Activity getActivity() {
        return mActivity;
    }

    public Activity getItems() {
        return mActivity;
    }

    public boolean isShowing() {
        return mHelper.isShowed();
    }

    public void show() {
        mHelper.showMenu();
    }

    public void hide() {
        mHelper.hideMenu();
    }

    public static class Builder {

        private Activity mActivity;
        private int mLayoutId;
        /* Пункты меню */
        private List<QuickMenuItem> mItems;
        /* Настройки меню */
        private QuickMenuProperties mMenuProperties;

        public Builder(Activity activity) {
            mActivity = activity;
            //устанавливаем стандартный id лейаута
            mLayoutId = R.id.quickMenuContainer;
            mItems = Collections.emptyList();
        }

        public Builder withLayoutId(int layoutId) {
            this.mLayoutId = layoutId;
            return this;
        }

        public Builder withItems(QuickMenuItem... items) {
            this.mItems = Arrays.asList(items);
            return this;
        }

        public Builder withProperties(QuickMenuProperties mMenuProperties) {
            this.mMenuProperties = mMenuProperties;
            return this;
        }

        public QuickMenu build() {
            //проверяем, была ли передана активити
            if (mActivity == null) {
                throw new RuntimeException("Activity value cannot be null");
            }

            //проверяем, чтобы ни один из элеиентов не равнялся null
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i) == null) {
                    throw new RuntimeException("Item with index " + i + " is null");
                }
            }

            if (mMenuProperties == null) {
                this.mMenuProperties = new QuickMenuProperties.Builder(mActivity).build();
            }

            return new QuickMenu(this);
        }
    }

}
