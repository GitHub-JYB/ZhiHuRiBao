package admin_jyb.zhihuribao.mvp.presenter;

import admin_jyb.zhihuribao.bean.ZhiHu;

/**
 * Created by Admin-JYB on 2016/9/27.
 */

public interface HomePresenter {

    void getStoriesFromModel();

    void sendStoriesToView(ZhiHu zhiHu);

    void getBeforeStory(String previousDay);


    void sendBeforeStoriesToView(ZhiHu zhiHu);
}
