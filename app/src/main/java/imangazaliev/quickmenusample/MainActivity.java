package imangazaliev.quickmenusample;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openQuickSettingsActivity(View view) {
        Intent intent = new Intent(this, QuickSettingsActivity.class);
        startActivity(intent);
    }

    public void openBottomPanelActivity(View view) {
        Intent intent = new Intent(this, BottomPanelActivity.class);
        startActivity(intent);
    }
}
