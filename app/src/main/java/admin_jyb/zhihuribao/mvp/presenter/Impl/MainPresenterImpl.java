package admin_jyb.zhihuribao.mvp.presenter.Impl;

import android.view.MenuItem;

import admin_jyb.zhihuribao.Application.MyApplication;
import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.mvp.presenter.MainPresenter;
import admin_jyb.zhihuribao.mvp.view.MainView;
import admin_jyb.zhihuribao.mvp.widget.GirlFragment;
import admin_jyb.zhihuribao.mvp.widget.HomeFragment;
import admin_jyb.zhihuribao.util.SpUtil;

/**
 * Created by Admin-JYB on 2016/9/26.
 */

public class MainPresenterImpl implements MainPresenter {

    private final MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void switchNavition(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                if (!isItemChecked(item)){
                    mainView.replaceFragment(GirlFragment.getInstance(), HomeFragment.getInstance());
                }
                break;
            case R.id.nav_girl:
                if (!isItemChecked(item)){
                    mainView.replaceFragment(HomeFragment.getInstance(), GirlFragment.getInstance());
                }
                break;
            case R.id.nav_exit:
                SpUtil.getIntance(MyApplication.getInstance()).setUsername("");
                mainView.exit();
                break;
        }
        item.setChecked(true);
        mainView.closeDrawers();
    }

    private boolean isItemChecked(MenuItem item) {
        return item.isChecked();
    }
}
