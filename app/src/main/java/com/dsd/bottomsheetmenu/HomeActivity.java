package com.dsd.bottomsheetmenu;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsd.bottommenu.bottomsheetmenuoptions.MenuSheetView;
import com.dsd.bottommenu.helpers.Util;

/**
 * Created by dalveersinghdaiya on 15/10/18.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    Button buttonHideIcon;
    Button buttonChangeTextColor;
    Button buttonHideEven;
    Button buttonHideOdd;
    Button buttonChangeIconColor;
    Button buttonChangeIcon;


    boolean isShowOdd = true;
    boolean isShowEven = true;
    boolean isChangeTextColor = false;
    boolean isChangeIcons = false;
    boolean isChangeIconColor = false;
    boolean isShowMenuIcons = true;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewByIds();
        setSupportActionBar(toolbar);
        applyClickListeners();
    }

    public void viewByIds() {
        toolbar = findViewById(R.id.toolbar);
        buttonHideIcon = findViewById(R.id.button_hide_icon);
        buttonChangeTextColor = findViewById(R.id.button_change_text_color);
        buttonHideEven = findViewById(R.id.button_hide_even);
        buttonHideOdd = findViewById(R.id.button_hide_odd);
        buttonChangeIconColor = findViewById(R.id.button_change_icon_color);
        buttonChangeIcon = findViewById(R.id.button_change_icon);
    }

    public void applyClickListeners() {
        buttonHideIcon.setOnClickListener(this);
        buttonChangeTextColor.setOnClickListener(this);
        buttonHideEven.setOnClickListener(this);
        buttonHideOdd.setOnClickListener(this);
        buttonChangeIconColor.setOnClickListener(this);
        buttonChangeIcon.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showMenuOptions();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_change_icon:
                if (isChangeIcons) {
                    isChangeIcons = false;
                    setTextForButtons(buttonChangeIcon, getString(R.string.change_icons));
                } else {
                    isChangeIcons = true;
                    setTextForButtons(buttonChangeIcon, getString(R.string.revert_icons));
                }
                break;
            case R.id.button_change_text_color:
                if (isChangeTextColor) {
                    isChangeTextColor = false;
                    setTextForButtons(buttonChangeTextColor, getString(R.string.change_text_color));
                } else {
                    isChangeTextColor = true;
                    setTextForButtons(buttonChangeTextColor, getString(R.string.revert_text_to_default));
                }
                break;
            case R.id.button_hide_even:
                if (isShowEven) {
                    isShowEven = false;
                    setTextForButtons(buttonHideEven, getString(R.string.show_even));
                } else {
                    isShowEven = true;
                    setTextForButtons(buttonHideEven, getString(R.string.hide_even));
                }
                break;
            case R.id.button_hide_odd:
                if (isShowOdd) {
                    isShowOdd = false;
                    setTextForButtons(buttonHideOdd, getString(R.string.show_odd));
                } else {
                    isShowOdd = true;
                    setTextForButtons(buttonHideOdd, getString(R.string.hide_odd));
                }
                break;
            case R.id.button_change_icon_color:
                if (isChangeIconColor) {
                    isChangeIconColor = false;
                    setTextForButtons(buttonChangeIconColor, getString(R.string.change_icon_color));
                } else {
                    isChangeIconColor = true;
                    setTextForButtons(buttonChangeIconColor, getString(R.string.revert_icons_color));
                }
                break;
            case R.id.button_hide_icon:
                if (isShowMenuIcons) {
                    isShowMenuIcons = false;
                    setTextForButtons(buttonHideIcon, getString(R.string.show_icons));
                } else {
                    isShowMenuIcons = true;
                    setTextForButtons(buttonHideIcon, getString(R.string.hide_icons));
                }
                break;
            default:
                break;

        }
    }


    private void setTextForButtons(Button button, String buttonText) {
        button.setText(buttonText);
    }

    private void toastOnOptionClick(String message) {
        Toast.makeText(context, message + " is clicked.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show bottom sheet menu using the menu option listings.
     */
    private void showMenuOptions() {
        Util.showMenuSheet(context, "Options", R.menu.call_options_bottom, new MenuSheetView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                Util.hideMenuSheet();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (item.getItemId()) {
                            case R.id.action_option_one:
                                toastOnOptionClick(getString(R.string.option_one));
                                break;
                            case R.id.action_option_two:
                                toastOnOptionClick(getString(R.string.option_two));
                                break;
                            case R.id.action_option_three:
                                toastOnOptionClick(getString(R.string.option_three));
                                break;
                            case R.id.action_option_four:
                                toastOnOptionClick(getString(R.string.option_four));
                                break;
                            case R.id.action_option_five:
                                toastOnOptionClick(getString(R.string.option_five));
                                break;
                            case R.id.action_option_six:
                                toastOnOptionClick(getString(R.string.option_six));
                                break;
                            case R.id.action_option_seven:
                                toastOnOptionClick(getString(R.string.option_seven));
                                break;
                            case R.id.action_option_eight:
                                toastOnOptionClick(getString(R.string.option_eight));
                                break;
                        }
                    }
                }, Util.getSheetDuration());
                return false;
            }
        }, false, R.id.sheet_layout);

