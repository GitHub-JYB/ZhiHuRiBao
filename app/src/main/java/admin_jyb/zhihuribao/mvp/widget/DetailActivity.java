package admin_jyb.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.presenter.Impl.DetailPresenterImpl;
import admin_jyb.zhihuribao.mvp.view.DetailView;
import admin_jyb.zhihuribao.util.HtmlUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2016/9/28.
 */
public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.iv_story_header)
    ImageView ivStoryHeader;
    @BindView(R.id.tv_story_title)
    TextView tvStoryTitle;
    @BindView(R.id.tv_copyright)
    TextView tvCopyright;
    @BindView(R.id.wb_story)
    WebView wbStory;
    private DetailPresenterImpl presenter;
    private int storyId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        presenter = new DetailPresenterImpl(this);
        Intent intent = getIntent();
        storyId = intent.getIntExtra("storyId",0);
        if (storyId != 0) {
            presenter.getStoryFromModel(storyId);
        }
    }

    @Override
    public void showStories(ZhiHu.Stories stories) {
        String body = HtmlUtil.formatHtml(stories.getBody());
        wbStory.loadDataWithBaseURL("file:///android_assets/",body,"text/html","UTF-8","");
        tvStoryTitle.setText(stories.getTitle());
        tvCopyright.setText(stories.getImage_source());
        Picasso.with(getBaseContext())
                .load(stories.getImage())
                .fit()
                .centerCrop()
                .into(ivStoryHeader);
    }
}
