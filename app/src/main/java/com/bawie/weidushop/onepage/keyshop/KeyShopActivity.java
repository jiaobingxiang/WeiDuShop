package com.bawie.weidushop.onepage.keyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bawie.weidushop.R;
import com.bawie.weidushop.api.ApiService;
import com.bawie.weidushop.api.ShopApiService;
import com.bawie.weidushop.onepage.bean.KeyShopBean;
import com.bawie.weidushop.onepage.keyshop.adapter.MyKeyShopAdapter;
import com.bawie.weidushop.onepage.xiang.XiangActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeyShopActivity extends AppCompatActivity {

    @BindView(R.id.key_rec)
    RecyclerView keyRec;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_shop);
        bind = ButterKnife.bind(this);
        initView();
        getData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        keyRec.setLayoutManager(new GridLayoutManager(this,2));
    }

    /**
     * 请求数据
     */
    public void getData(){
        //创建retrofit管理器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)//主机名
                .addConverterFactory(GsonConverterFactory.create())//数据解析器
                .build();
        //实现接口
        ShopApiService shopApiService = retrofit.create(ShopApiService.class);
        Call<KeyShopBean> keyShopCall = shopApiService.keyShopCall("板鞋", 1, 5);
        //请求
        keyShopCall.enqueue(new Callback<KeyShopBean>() {
            @Override
            public void onResponse(Call<KeyShopBean> call, Response<KeyShopBean> response) {
                KeyShopBean keyShopBean = response.body();
                final List<KeyShopBean.ResultBean> result = keyShopBean.getResult();
                MyKeyShopAdapter myKeyShopAdapter = new MyKeyShopAdapter(KeyShopActivity.this, result);
                keyRec.setAdapter(myKeyShopAdapter);
                myKeyShopAdapter.setItemClick(new MyKeyShopAdapter.SetOnItemClickListener() {
                    @Override
                    public void downClick(View view,String id) {
                        EventBus.getDefault().post(id);
                        startActivity(new Intent(KeyShopActivity.this,XiangActivity.class));
                    }
                });
            }

            @Override
            public void onFailure(Call<KeyShopBean> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
