package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util;

import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * @author Mirash
 */
public final class MirashUtils {
    private static final String LOG = "LOL";

    public static float clamp(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }

    public static void log(String msg) {
        logD(msg);
    }

    public static void logD(String msg) {
        Log.d(LOG, msg);
    }

    public static void interpolate(View view1, View view2, View header, float interpolation) {
        RectF getRect1 = new RectF();
        RectF getRect2 = new RectF();
        getOnScreenRect(getRect1, view1);
        getOnScreenRect(getRect2, view2);

        float scaleX = 1.0F + interpolation * (getRect2.width() / getRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (getRect2.height() / getRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (getRect2.left + getRect2.right - getRect1.left - getRect1.right));
        float translationY = 0.5F * (interpolation * (getRect2.top + getRect2.bottom - getRect1.top - getRect1.bottom));

        ViewHelper.setTranslationX(view1, translationX);
        ViewHelper.setTranslationY(view1, translationY - ViewHelper.getTranslationY(header));
        ViewHelper.setScaleX(view1, scaleX);
        ViewHelper.setScaleY(view1, scaleY);
    }

    public static RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

}
