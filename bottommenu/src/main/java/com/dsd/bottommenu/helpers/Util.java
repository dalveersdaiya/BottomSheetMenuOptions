package com.dsd.bottommenu.helpers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;

import com.dsd.bottommenu.R;
import com.dsd.bottommenu.bottomsheetmenuoptions.BottomSheetLayout;
import com.dsd.bottommenu.bottomsheetmenuoptions.MenuSheetView;
import com.dsd.bottommenu.bottomsheetmenuoptions.OnSheetDismissedListener;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Util {


    /**
     * Created by dalveersinghdaiya on 15/10/18.
     */

    public static BottomSheetLayout bottomSheetLayout;
    public static MenuSheetView menuSheetView;

    private Util() {
        throw new AssertionError("No Instances");
    }

    /**
     * Convert a dp float value to pixels
     *
     * @param dp float value in dps to convert
     * @return DP value converted to pixels
     */
    public static int dp2px(Context context, float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return Math.round(px);
    }

    public static void replaceFont(String staticTypefaceFieldName,
                                   final Typeface newTypeface) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<String, Typeface>();
            newMap.put("sans-serif", newTypeface);
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


//    For Bottom sheet

    public static void changeStatusBarColor(Context context, String color) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    public static void changeStatusBarColor(Context context, int color) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public static void showMenuSheet(final Context context, String title, int menuResource, MenuSheetView.OnMenuItemClickListener menuItemClickListener, boolean isForCustomHeight, int sheetLayoutId) {
        bottomSheetLayout = ((Activity) context).findViewById(sheetLayoutId);
        menuSheetView = new MenuSheetView(context, MenuSheetView.MenuType.LIST, title, menuItemClickListener);
        menuSheetView.inflateMenu(menuResource);
        bottomSheetLayout.showWithSheetView(menuSheetView);
        int currentOrientation = ((Activity) context).getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            bottomSheetLayout.setPeekSheetTranslation(550);
        } else {
            if (isForCustomHeight) {
                bottomSheetLayout.setPeekSheetTranslation(700);
            }
        }
        setTypeFaceForMenuItems(menuSheetView.getMenu(), context);
        setTypeFaceForMenuItemsForActionSingle(menuSheetView.getMenu(), context);
        changeStatusBarColor(context, ContextCompat.getColor(context, R.color.black_opaque));
        bottomSheetLayout.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            @Override
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                changeStatusBarColor(context, ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
        });
    }

    public static void showMenuItem(int menuItem, boolean show) {
        menuSheetView.getMenu().findItem(menuItem).setVisible(show);
    }

    public static void setMenuIcon(int menuItem, Drawable drawable) {
        menuSheetView.getMenu().findItem(menuItem).setIcon(drawable);
    }

    public static void setMenuIconTint(int menuItem, int color) {
        Drawable mDrawable = menuSheetView.getMenu().findItem(menuItem).getIcon();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDrawable.setTint(color);
        }
        menuSheetView.getMenu().findItem(menuItem).setIcon(mDrawable);
    }

    public static void hideMenuSheet() {
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        }
    }

    public static long getSheetDuration() {
        return bottomSheetLayout.getSheetDuration();
    }

    public static void updateMenuItems() {
        menuSheetView.updateMenu();
    }

    public static void setColorForMenuItemsText(Context context, int menuItemRes, String source, int color) {
        MenuItem menuItem = menuSheetView.getMenu().findItem(menuItemRes);
        SpannableString s = new SpannableString(source);
        s.setSpan(new ForegroundColorSpan(color), 0, s.length(), 0);
        menuItem.setTitle(s);
    }

    public static void setTextForMenuItem(Context context, int menuItemRes, String title) {
        setColorForMenuItemsText(context, menuItemRes, title, ContextCompat.getColor(context, R.color.dusk));
    }

    public static void setTypeFaceForMenuItems(Menu menu, Context context) {
        Typeface typeface = FontHelper.getInstance(context).getRegularFont();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem, typeface);
                }
            }
            //the method we have create in activity
            applyFontToMenuItem(mi, typeface);
        }
    }

    public static void applyFontToMenuItem(MenuItem mi, Typeface font) {
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomSpanClass("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public static void setTypeFaceForMenuItemsForActionSingle(Menu menu, Context context) {
        Typeface typeface = FontHelper.getInstance(context).getRegularFont();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    replaceFont(subMenuItem.getTitle().toString(), typeface);
                }
            }
            //the method we have create in activity
            replaceFont(mi.getTitle().toString(), typeface);
        }
    }

    /**
     * A helper class for providing a shadow on sheets
     */
    @TargetApi(21)
    public static class ShadowOutline extends ViewOutlineProvider {

        int width;
        int height;

        public ShadowOutline(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, width, height);
        }
    }


}
