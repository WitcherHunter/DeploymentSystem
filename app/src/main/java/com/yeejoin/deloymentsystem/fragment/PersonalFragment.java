package com.yeejoin.deloymentsystem.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yeejoin.deloymentsystem.R;
import com.yeejoin.deloymentsystem.data.model.entity.Login;
import com.yeejoin.deloymentsystem.data.model.entity.User;
import com.yeejoin.deloymentsystem.viewmodel.PersonalViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maodou on 2017/12/28.
 */

public class PersonalFragment extends Fragment {
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.tvNameAndRole)
    TextView mTvNameAndRole;
    @BindView(R.id.tvCompany)
    TextView mTvCompany;
    @BindView(R.id.tvPhone)
    TextView mTvPhone;

    private PersonalViewModel mViewModel = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init(){
        mViewModel = ViewModelProviders.of(this).get(PersonalViewModel.class);

        mViewModel.getLoginInfo()
                .observe(this, login -> {
                    if (login != null && login.user != null)
                        setData(login.user);
                });
    }

    private void setData(User user){
        Glide.with(this)
                .load(R.drawable.default_avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(mIvAvatar);
        mTvNameAndRole.setText(user.name + " " + user.role.name);
        mTvPhone.setText(user.mobile == null ? "" : user.mobile);
        mTvCompany.setText(user.company.companyName);
    }
}
