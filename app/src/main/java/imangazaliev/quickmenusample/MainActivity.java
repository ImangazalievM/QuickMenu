package imangazaliev.quickmenusample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import imangazaliev.quickmenu.QuickMenu;
import imangazaliev.quickmenu.QuickMenuProperties;
import imangazaliev.quickmenu.model.DividerMenuItem;
import imangazaliev.quickmenu.model.SpinnerMenuItem;

public class MainActivity extends AppCompatActivity {

    QuickMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] spinnerItems = {"Cat", "Dog", "Cow"};

        int menuWidth = getResources().getDimensionPixelSize(R.dimen.quick_menu_custom_width);
        Drawable menuBackground = getResources().getDrawable(R.drawable.quick_menu_custom_bg);
        Drawable layoutBackground = new ColorDrawable(Color.parseColor("#80000000"));

        QuickMenuProperties properties = new QuickMenuProperties.Builder(this)
                .withWidthInPercentages(40)
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
