package admin_jyb.zhihuribao.mvp.presenter.Impl;

import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.model.Impl.DetailModelImpl;
import admin_jyb.zhihuribao.mvp.presenter.DetailPresenter;
import admin_jyb.zhihuribao.mvp.view.DetailView;

/**
 * Created by Admin-JYB on 2016/9/28.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private final DetailView detailView;
    private final DetailModelImpl detailModel;

    public DetailPresenterImpl(DetailView detailView) {
        this.detailView = detailView;
        this.detailModel = new DetailModelImpl(this);
    }

    @Override
    public void getStoryFromModel(int storyId) {
        detailModel.getStory(storyId);
    }

    @Override
    public void sendStoriesToView(ZhiHu.Stories stories) {
        detailView.showStories(stories);
    }
}
