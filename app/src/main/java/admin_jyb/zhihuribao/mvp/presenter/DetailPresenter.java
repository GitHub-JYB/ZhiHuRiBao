package admin_jyb.zhihuribao.mvp.presenter;

import admin_jyb.zhihuribao.bean.ZhiHu;

/**
 * Created by Admin-JYB on 2016/9/28.
 */

public interface DetailPresenter {

    void getStoryFromModel(int storyId);

    void sendStoriesToView(ZhiHu.Stories stories);
}
