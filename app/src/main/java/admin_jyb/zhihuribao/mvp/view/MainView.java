package admin_jyb.zhihuribao.mvp.view;

import android.support.v4.app.Fragment;

import admin_jyb.zhihuribao.mvp.widget.HomeFragment;


/**
 * Created by Admin-JYB on 2016/9/26.
 */

public interface MainView {

    void closeDrawers();

    void replaceFragment(Fragment oldFragment, Fragment newFragment);

    void exit();
}
