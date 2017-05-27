package admin_jyb.zhihuribao.Application;

import android.app.Application;
import android.util.DisplayMetrics;

import cn.bmob.v3.Bmob;


/**
 * Created by Admin-JYB on 2017/4/11.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    public static int widthPixels;
    public static int heightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Bmob.initialize(this,"d6218f4f20b3c6f586ed638bde85f14d");
        DisplayMetrics density = getResources().getDisplayMetrics();
        widthPixels = density.widthPixels;
        heightPixels = density.heightPixels;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
