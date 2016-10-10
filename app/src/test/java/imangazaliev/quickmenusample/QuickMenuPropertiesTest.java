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

import imangazaliev.quickmenu.QuickMenuProperties;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Mahach Imangazaliev on 15.01.2016
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, manifest = "src/main/AndroidManifest.xml")
public class QuickMenuPropertiesTest {

    private Activity activity;

    @Before
    public void init() {
        activity = Robolectric.setupActivity(QuickSettingsActivity.class);
    }

    @Test
    public void testWidthInPercentages() {
        new QuickMenuProperties.Builder(activity)
                .withWidthInPercentages(50)
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testWidthMoreThan100Percent() {
        new QuickMenuProperties.Builder(activity)
                .withWidthInPercentages(101)
                .build();
    }

    @Test
    public void testWidthInPixels() {
        new QuickMenuProperties.Builder(activity)
                .withWidth(300)
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testWidthLessThanZeroPixels() {
        new QuickMenuProperties.Builder(activity)
                .withWidth(-2)
                .build();
    }

    @Test()
    public void testMenuPropertiesMargins() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withMargins(10, 20, 30, 40)
                .build();

        assertEquals(10, properties.getMarginLeft());
        assertEquals(20, properties.getMarginTop());
        assertEquals(30, properties.getMarginRight());
        assertEquals(40, properties.getMarginBottom());
    }

    @Test()
    public void testMenuPropertiesPaddings() {
        QuickMenuProperties properties = new QuickMenuProperties.Builder(activity)
                .withPaddings(10, 20, 30, 40)
                .build();

        assertEquals(10, properties.getPaddingLeft());
        assertEquals(20, properties.getPaddingTop());
        assertEquals(30, properties.getPaddingRight());
        assertEquals(40, properties.getPaddingBottom());
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
