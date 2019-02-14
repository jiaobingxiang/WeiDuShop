package com.bawie.weidushop.onepage.xiang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bawie.weidushop.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class XiangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        EventBus.getDefault().register(this);
    }

    /**
     * 粘性事件
     * @param id
     */
    @Subscribe(sticky = true)
    public void getId(String id){
        Toast.makeText(XiangActivity.this,id,Toast.LENGTH_SHORT).show();
    }
}
