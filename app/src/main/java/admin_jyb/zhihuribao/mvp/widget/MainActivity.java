package admin_jyb.zhihuribao.mvp.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.mvp.presenter.Impl.MainPresenterImpl;
import admin_jyb.zhihuribao.mvp.view.MainView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        initToolbar();
        initToggle();
        initDrawerContent();
        replaceFragment(null,HomeFragment.getInstance());
    }

    private void initDrawerContent() {
        navigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                presenter.switchNavition(item);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)
        ||super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void closeDrawers() {
        drawerlayout.closeDrawers();
    }

    @Override
    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (newFragment instanceof DetailFragment){
            transaction.hide(oldFragment).addToBackStack(null);
        }
        transaction.replace(R.id.content,newFragment).commit();
    }

    /**
     *  关联drawerlayout和toolbar
     */
    private void initToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerlayout,toolbar, R.string.open, R.string.close);
        drawerlayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void initToolbar() {
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onReplaceFragment(HomeFragment homeFragment, int storyId) {
        replaceFragment(homeFragment,DetailFragment.getInstance(storyId));
    }
}
