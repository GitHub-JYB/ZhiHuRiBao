package admin_jyb.zhihuribao.mvp.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import admin_jyb.zhihuribao.R;
import admin_jyb.zhihuribao.util.ToastUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2017/4/19.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void register(View view){
        if (password.getText().toString() != confirmPassword.getText().toString()){
            ToastUtil.showShortToast("前后密码不一致");
        }else if (username.getText().toString().trim().isEmpty()){
            ToastUtil.showShortToast("邮箱不能为空");
        }else {
//            BmobUser bmobUser = new BmobUser();
//            bmobUser.setUsername(username.getText().toString().trim());
//            bmobUser.setPassword(password.getText().toString().trim());
//            bmobUser.setEmail(username.getText().toString().trim());
//            bmobUser.signUp(new SaveListener<BmobUser>() {
//                @Override
//                public void done(BmobUser bmobUser, BmobException e) {
//                    if (e == null){
//                        finish();
//                    }else {
//                        ToastUtil.showShortToast(e.getMessage());
//                    }
//                }
//            });
        }
    }
}
