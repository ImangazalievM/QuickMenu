package imangazaliev.quickmenusample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import imangazaliev.quickmenulibrary.QuickMenu;
import imangazaliev.quickmenulibrary.QuickMenuProperties;
import imangazaliev.quickmenulibrary.model.SpinnerMenuItem;

/**
 * Created by Mahach Imangazaliev on 14.01.2016
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, manifest = "src/main/AndroidManifest.xml")
public class QuickMenuTest {

    private Activity activity;
    private final String[] spinnerItems = {"Ражик", "Мурзик", "Барсик"};
    private int menuWidth;
    private int menuWidthInPercentages;
    private int mMarginTop, mMarginLeft, mMarginBottom, mMarginRight;
    private Drawable menuBackground;
    private Drawable layoutBackground;

    @Before
    public void init() {
        activity = Robolectric.setupActivity(MainActivity.class);
        menuWidth = activity.getResources().getDimensionPixelSize(R.dimen.quick_menu_custom_width);
        menuWidthInPercentages = 50;
        mMarginTop = 50;
        mMarginLeft = 50;
        mMarginBottom = 50;
        menuBackground = activity.getResources().getDrawable(R.drawable.quick_menu_custom_bg);
        layoutBackground = new ColorDrawable(Color.parseColor("#99000000"));
    }

    @Test(expected = RuntimeException.class)
    public void testActivityNull() {
        QuickMenu menu = new QuickMenu.Builder(null)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();

    }

    @Test(expected = RuntimeException.class)
    public void testOneOfItemsNull() {
        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(null)
                .build();
    }

    @Test()
    public void testCorrectLayoutId() {
        QuickMenu menu = new QuickMenu.Builder(activity)
                .withLayoutId(R.id.quickMenuContainer)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testIncorrectLayoutId() {
        QuickMenu menu = new QuickMenu.Builder(activity)
                .withLayoutId(-1)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test()
    public void testMenu() {
        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test()
    public void testMenuPropertiesWidth() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withWidth(menuWidth)
                .build();

        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();
    }

    @Test()
    public void testMenuPropertiesWidthInPercentages() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withWidthInPercentages(menuWidthInPercentages)
                .build();

        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();
    }

    @Test()
    public void testMenuPropertiesMargins() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withMargins(mMarginTop, mMarginLeft, mMarginBottom, mMarginLeft)
                .build();

        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();
    }

    @Test()
    public void testMenuVisibility() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withMargins(mMarginTop, mMarginLeft, mMarginBottom, mMarginLeft)
                .build();

        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();

        //меню не показывается
        Assert.assertTrue(!menu.isShowing());

        //меню показывается
        menu.show();
        Assert.assertTrue(menu.isShowing());
    }

}
