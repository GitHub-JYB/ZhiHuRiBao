package admin_jyb.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.bean.Girl;
import admin_jyb.zhihuribao.mvp.adapter.GirlAdapter;
import admin_jyb.zhihuribao.util.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2017/5/3.
 */

public class GirlFragment extends Fragment{

    @BindView(R.id.girl_list)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private GirlAdapter adapter;

    public GirlFragment() {
    }

    private static GirlFragment instance = new GirlFragment();

    public static GirlFragment getInstance() {
        if (instance == null){
            synchronized (GirlFragment.class){
                if (instance == null){
                    instance = new GirlFragment();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_girl, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initRecyclerView();
        return view;
    }

    private void getData() {
        ApiClient.getService(ApiClient.Gril_URL)
                .getGirl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Girl>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Girl girl) {
                        adapter.setData(girl.getImgs().subList(0,girl.getImgs().size()-1));
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
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
                getData();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GirlAdapter();
        adapter.setOnClickListener(new GirlAdapter.OnClickListener() {
            @Override
            public void onClick(Girl.imgs imas) {
                Intent intent = new Intent(getContext(), GirlDetailActivity.class);
                intent.putExtra("url",imas.getImageUrl());
                intent.putExtra("desc",imas.getDesc());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
