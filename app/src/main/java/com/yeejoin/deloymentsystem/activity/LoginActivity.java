package com.yeejoin.deloymentsystem.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.widget.EditText;

import com.yeejoin.deloymentsystem.MyApplication;
import com.yeejoin.deloymentsystem.R;
import com.yeejoin.deloymentsystem.base.BaseActivity;
import com.yeejoin.deloymentsystem.viewmodel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by maodou on 2017/12/4.
 * 登录界面
 */

public class LoginActivity extends BaseActivity {
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
                            showToast("登录成功");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else
                            showToast("登录失败");
                    });
        }
    }

    private LoginViewModel viewModel;

    @Override
    protected int getResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getLastLogin().observe(this,login -> {
           if (login != null && login.user != null)
               mEtAccount.setText(login.user.userName);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
