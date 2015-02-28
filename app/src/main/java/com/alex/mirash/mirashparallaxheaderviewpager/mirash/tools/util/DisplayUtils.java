package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author Mirash
 */
public class DisplayUtils {
    public static Point getDisplaySize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static int getDisplayHeight(Context context) {
        return getDisplaySize(context).y;
    }

    public static int getDisplayWidth(Context context) {
        return getDisplaySize(context).x;
    }

    public static float convertDpToPixel(Context context, float valueInDp) {
        return valueInDp * (context.getResources().getDisplayMetrics().densityDpi / 160f);
    }
}
