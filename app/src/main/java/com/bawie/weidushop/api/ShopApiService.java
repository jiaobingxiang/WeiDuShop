package com.bawie.weidushop.api;

import com.bawie.weidushop.onepage.bean.KeyShopBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApiService {
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<KeyShopBean> keyShopCall(@Query("keyword") String title, @Query("page") int page, @Query("count") int count);
}
