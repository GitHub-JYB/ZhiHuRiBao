package admin_jyb.zhihuribao.mvp.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.bean.ZhiHu;
import admin_jyb.zhihuribao.mvp.adapter.StoryAdapter;
import admin_jyb.zhihuribao.mvp.presenter.Impl.HomePresenterImpl;
import admin_jyb.zhihuribao.mvp.view.HomeView;
import admin_jyb.zhihuribao.util.DateUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2016/9/26.
 */
public class HomeFragment extends Fragment implements HomeView{


    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private HomePresenterImpl presenter;
    private StoryAdapter storyAdapter;
    private Context context;

    public HomeFragment() {
    }

    private static HomeFragment instance = new HomeFragment();

    public static HomeFragment getInstance() {
        if (instance == null){
            synchronized (HomeFragment.class){
                if (instance == null){
                    instance = new HomeFragment();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new HomePresenterImpl(this);
    }

    @Override
    public void showStories(List<ZhiHu.Stories> stories,
                            List<ZhiHu.TopStories> top_stories) {
        storyAdapter.setData(stories,top_stories);
        cancelRefresh();

    }

    @Override
    public void cancelRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showBeforeStories(List<ZhiHu.Stories> stories) {
        storyAdapter.addStories(stories);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        storyAdapter = new StoryAdapter(recyclerView);
        recyclerView.setAdapter(storyAdapter);
        storyAdapter.setOnLoadMoreListener(new StoryAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.getBeforeStory(DateUtil.getPreviousDay());
            }
        });

        storyAdapter.setOnClickListener(new StoryAdapter.OnClickListener() {
            @Override
            public void onClick(int storyId) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("storyId",storyId);
                context.startActivity(intent);
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.getStoriesFromModel();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getStoriesFromModel();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