//       To hide and show each option with respect to the boolean conditions
        Util.showMenuItem(R.id.action_option_one, isShowOdd);
        Util.showMenuItem(R.id.action_option_two, isShowEven);
        Util.showMenuItem(R.id.action_option_three, isShowOdd);
        Util.showMenuItem(R.id.action_option_four, isShowEven);
        Util.showMenuItem(R.id.action_option_five, isShowOdd);
        Util.showMenuItem(R.id.action_option_six, isShowEven);
        Util.showMenuItem(R.id.action_option_seven, isShowOdd);
        Util.showMenuItem(R.id.action_option_eight, isShowEven);

//        Change icons at runtime
        if (isShowMenuIcons) {
            if (isChangeIcons) {
                Util.setMenuIcon(R.id.action_option_one, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_two, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_three, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_four, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_five, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_six, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_seven, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
                Util.setMenuIcon(R.id.action_option_eight, ContextCompat.getDrawable(context, R.drawable.ic_calendar));
            } else {
                Util.setMenuIcon(R.id.action_option_one, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_two, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_three, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_four, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_five, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_six, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_seven, ContextCompat.getDrawable(context, R.drawable.ic_role));
                Util.setMenuIcon(R.id.action_option_eight, ContextCompat.getDrawable(context, R.drawable.ic_role));
            }
        }

//        Change text color at runtime
        if (isChangeTextColor) {
            Util.setColorForMenuItemsText(context, R.id.action_option_one, getString(R.string.option_one), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_two, getString(R.string.option_two), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_three, getString(R.string.option_three), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_four, getString(R.string.option_four), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_five, getString(R.string.option_five), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_six, getString(R.string.option_six), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_seven, getString(R.string.option_seven), ContextCompat.getColor(context, R.color.colorAccent));
            Util.setColorForMenuItemsText(context, R.id.action_option_eight, getString(R.string.option_eight), ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            Util.setColorForMenuItemsText(context, R.id.action_option_one, getString(R.string.option_one), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_two, getString(R.string.option_two), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_three, getString(R.string.option_three), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_four, getString(R.string.option_four), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_five, getString(R.string.option_five), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_six, getString(R.string.option_six), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_seven, getString(R.string.option_seven), ContextCompat.getColor(context, R.color.dusk));
            Util.setColorForMenuItemsText(context, R.id.action_option_eight, getString(R.string.option_eight), ContextCompat.getColor(context, R.color.dusk));
        }

//        Change icon color at runtime
        if (isShowMenuIcons) {
            if (isChangeIconColor) {
                Util.setMenuIconTint(R.id.action_option_one, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_two, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_three, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_four, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_five, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_six, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_seven, ContextCompat.getColor(context, R.color.colorAccent));
                Util.setMenuIconTint(R.id.action_option_eight, ContextCompat.getColor(context, R.color.colorAccent));
            } else {
                Util.setMenuIconTint(R.id.action_option_one, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_two, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_three, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_four, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_five, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_six, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_seven, ContextCompat.getColor(context, R.color.colorPrimary));
                Util.setMenuIconTint(R.id.action_option_eight, ContextCompat.getColor(context, R.color.colorPrimary));
            }
        }

//        Updating the values so as to get the effects to take place.
        Util.updateMenuItems();
    }
}
