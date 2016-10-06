package admin_jyb.zhihuribao.mvp.presenter.Impl;

import admin_jyb.zhihuribao.bean.StartImage;
import admin_jyb.zhihuribao.mvp.model.Impl.SplashModelImpl;
import admin_jyb.zhihuribao.mvp.presenter.SplashPresenter;
import admin_jyb.zhihuribao.mvp.view.SplashView;

/**
 * Created by Admin-JYB on 2016/9/25.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private final SplashView splashView;
    private final SplashModelImpl splashModelImpl;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
        this.splashModelImpl = new SplashModelImpl(this);
    }


    @Override
    public void getStartImage() {
        splashModelImpl.getStartImage();
    }

    @Override
    public void sendImageToView(StartImage startImage) {
        splashView.showImageAndText(startImage.getImg(),startImage.getText());
    }

    @Override
    public void showAnimation() {
        splashView.showAnimation();
    }
}
