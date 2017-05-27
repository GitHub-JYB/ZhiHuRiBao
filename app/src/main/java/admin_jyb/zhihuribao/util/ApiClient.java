package admin_jyb.zhihuribao.util;

import admin_jyb.zhihuribao.bean.Girl;
import admin_jyb.zhihuribao.bean.StartImage;
import admin_jyb.zhihuribao.bean.ZhiHu;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Admin-JYB on 2016/9/25.
 */

public class ApiClient {

    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String Gril_URL = "http://image.baidu.com/data/";

    private static Retrofit retrofit = null;

    public interface ApiService{

        @GET("imgs?col=美女&tag=美女&sort=0&pn=20&rn=20&p=channel&from=1")
        Observable<Girl>getGirl();

        @GET("start-image/1080*1776")
        Observable<StartImage> getStartImage();

        @GET("news/latest")
        Observable<ZhiHu> getZhiHu();

        @GET("news/before/{date}")
        Observable<ZhiHu> getBeforeZhiHu(@Path("date") String date);

        @GET("news/{id}")
        Observable<ZhiHu.Stories> getStories(@Path("id") int id);
    }

    private static Retrofit getClient(String url){
        if (url.equals(BASE_URL)){
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        }else if (url.equals(Gril_URL)){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Gril_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getService(String url){
        return getClient(url).create(ApiService.class);
    }

}
