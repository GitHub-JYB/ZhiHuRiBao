package admin_jyb.zhihuribao.mvp.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class DetailFragment extends Fragment implements DetailView {

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

    public DetailFragment() {
    }

    private static DetailFragment instance = new DetailFragment();

    public static DetailFragment getInstance(int storyId) {
        if (instance == null) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new DetailFragment();
                }
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("storyId", storyId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenterImpl(this);
        if (getArguments() != null) {
            storyId = getArguments().getInt("storyId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        ButterKnife.bind(this, view);
        presenter.getStoryFromModel(storyId);
        return view;
    }

    @Override
    public void showStories(ZhiHu.Stories stories) {
        String body = HtmlUtil.formatHtml(stories.getBody());
        wbStory.loadDataWithBaseURL("file:///android_assets/",body,"text/html","UTF-8","");
        tvStoryTitle.setText(stories.getTitle());
        tvCopyright.setText(stories.getImage_source());
        Picasso.with(getContext())
                .load(stories.getImage())
                .fit()
                .centerCrop()
                .into(ivStoryHeader);
    }
}
