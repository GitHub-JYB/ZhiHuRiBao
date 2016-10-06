package admin_jyb.zhihuribao.mvp.model.Impl;

import admin_jyb.zhihuribao.bean.StartImage;
import admin_jyb.zhihuribao.mvp.model.SplashModel;
import admin_jyb.zhihuribao.mvp.presenter.Impl.SplashPresenterImpl;
import admin_jyb.zhihuribao.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/9/25.
 */

public class SplashModelImpl implements SplashModel {

    private final SplashPresenterImpl presenter;

    public SplashModelImpl(SplashPresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getStartImage() {
        ApiClient.getService()
                .getStartImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StartImage>() {
                    @Override
                    public void onCompleted() {
                        presenter.showAnimation();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StartImage startImage) {
                        presenter.sendImageToView(startImage);
                    }
                });
    }
}
