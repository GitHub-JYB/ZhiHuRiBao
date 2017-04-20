package admin_jyb.zhihuribao.mvp.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.util.SpUtil;
import admin_jyb.zhihuribao.util.ToastUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Admin-JYB on 2017/4/19.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    public void login(View view){
        if (username.getText().toString().trim().isEmpty() ||
                password.getText().toString().trim().isEmpty()){
            ToastUtil.showShortToast("用户名密码不能为空");
        }else {
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(username.getText().toString().trim());
            bmobUser.setPassword(password.getText().toString().trim());
            bmobUser.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null){
                        ToastUtil.showShortToast("登录成功");
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        SpUtil.getIntance(getBaseContext()).setUsername(bmobUser.getUsername());
                        finish();
                    }else {
                        switch (e.getErrorCode()){
                            case 101:
                                ToastUtil.showShortToast("用户名或密码不正确");
                                break;
                            case 9016:
                                ToastUtil.showShortToast("无网络连接，请检查您的手机网络");
                                break;
                            default:
                                ToastUtil.showShortToast(e.getMessage());
                                break;
                        }

                    }
                }
            });
        }
    }
}
