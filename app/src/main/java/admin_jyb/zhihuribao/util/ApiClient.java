package admin_jyb.zhihuribao.util;

import admin_jyb.zhihuribao.bean.StartImage;
import admin_jyb.zhihuribao.bean.ZhiHu;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Admin-JYB on 2016/9/25.
 */

public class ApiClient {

    private static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

    private static Retrofit retrofit = null;

    public interface ApiService{

        @GET("start-image/1080*1776")
        Observable<StartImage> getStartImage();

        @GET("news/latest")
        Observable<ZhiHu> getZhiHu();

        @GET("news/before/{date}")
        Observable<ZhiHu> getBeforeZhiHu(@Path("date") String date);

        @GET("news/{id}")
        Observable<ZhiHu.Stories> getStories(@Path("id") int id);
    }

    private static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getService(){
        return getClient().create(ApiService.class);
    }

}
