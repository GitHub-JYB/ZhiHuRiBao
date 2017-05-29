package admin_jyb.zhihuribao.Application;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import cn.bmob.v3.Bmob;


/**
 * Created by Admin-JYB on 2017/4/11.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    public static int widthPixels;
    public static int heightPixels;
    public static int statusHight;
    private static float scale;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Bmob.initialize(this,"d6218f4f20b3c6f586ed638bde85f14d");
        DisplayMetrics density = getResources().getDisplayMetrics();
        widthPixels = density.widthPixels;
        heightPixels = density.heightPixels;
        scale = density.density;
        statusHight = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height","dimen","android"));
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public static int px2dip(float px){
        return (int)(px/scale + 0.5f);
    }

    public static int dip2px(float dip){
        return (int)(dip * scale + 0.5f);
    }
}
