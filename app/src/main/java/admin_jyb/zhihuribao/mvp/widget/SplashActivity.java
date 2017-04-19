package admin_jyb.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.mvp.presenter.Impl.SplashPresenterImpl;
import admin_jyb.zhihuribao.mvp.view.SplashView;
import admin_jyb.zhihuribao.util.SpUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.text)
    TextView text;
    private SplashPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setPresenter();
        presenter.getStartImage();
    }

    private void finishSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (SpUtil.getIntance(getBaseContext()).getUsername() == ""){
                            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                            finish();
                        }else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public void setPresenter() {
        presenter = new SplashPresenterImpl(this);
    }

    @Override
    public void showImageAndText(String img, String text) {
        Picasso.with(this).load(img).fit().into(iv);
        this.text.setText(text);
    }

    @Override
    public void showAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        iv.startAnimation(animation);
        finishSplash();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
