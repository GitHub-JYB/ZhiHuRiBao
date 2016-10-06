package admin_jyb.zhihuribao.mvp.presenter;

import admin_jyb.zhihuribao.bean.StartImage;

/**
 * Created by Admin-JYB on 2016/9/25.
 */

public interface SplashPresenter {

    void getStartImage();

    void sendImageToView(StartImage startImage);

    void showAnimation();
}
