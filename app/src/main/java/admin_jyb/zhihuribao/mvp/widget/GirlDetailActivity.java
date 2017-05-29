package admin_jyb.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import admin_jyb.zhihuribao.Application.MyApplication;
import admin_jyb.zhihuribao.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2017/5/27.
 */

public class GirlDetailActivity extends AppCompatActivity {

    @BindView(R.id.girl_big_image)
    ImageView girlBigImage;
    @BindView(R.id.girl_detail_desc)
    TextView girlDetailDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_girldetail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String desc = intent.getStringExtra("desc");
        Picasso.with(getBaseContext())
                .load(url)
                .placeholder(R.drawable.login_wallpaper2)
                .error(R.drawable.login_wallpaper2)
                .resize(MyApplication.widthPixels,MyApplication.heightPixels - MyApplication.statusHight)
                .into(girlBigImage);
        girlDetailDesc.setTextColor(getResources().getColor(android.R.color.white));
        girlDetailDesc.setTextSize(24);
        girlDetailDesc.setText(desc);
    }

    public void toggle(View view){
        int visibility = girlDetailDesc.getVisibility();
        if (visibility != View.GONE){
            girlDetailDesc.setVisibility(View.GONE);
        }
        if (visibility == View.GONE){
            girlDetailDesc.setVisibility(View.VISIBLE);
        }
    }
}
