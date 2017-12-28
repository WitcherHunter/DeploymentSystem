package com.yeejoin.deloymentsystem.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.yeejoin.deloymentsystem.utils.Util;

import butterknife.ButterKnife;

/**
 * Created by maodou on 2017/12/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private WindowManager windowManager = null;
    private View mParentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        ButterKnife.bind(this);
        mParentView = getWindow().getDecorView();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        init();
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

    protected void showSnackBar(CharSequence msg){
        Util.showSnackbar(mParentView,msg);
    }

    protected void showSnackBar(@StringRes int resId){
        Util.showSnackbar(mParentView,getResources().getText(resId));
    }
}
