package com.yeejoin.deloymentsystem.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.yeejoin.deloymentsystem.activity.LoginActivity;
import com.yeejoin.deloymentsystem.activity.MainActivity;
import com.yeejoin.deloymentsystem.activity.SplashActivity;
import com.yeejoin.deloymentsystem.data.Injection;
import com.yeejoin.deloymentsystem.viewmodel.BaseViewModel;

import butterknife.ButterKnife;

/**
 * Created by maodou on 2017/12/4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private WindowManager windowManager = null;
    private BaseViewModel baseViewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        ButterKnife.bind(this);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        getLogin();

        init();
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("BaseActivity: onResume");
//
//    }

    private void getLogin(){
        baseViewModel.getLastLogin()
                .observe(this, login -> {
                    if (login != null && login.token != null) {
                        validToken(login.token);
                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    }
                });
    }

    private void validToken(String token){
        baseViewModel.tokenValid(token)
                .observe(this, valid -> {
                    if (!valid){
                        showToast("用户登录已过期");
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    } else {
                        showToast("token验证成功");
                    }
                });
    }

    /**
     * @return 屏幕宽度
     */
    private int getScreenWidth(){
        return windowManager == null ? -1 : windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * @return 屏幕高度
     */
    private int getScreenHeight(){
        return windowManager == null ? -1 : windowManager.getDefaultDisplay().getHeight();
    }

    protected abstract int getResourceId();

    protected abstract void init();

    protected void showToast(@StringRes int resId) throws Resources.NotFoundException{
        makeText(BaseActivity.this.getResources().getText(resId));
    }

    protected void showToast(CharSequence charsequence){
        makeText(charsequence);
    }

    private void makeText(CharSequence charsequence){
        String tag = "BaseActivity.showToast";
        Context applicationContext = getApplicationContext();
        try {
            Toast.makeText(applicationContext, charsequence, Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e){
            Log.e(tag,"Context cannot be null!");
        }
    }
}
