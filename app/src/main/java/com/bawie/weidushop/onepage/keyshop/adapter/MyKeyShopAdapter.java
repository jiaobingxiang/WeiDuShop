package com.bawie.weidushop.onepage.keyshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.weidushop.R;
import com.bawie.weidushop.onepage.bean.KeyShopBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyKeyShopAdapter extends RecyclerView.Adapter<MyKeyShopAdapter.KeyViewHolder> {
    Context context;
    List<KeyShopBean.ResultBean> result;

    public MyKeyShopAdapter(Context context, List<KeyShopBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public KeyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.key_shop_item,viewGroup,false);
        KeyViewHolder keyViewHolder = new KeyViewHolder(view);
        return keyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeyViewHolder keyViewHolder, final int i) {
        keyViewHolder.name.setText(result.get(i).getCommodityName());
        keyViewHolder.price.setText(result.get(i).getPrice());
        keyViewHolder.saleNum.setText(result.get(i).getSaleNum());
        //设置图片
        RoundingParams params = RoundingParams.fromCornersRadius(20f);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(params).build();
        keyViewHolder.image.setHierarchy(hierarchy);
        keyViewHolder.image.setImageURI(result.get(i).getMasterPic());
        keyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemClickListener.downClick(v,result.get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class KeyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView image;
        TextView name,price,saleNum;
        public KeyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.key_img);
            name = itemView.findViewById(R.id.key_name);
            price = itemView.findViewById(R.id.key_price);
            saleNum = itemView.findViewById(R.id.key_salenum);
        }
    }
    //创建接口     实现条目点击事件
    public interface SetOnItemClickListener{
        void downClick(View view,String id);
    }
    SetOnItemClickListener setOnItemClickListener;
    public void setItemClick(SetOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener = setOnItemClickListener;
    }
}
