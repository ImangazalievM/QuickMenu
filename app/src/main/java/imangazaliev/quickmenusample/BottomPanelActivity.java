package imangazaliev.quickmenusample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import imangazaliev.quickmenu.QuickMenu;
import imangazaliev.quickmenu.QuickMenuListener;
import imangazaliev.quickmenu.QuickMenuProperties;
import imangazaliev.quickmenu.model.DividerMenuItem;
import imangazaliev.quickmenu.model.SpinnerMenuItem;

public class BottomPanelActivity extends AppCompatActivity {

    QuickMenu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_panel);
        String[] spinnerItems = {"Cat", "Dog", "Cow"};

        Drawable menuBackground = getResources().getDrawable(R.drawable.bottom_panel_menu_bg);
        Drawable layoutBackground = new ColorDrawable(Color.parseColor("#80000000"));

        QuickMenuProperties properties = new QuickMenuProperties.Builder(this)
                .withWidthInPercentages(100)
                .withGravity(Gravity.BOTTOM)
                .withBackground(menuBackground)
                .withLayoutBackground(layoutBackground)
                .withCancelOnTouchOutside(true)
                .withMargins(0, 0, 0, 0)
                .withOnShowAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_panel_slide_up))
                .withOnHideAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_panel_slide_down))
                .build();

        menu = new QuickMenu.Builder(this)
                .withItems(new SpinnerMenuItem(spinnerItems),
                        new DividerMenuItem(this).withColor(Color.parseColor("#EEEEEE")),
                        new SpinnerMenuItem(spinnerItems))
                .withProperties(properties)
                .build();

        menu.setQuickMenuListener(new QuickMenuListener() {
            @Override
            public void onMenuShowed() {
                Toast.makeText(BottomPanelActivity.this, "Menu showed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuHided() {
                Toast.makeText(BottomPanelActivity.this, "Menu hided", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showMenu(View view) {
        menu.show();
    }
}
