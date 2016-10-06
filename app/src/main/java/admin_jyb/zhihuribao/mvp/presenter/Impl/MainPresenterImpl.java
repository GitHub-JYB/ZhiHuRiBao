package admin_jyb.zhihuribao.mvp.presenter.Impl;

import android.view.MenuItem;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.mvp.presenter.MainPresenter;
import admin_jyb.zhihuribao.mvp.view.MainView;
import admin_jyb.zhihuribao.mvp.widget.HomeFragment;

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
                    mainView.replaceFragment(null, HomeFragment.getInstance());
                }
                break;
        }
        item.setChecked(true);
        mainView.closeDrawers();
    }

    private boolean isItemChecked(MenuItem item) {
        return item.isChecked();
    }
}
