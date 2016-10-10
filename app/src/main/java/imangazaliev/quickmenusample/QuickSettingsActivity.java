package imangazaliev.quickmenusample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import imangazaliev.quickmenu.QuickMenu;
import imangazaliev.quickmenu.QuickMenuProperties;
import imangazaliev.quickmenu.model.DividerMenuItem;
import imangazaliev.quickmenu.model.SpinnerMenuItem;

public class QuickSettingsActivity extends AppCompatActivity {

    QuickMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_settings);

        String[] spinnerItems = {"Cat", "Dog", "Cow"};

        Drawable menuBackground = getResources().getDrawable(R.drawable.quick_settings_menu_bg);
        Drawable layoutBackground = new ColorDrawable(Color.parseColor("#80000000"));

        QuickMenuProperties properties = new QuickMenuProperties.Builder(this)
                .withWidthInPercentages(60)
                .withBackground(menuBackground)
                .withMargins(50, 50, 50, 50)
                .withLayoutBackground(layoutBackground)
                .withCancelOnTouchOutside(true)
                .build();

        menu = new QuickMenu.Builder(this)
                .withItems(new SpinnerMenuItem(spinnerItems),
                        new DividerMenuItem(this).withColor(Color.parseColor("#EEEEEE")),
                        new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();


    }

    public void showMenu(View view) {
        menu.show();
    }
}
