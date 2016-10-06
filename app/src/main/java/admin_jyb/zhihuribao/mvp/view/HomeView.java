package admin_jyb.zhihuribao.mvp.view;

import java.util.List;

import admin_jyb.zhihuribao.bean.ZhiHu;

/**
 * Created by Admin-JYB on 2016/9/27.
 */

public interface HomeView {

    void setPresenter();

    void showStories(List<ZhiHu.Stories> stories, List<ZhiHu.TopStories> top_stories);

    void cancelRefresh();

    void showBeforeStories(List<ZhiHu.Stories> stories);
}
