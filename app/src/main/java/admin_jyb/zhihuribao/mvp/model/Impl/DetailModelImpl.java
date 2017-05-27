package admin_jyb.zhihuribao.mvp.model.Impl;

import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.model.DetailModel;
import admin_jyb.zhihuribao.mvp.presenter.Impl.DetailPresenterImpl;
import admin_jyb.zhihuribao.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/9/28.
 */

public class DetailModelImpl implements DetailModel {
    private final DetailPresenterImpl presenter;

    public DetailModelImpl(DetailPresenterImpl detailPresenter) {
        this.presenter = detailPresenter;
    }

    @Override
    public void getStory(int storyId) {
        ApiClient.getService(ApiClient.BASE_URL)
                .getStories(storyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHu.Stories>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ZhiHu.Stories stories) {
                        presenter.sendStoriesToView(stories);
                    }
                });
    }
}
