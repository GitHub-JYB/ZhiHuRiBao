package admin_jyb.zhihuribao.mvp.model;

/**
 * Created by Admin-JYB on 2016/9/27.
 */

public interface HomeModel {
    void getStories();

    void getBeforeStories(String previousDay);
}
