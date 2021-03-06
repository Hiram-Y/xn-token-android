package com.cdkj.token.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.token.R;
import com.cdkj.token.Util.AccountUtil;
import com.cdkj.token.model.BillModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DATE_DAY;
import static com.cdkj.baselibrary.utils.DateUtil.DATE_HM;
import static com.cdkj.baselibrary.utils.DateUtil.DATE_M;
import static com.cdkj.baselibrary.utils.DateUtil.DATE_YM;
import static com.cdkj.token.Util.CoinUtil.getCoinWatermarkWithCurrency;

/**
 * Created by lei on 2017/8/22.
 */

public class BillAdapter extends BaseQuickAdapter<BillModel.ListBean,BaseViewHolder> {

    List<BillModel.ListBean> list;

    public BillAdapter(@Nullable List<BillModel.ListBean> data) {
        super (R.layout.item_bill,data);
    }

    @NonNull
    @Override
    public List<BillModel.ListBean> getData() {
        return super.getData();
    }

    @Override
    protected void convert(BaseViewHolder helper, BillModel.ListBean item) {
        if (list == null){
            list = getData();
        }

        // 当itemPosition为0时，展示日期
        if (helper.getLayoutPosition() == 0){
            helper.setVisible(R.id.tv_ym, true);
            helper.setText(R.id.tv_ym, DateUtil.formatStringData(item.getCreateDatetime(),DATE_YM));
        }else { // 当itemPosition不为0但当前item的日期与上一个item不相同时，展示日期，否则不展示
            String month_now = DateUtil.formatStringData(item.getCreateDatetime(),DATE_M);
            String month_last = DateUtil.formatStringData(list.get(helper.getLayoutPosition()-1).getCreateDatetime(),DATE_M);

            if (!month_now.equals(month_last)){
                helper.setVisible(R.id.tv_ym, true);
                helper.setText(R.id.tv_ym, DateUtil.formatStringData(item.getCreateDatetime(),DATE_YM));
            }else {
                helper.setVisible(R.id.tv_ym, false);
            }
        }

        helper.setText(R.id.tv_day, DateUtil.formatStringData(item.getCreateDatetime(),DATE_DAY));
        helper.setText(R.id.tv_time, DateUtil.formatStringData(item.getCreateDatetime(),DATE_HM));

        helper.setText(R.id.tv_remark, item.getBizNote());
        helper.setText(R.id.tv_currency, item.getCurrency());

        BigDecimal tas = new BigDecimal(item.getTransAmountString());
        int i=tas.compareTo(BigDecimal.ZERO);
        if (i==1){
            helper.setText(R.id.tv_amount, "+" + AccountUtil.amountFormatUnit(tas, item.getCurrency(), 8));
        }else {
            helper.setText(R.id.tv_amount, AccountUtil.amountFormatUnit(tas, item.getCurrency(), 8));
        }

        ImageView ivType = helper.getView(R.id.iv_type);
        if (item.getKind().equals("0")){ // 非冻结流水

            switch (item.getBizType()){
                case "charge": // 充值
                case "o2o_in": // o2o店铺消费收入
                case "invite": // 推荐好友分成

                    ImgUtils.loadImage(mContext, getCoinWatermarkWithCurrency(item.getCurrency(),2), ivType);

                    break;

                case "withdraw": // 取现
                case "tradefee": // 手续费
                case "withdrawfee": // 手续费
                case "o2o_out": // o2o店铺消费支出

                    ImgUtils.loadImage(mContext, getCoinWatermarkWithCurrency(item.getCurrency(),3), ivType);

                    break;
            }
        }else { // 冻结流水

            if (item.getTransAmountString().contains("-")){ // 金额是负数
                ImgUtils.loadImage(mContext, getCoinWatermarkWithCurrency(item.getCurrency(),3), ivType);
            } else {
                ImgUtils.loadImage(mContext, getCoinWatermarkWithCurrency(item.getCurrency(),2), ivType);
            }

        }

//        if (item.getKind().equals("0")){ // 非冻结流水
//            int resId = 0;
//            switch (item.getBizType()){
//                case "charge": // 充值
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_charge", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//                case "withdraw": // 取现
//                case "autofill": // 自动补给
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_withdraw", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//                case "buy": // 买入
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_into", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//                case "sell": // 卖出
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_out", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//                case "tradefee": // 手续费
//                case "withdrawfee": // 手续费
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_fee", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//                default:
//
//                    resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_award", "mipmap" , mContext.getPackageName());
//                    if (resId != 0)
//                        helper.setImageResource(R.id.iv_type, resId);
//
//                    break;
//
//            }
//        }else { // 冻结流水
//
//            int resId;
//
//            if (item.getTransAmountString().contains("-")){ // 金额是负数
//
//                resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_withdraw", "mipmap" , mContext.getPackageName());
//                if (resId != 0)
//                    helper.setImageResource(R.id.iv_type, resId);
//
//            } else {
//
//                resId = mContext.getResources().getIdentifier("bill_"+item.getCurrency().toLowerCase()+"_charge", "mipmap" , mContext.getPackageName());
//                if (resId != 0)
//                    helper.setImageResource(R.id.iv_type, resId);
//            }
//
//        }
    }
}
