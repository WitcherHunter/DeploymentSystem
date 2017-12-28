package com.yeejoin.commonapplication.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.yeejoin.commonapplication.R;
import com.yeejoin.commonapplication.utils.Util;
import com.yeejoin.commonapplication.viewmodel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 /**
 * Created by maodou on 2017/12/28.
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity{
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @OnClick(R.id.btn_login)
    void login() {
        if (mEtAccount.getText() != null && mEtPassword.getText() != null) {
            String username = mEtAccount.getText().toString();
            String password = mEtPassword.getText().toString();

            viewModel.login(username, password)
                    .observe(LoginActivity.this, login -> {
                        if (login != null && login.token != null){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else
                            Util.showSnackbar(mParentView,"登录失败");
                    });
        }
    }

    private LoginViewModel viewModel = null;
    private View mParentView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mParentView = getWindow().getDecorView();

        if (!Util.isNetworkConnected(this))
            Util.showSnackbar(mParentView,"无网络连接");

        init();
    }

    protected void init() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getNetConfig().observe(this,observer -> {
            if (observer == null || observer.size() == 0) {
                Util.showSnackbar(mParentView, "网络配置获取失败!");
            }
        });

        viewModel.getLastLogin().observe(this,login -> {
           if (login != null && login.user != null)
               mEtAccount.setText(login.user.userName);
        });
    }
}
