package com.cdkj.token.wallet.create_guide;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.CommonDialog;
import com.cdkj.token.R;
import com.cdkj.token.databinding.ActivityWalletWordsShowBinding;
import com.cdkj.token.utils.WalletHelper;

/**
 * 助记词显示
 * Created by cdkj on 2018/6/6.
 */

public class WalletHelpWordsShowActivity extends AbsBaseLoadActivity {

    private ActivityWalletWordsShowBinding mBinding;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, WalletHelpWordsShowActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_wallet_words_show, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        CommonDialog commonDialog = new CommonDialog(this).builder()
                .setTitle(getString(com.cdkj.baselibrary.R.string.activity_base_tip)).setContentMsg(getString(R.string.dont_screenshot))
                .setPositiveBtn(getString(com.cdkj.baselibrary.R.string.activity_base_confirm), null);

        commonDialog.show();

        mBinding.tvWords.setText(WalletHelper.getHelpWords("    "));
        mBinding.btnNowBackup.setOnClickListener(view -> {
            WalletBackupCheckActivity.open(this);
            finish();
        });
    }
}
