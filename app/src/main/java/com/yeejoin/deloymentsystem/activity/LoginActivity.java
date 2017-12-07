package com.yeejoin.deloymentsystem.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

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
                            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        System.out.println("LoginActivity: " + isTaskRoot());

        init();
    }

    protected void init() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getLastLogin().observe(this,login -> {
           if (login != null && login.user != null)
               mEtAccount.setText(login.user.userName);
        });
    }
}
