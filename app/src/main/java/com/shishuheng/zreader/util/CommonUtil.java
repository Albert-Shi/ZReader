package com.shishuheng.zreader.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.NumberFormat;

public class CommonUtil {
    private Activity activity;

    private Float heightDp = null;
    private Float widthDp = null;
    private Integer heightPixel = null;
    private Integer widthPixel = null;

    public CommonUtil(Activity activity) {
        setActivity(activity);
        getScreenSize(activity);
    }

    //获取手机高度dp 和 宽度dp
    private void getScreenSize(Activity activity) throws NullPointerException {
        WindowManager wm = activity.getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        heightDp = dm.heightPixels/density;
        widthDp = dm.widthPixels/density;
        heightPixel = dm.heightPixels;
        widthPixel = dm.widthPixels;
    }

    public Float getHeightDp() {
        return heightDp;
    }

    public Float getWidthDp() {
        return widthDp;
    }

    public Integer getHeightPixel() {
        return heightPixel;
    }

    public Integer getWidthPixel() {
        return widthPixel;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //计算一个屏幕的字符数
    public static Integer getFullScreenCharactorNumbers(Float heightDp, Float widthDp, Float letterSize, Float lineHeight, Float padding, Float letterSpacing) {
        Integer num = (int)(((heightDp-(padding*2))/lineHeight) * ((widthDp-(padding*2))/(letterSize + letterSpacing)));
        return num;
    }

    public static String getFileSize(Long size) {
        int i = 1;
        String unit = "B";
        float temp = size;
        while (temp >= 1024) {
            temp /= 1024;
            i++;
        }
        switch (i) {
            case 1: unit = "B";break;
            case 2: unit = "KB"; break;
            case 3: unit = "MB"; break;
            case 4: unit = "GB"; break;
            case 5: unit = "TB"; break;
            default: unit = "文件太大，已经不知道用什么单位了";
        }
        //格式化两位小数 参考 http://blog.csdn.net/chivalrousli/article/details/51122113
        NumberFormat percentageFormat = NumberFormat.getNumberInstance();
        percentageFormat.setMaximumFractionDigits(2);
        String result = percentageFormat.format(temp) +" "+ unit;
        return result;
    }
}
