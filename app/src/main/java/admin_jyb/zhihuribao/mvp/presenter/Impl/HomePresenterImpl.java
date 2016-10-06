package admin_jyb.zhihuribao.mvp.presenter.Impl;

import java.util.List;

import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.model.Impl.HomeModelImpl;
import admin_jyb.zhihuribao.mvp.presenter.HomePresenter;
import admin_jyb.zhihuribao.mvp.view.HomeView;

/**
 * Created by Admin-JYB on 2016/9/27.
 */

public class HomePresenterImpl implements HomePresenter {

    private final HomeView homeView;
    private final HomeModelImpl homeModelImpl;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        this.homeModelImpl = new HomeModelImpl(this);
    }

    @Override
    public void getStoriesFromModel() {
        homeModelImpl.getStories();
    }

    @Override
    public void sendStoriesToView(ZhiHu zhiHu) {
        homeView.showStories(zhiHu.getStories(),zhiHu.getTop_stories());
    }

    @Override
    public void getBeforeStory(String previousDay) {
        homeModelImpl.getBeforeStories(previousDay);
    }

    @Override
    public void sendBeforeStoriesToView(ZhiHu zhiHu) {
        homeView.showBeforeStories(zhiHu.getStories());
    }
}
