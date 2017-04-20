package admin_jyb.zhihuribao.Application;

import android.app.Application;

import cn.bmob.v3.Bmob;


/**
 * Created by Admin-JYB on 2017/4/11.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Bmob.initialize(this,"d6218f4f20b3c6f586ed638bde85f14d");
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
