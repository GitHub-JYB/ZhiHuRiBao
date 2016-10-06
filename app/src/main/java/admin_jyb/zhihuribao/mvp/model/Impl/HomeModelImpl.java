package admin_jyb.zhihuribao.mvp.model.Impl;

import android.util.Log;

import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.model.HomeModel;
import admin_jyb.zhihuribao.mvp.presenter.Impl.HomePresenterImpl;
import admin_jyb.zhihuribao.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/9/27.
 */

public class HomeModelImpl implements HomeModel {
    private final HomePresenterImpl presenter;

    public HomeModelImpl(HomePresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getStories() {
        ApiClient.getService()
                .getZhiHu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHu>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhiHu zhiHu) {
                        presenter.sendStoriesToView(zhiHu);
                    }
                });
    }

    @Override
    public void getBeforeStories(String previousDay) {

        ApiClient.getService()
                .getBeforeZhiHu(previousDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHu>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhiHu zhiHu) {
                        presenter.sendBeforeStoriesToView(zhiHu);
                    }
                });
    }
}
