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

import imangazaliev.quickmenu.QuickMenu;
import imangazaliev.quickmenu.QuickMenuProperties;
import imangazaliev.quickmenu.model.SpinnerMenuItem;

/**
 * Created by Mahach Imangazaliev on 14.01.2016
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, manifest = "src/main/AndroidManifest.xml")
public class QuickMenuTest {

    private Activity activity;
    private final String[] spinnerItems = {"Ражик", "Мурзик", "Барсик"};

    @Before
    public void init() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test(expected = RuntimeException.class)
    public void testActivityNull() {
        new QuickMenu.Builder(null)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();

    }

    @Test(expected = RuntimeException.class)
    public void testOneOfItemsNull() {
        new QuickMenu.Builder(activity)
                .withItems(null)
                .build();
    }

    @Test()
    public void testCorrectLayoutId() {
        new QuickMenu.Builder(activity)
                .withLayoutId(R.id.quickMenuContainer)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testIncorrectLayoutId() {
        new QuickMenu.Builder(activity)
                .withLayoutId(-1)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test()
    public void testMenu() {
        new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();
    }

    @Test()
    public void testMenuVisibility() {
        QuickMenu menu = new QuickMenu.Builder(activity)
                .withItems(new SpinnerMenuItem(spinnerItems))
                .build();

        //меню не показывается
        Assert.assertTrue(!menu.isShowing());

        //меню показывается
        menu.show();
        Assert.assertTrue(menu.isShowing());
    }

}
