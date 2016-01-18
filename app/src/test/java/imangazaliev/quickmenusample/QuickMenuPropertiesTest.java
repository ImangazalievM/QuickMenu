package imangazaliev.quickmenusample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import imangazaliev.quickmenulibrary.QuickMenuProperties;

/**
 * Created by Mahach Imangazaliev on 15.01.2016
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, manifest = "src/main/AndroidManifest.xml")
public class QuickMenuPropertiesTest {

    private Activity activity;

    @Before
    public void init() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test(expected = RuntimeException.class)
    public void testWidthMoreThan100Percent() {
        new QuickMenuProperties.Builder(activity)
                .withWidthInPercentages(101)
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testLessThanZeroPixels() {
        new QuickMenuProperties.Builder(activity)
                .withWidth(-2)
                .build();
    }

    @Test
    public void testQuickMenuProperties() {
        Drawable menuBackground = activity.getResources().getDrawable(R.drawable.quick_menu_bg);
        Drawable layoutBackground = new ColorDrawable(Color.parseColor("#99000000"));

        new QuickMenuProperties.Builder(activity)
                .withWidth(1200)
                .withWidthInPercentages(99)
                .withBackground(menuBackground)
                .withMargins(0, 0, 10, 10)
                .withLayoutBackground(layoutBackground)
                .build();
    }
}
