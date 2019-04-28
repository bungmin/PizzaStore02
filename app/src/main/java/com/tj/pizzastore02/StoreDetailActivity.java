package com.tj.pizzastore02;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.tj.pizzastore02.databinding.ActivityStoreDetailBinding;
import com.tj.pizzastore02.datas.PizzaStore;

public class StoreDetailActivity extends BaseActivity {

    PizzaStore storeData;

    ActivityStoreDetailBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setValues();
        setupEvents();

    }

    @Override
    public void setupEvents() {
        act.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(String.format("tel:%s",storeData.phoneNum));
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        });

        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                결과를 저장해서, 필터 화면을 호출한 곳으로 돌아가도록.
//                1. 결과 저장

                String selectedItem = act.pizzaMenu.getSelectedItem().toString();

//                2. 메인화면으로 돌아가기. 새로 띄우는게 X, 이전 화면으로 돌아가기 => 지금 화면을 닫는다.
//                돌아가는 Intent는 new Intent() 안에 아무것도 넣지 않음.
                Intent resultIntent = new Intent();
//                결과 인텐트에 입력한 최소 평점을 첨부


                resultIntent.putExtra("선택가게", storeData);
                resultIntent.putExtra("선택피자", selectedItem);
//                finish 하기 전에 결과를 설정.
                setResult(RESULT_OK, resultIntent);
//                모든 설정이 끝났으니 이 화면을 닫는다.
                finish();

                Log.d("aaa", "aaaaaaaaa");



            }
        });



    }

    @Override
    public void setValues() {
        storeData = (PizzaStore) getIntent().getSerializableExtra("가게정보");

        act.phoneNumTxt.setText(storeData.phoneNum);
        act.nameTxt.setText(storeData.name);
        Glide.with(mContext).load(storeData.logoUrl).into(act.logoImg);

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_store_detail);
    }
}
