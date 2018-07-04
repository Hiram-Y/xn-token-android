package com.cdkj.token.user;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.activitys.WebViewActivity;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.token.model.db.LocalCoinDbModel;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.LoginFailureEvent;
import com.cdkj.baselibrary.model.UserInfoModel;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.token.R;
import com.cdkj.token.api.MyApi;
import com.cdkj.token.databinding.FragmentUserBinding;
import com.cdkj.token.wallet.trusteeship.WalletUserActivity;

import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;


/**
 * 我的
 * Created by lei on 2017/8/21.
 */
public class UserFragment extends BaseLazyFragment {

    private FragmentUserBinding mBinding;

    public final int PHOTOFLAG = 110;

    private boolean isFirstCoinListRequest;//记录是否请求了币种

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static UserFragment getInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, null, false);

        initListener();

        return mBinding.getRoot();
    }


    private void initListener() {

        mBinding.linLayoutLogo.setOnClickListener(view -> {
            if (!SPUtilHelper.isLogin(mActivity, false)) {
                return;
            }
            ImageSelectActivity.launchFragment(this, PHOTOFLAG);
        });

        mBinding.llWalletSet.setOnClickListener(view -> {
            UserWalletActivity.open(mActivity);
        });

        mBinding.llSetting.setOnClickListener(view -> {
            if (!SPUtilHelper.isLogin(mActivity, false)) {
                return;
            }
            UserSettingActivity.open(mActivity);
        });
        //中心化钱包
        mBinding.linLayoutUserAccount.setOnClickListener(view -> {
            if (!SPUtilHelper.isLogin(mActivity, false)) {
                return;
            }

            if (isFirstCoinListRequest) {  //如果已经获取过币种 则不再获取
                WalletUserActivity.open(mActivity);
            } else {
                //获取配置币种列表
                getCoinList();
            }

        });

        mBinding.llJoin.setOnClickListener(view -> {
            if (!SPUtilHelper.isLogin(mActivity, false)) {
                return;
            }
            UserJoinActivity.open(mActivity);
        });

        mBinding.llIssue.setOnClickListener(view -> {
            WebViewActivity.openkey(mActivity, mBinding.tvIssue.getText().toString(), "questions");
//            new SupportActivity.Builder().show(getActivity());
        });

        mBinding.llAbout.setOnClickListener(view -> {
//            WebViewActivity.openkey(mActivity, mBinding.tvAbout.getText().toString(),"about_us");
            UserAboutActivity.open(mActivity);
        });
    }


    private void getCoinList() {

        Map<String, String> map = new HashMap<>();
        map.put("type", "");
        map.put("ename", "");
        map.put("cname", "");
        map.put("symbol", "");
        map.put("status", "0"); // 0已发布，1已撤下
        map.put("contractAddress", "");

        Call call = RetrofitUtils.createApi(MyApi.class).getCoinList("802267", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<LocalCoinDbModel>(mActivity) {

            @Override
            protected void onSuccess(List<LocalCoinDbModel> data, String SucMessage) {
                if (data == null) {
                    WalletUserActivity.open(mActivity);
                    return;
                }
                // 如果数据库已有数据，清空重新加载
                if (DataSupport.isExist(LocalCoinDbModel.class)) {
                    DataSupport.deleteAll(LocalCoinDbModel.class);
                }

                // 初始化交易界面默认所选择的币
                if (data.size() > 0) {
                    data.get(0).setChoose(true);
                }
                DataSupport.saveAll(data);
                disMissLoading();
                isFirstCoinListRequest = true;
                WalletUserActivity.open(mActivity);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                disMissLoading();
//                WalletUserActivity.open(mActivity);
            }

            @Override
            protected void onFinish() {
            }
        });
    }


    @Override
    protected void lazyLoad() {
        if (mBinding != null) {
            getUserInfoRequest();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!SPUtilHelper.getUserId().equals("")) {
//            // 已登陆时初始化登录用户的用户信息
//            getUserInfoRequest();
//        }

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        if (requestCode == PHOTOFLAG) {
            showLoadingDialog();
            String path = data.getStringExtra(CameraHelper.staticPath);
            new QiNiuHelper(mActivity).uploadSinglePic(new QiNiuHelper.QiNiuCallBack() {
                @Override
                public void onSuccess(String key) {
                    updateUserPhoto(key);
                }


                @Override
                public void onFal(String info) {
                    disMissLoading();
                    ToastUtil.show(mActivity, info);
                }
            }, path);

        }
    }

    /**
     * 更新用户头像
     *
     * @param key
     */
    private void updateUserPhoto(final String key) {
        Map<String, String> map = new HashMap<>();
        map.put("photo", key);
        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("805080", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    getUserInfoRequest();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest() {

        if (!SPUtilHelper.isLoginNoStart()) {
            setShowData(null);
            return;
        }

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApi.class).getUserInfoDetails("805121", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<UserInfoModel>(mActivity) {
            @Override
            protected void onSuccess(UserInfoModel data, String SucMessage) {
                if (data == null)
                    return;

                setShowData(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setShowData(UserInfoModel data) {
        if (data == null) {
            mBinding.tvNick.setText("");
            mBinding.tvMobile.setText("");
//        ImgUtils.loadAvatar(mActivity, data.getPhoto(), data.getNickname(), mBinding.imAvatar, mBinding.tvAvatar);
            mBinding.imAvatar.setImageResource(R.mipmap.default_photo);
            return;
        }

        SPUtilHelper.saveSecretUserId(data.getSecretUserId());

        SPUtilHelper.saveUserPhoto(data.getPhoto());
        SPUtilHelper.saveUserEmail(data.getEmail());
        SPUtilHelper.saveUserName(data.getNickname());
        SPUtilHelper.saveRealName(data.getRealName());
        SPUtilHelper.saveUserPhoneNum(data.getMobile());
        SPUtilHelper.saveTradePwdFlag(data.isTradepwdFlag());
        SPUtilHelper.saveGoogleAuthFlag(data.isGoogleAuthFlag());

        if (data.getNickname() == null)
            return;

        mBinding.tvNick.setText(data.getNickname());
        mBinding.tvMobile.setText(data.getMobile());
        ImgUtils.loadLogo(mActivity, data.getPhoto(), mBinding.imAvatar);
    }


    //登录失效
    @Subscribe
    public void loginFailureEvent(LoginFailureEvent loginFailureEvent) {
        setShowData(null);
    }


}
