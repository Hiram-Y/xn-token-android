package com.cdkj.token.api;

import com.cdkj.baselibrary.api.BaseResponseListModel;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.model.BaseCoinModel;
import com.cdkj.baselibrary.model.UserInfoModel;
import com.cdkj.baselibrary.model.UserLoginModel;
import com.cdkj.token.model.AddressModel;
import com.cdkj.token.model.BannerModel;
import com.cdkj.token.model.BillModel;
import com.cdkj.token.model.CoinModel;
import com.cdkj.token.model.ConsultListModel;
import com.cdkj.token.model.ConsultModel;
import com.cdkj.token.model.ConsultingModel;
import com.cdkj.token.model.DealDetailModel;
import com.cdkj.token.model.DealHistoryModel;
import com.cdkj.token.model.DealModel;
import com.cdkj.token.model.DealResultModel;
import com.cdkj.token.model.DealUserDataModel;
import com.cdkj.token.model.ExchangeModel;
import com.cdkj.token.model.InviteModel;
import com.cdkj.token.model.KtInfoModel;
import com.cdkj.token.model.MarketCoinModel;
import com.cdkj.token.model.MarketModel;
import com.cdkj.token.model.OrderDetailModel;
import com.cdkj.token.model.OrderModel;
import com.cdkj.token.model.RateModel;
import com.cdkj.token.model.StatisticsListModel;
import com.cdkj.token.model.SystemMessageModel;
import com.cdkj.token.model.SystemParameterListModel;
import com.cdkj.token.model.SystemParameterModel;
import com.cdkj.token.model.TrustModel;
import com.cdkj.token.model.UserRefereeModel;
import com.cdkj.token.model.UserSettingModel;
import com.cdkj.token.model.VersionModel;
import com.cdkj.token.model.WithdrawOrderModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lei on 2017/10/19.
 */

public interface MyApi {


    /**
     * 获取空投流水
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<StatisticsListModel>> getKtBillList(@Field("code") String code, @Field("json") String json);


    /**
     * 获取空投信息
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<KtInfoModel>> getTkInfo(@Field("code") String code, @Field("json") String json);


    /**
     * 获取店铺详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ConsultModel>> getConsultDetail(@Field("code") String code, @Field("json") String json);


    /**
     * 获取店铺列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ConsultListModel>> getConsultList(@Field("code") String code, @Field("json") String json);


    /**
     * 注册
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserLoginModel>> signUp(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户账户
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CoinModel>> getAccount(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户账户
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DealHistoryModel>> getDealHistory(@Field("code") String code, @Field("json") String json);

    /**
     * 获取统计信息
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DealUserDataModel>> getDealUserData(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户账单
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<BillModel>> getBillListData(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户信息详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserInfoModel>> getUserInfoDetails(@Field("code") String code, @Field("json") String json);

    /**
     * 获取轮播图
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<BannerModel>> getBanner(@Field("code") String code, @Field("json") String json);

    /**
     * 获取系统参数
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemParameterModel>> getSystemParameter(@Field("code") String code, @Field("json") String json);

    /**
     * 获取系统参数
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemParameterListModel>> getSystemParameterList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取系统参数
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<SystemParameterModel>> getSystemInformation(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户设置
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<UserSettingModel>> getUserSetting(@Field("code") String code, @Field("json") String json);

    /**
     * 获取邀请数据
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<InviteModel>> getInvite(@Field("code") String code, @Field("json") String json);

    /**
     * 获取电子货币行情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<MarketCoinModel>> getMarketCoinList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取电子货币行情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<MarketCoinModel>> getMarketCoin(@Field("code") String code, @Field("json") String json);


    /**
     * 数字货币，平台干预后的价格
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<MarketCoinModel>> getTruePrice(@Field("code") String code, @Field("json") String json);

    /**
     * 获取法币汇率
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<RateModel>> getRate(@Field("code") String code, @Field("json") String json);

    /**
     * 获取法币汇率
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<RateModel>> getRateList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取货币行情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<MarketModel>> getMarket(@Field("code") String code, @Field("json") String json);

    /**
     * 获取系统消息
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemMessageModel>> getSystemMessage(@Field("code") String code, @Field("json") String json);

    /**
     * 获取系统资讯
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ConsultingModel>> getConsulting(@Field("code") String code, @Field("json") String json);

    /**
     * 获取交易
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DealModel>> getDeal(@Field("code") String code, @Field("json") String json);

    /**
     * 获取交易
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<DealDetailModel>> getDealList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取交易
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<WithdrawOrderModel>> getWithdrawOrder(@Field("code") String code, @Field("json") String json);

    /**
     * 获取交易
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DealDetailModel>> getDealDetail(@Field("code") String code, @Field("json") String json);

    /**
     * 获取信任列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<TrustModel>> getTrust(@Field("code") String code, @Field("json") String json);

    /**
     * 获取推荐列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserRefereeModel>> getUserReferee(@Field("code") String code, @Field("json") String json);

    /**
     * 获取汇率
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ExchangeModel>> getExchange(@Field("code") String code, @Field("json") String json);

    /**
     * 获取交易结果
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DealResultModel>> getDealResult(@Field("code") String code, @Field("json") String json);

    /**
     * 获取订单
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<OrderModel>> getOrder(@Field("code") String code, @Field("json") String json);

    /**
     * 获取订单
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<OrderDetailModel>> getOrderDetail(@Field("code") String code, @Field("json") String json);

    /**
     * 获取地址
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<AddressModel>> getAddress(@Field("code") String code, @Field("json") String json);

    /**
     * 获取版本
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<VersionModel>> getVersion(@Field("code") String code, @Field("json") String json);

    /**
     * 获取支持的币种
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<BaseCoinModel>> getCoinList(@Field("code") String code, @Field("json") String json);

}
