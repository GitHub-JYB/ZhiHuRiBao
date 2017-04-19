package admin_jyb.zhihuribao.util;

import android.widget.Toast;

import admin_jyb.zhihuribao.Application.MyApplication;


/**
 * Created by Admin-JYB on 2017/4/11.
 */

public class ToastUtil {

    private static Toast toast;

    public static void showShortToast(CharSequence message){
        if (toast == null){
            toast = Toast.makeText(MyApplication.getInstance(),message,Toast.LENGTH_SHORT);
        }else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showLongToast(CharSequence message){
        if (toast == null){
            toast = Toast.makeText(MyApplication.getInstance(),message,Toast.LENGTH_LONG);
        }else {
            toast.setText(message);
        }
        toast.show();
    }
}
