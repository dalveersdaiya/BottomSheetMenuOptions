package com.dsd.bottommenu.bottomsheetmenuoptions;

import android.view.View;

/**
 * Created by dalveersinghdaiya on 15/10/18.
 */
public abstract class BaseViewTransformer implements ViewTransformer {

    public static final float MAX_DIM_ALPHA = 0.7f;

    @Override
    public float getDimAlpha(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        float progress = translation / maxTranslation;
        return progress * MAX_DIM_ALPHA;
    }

}
