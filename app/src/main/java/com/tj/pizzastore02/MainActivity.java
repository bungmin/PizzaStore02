package com.tj.pizzastore02;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tj.pizzastore02.adapters.StoreAdapter;
import com.tj.pizzastore02.databinding.ActivityMainBinding;
import com.tj.pizzastore02.datas.PizzaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    static int REQ_FOR_FILTER = 150;


    ActivityMainBinding act;

    List<PizzaStore> pizzaStores = new ArrayList<>();

    StoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        act.pizzaStoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PizzaStore store = pizzaStores.get(position);

              /*  Intent intent = new Intent(mContext, StoreDetailActivity.class);
                startActivity(intent);
*/
                Intent resultIntent = new Intent(mContext, StoreDetailActivity.class);
                resultIntent.putExtra("가게정보", store);
                startActivityForResult(resultIntent, REQ_FOR_FILTER);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_FOR_FILTER) {
//            필터를 설정하러 갔다 돌아온게 맞다!

            if (resultCode == RESULT_OK) {
                String selectedPizza = data.getStringExtra("선택피자");
                //String selectedPizza = data.getStringExtra("선택가게");
                PizzaStore selectedPizzaStore = (PizzaStore) data.getSerializableExtra("선택가게");






                Toast.makeText(this, String.format("%s 에서 %s 를 선택하셨습니다.", selectedPizzaStore.name, selectedPizza), Toast.LENGTH_SHORT).show();




            }


        }


    }

    @Override
    public void setValues() {

        setTitle("피자 주문 앱");
        fillPizzaStores();


        mAdapter = new StoreAdapter(mContext, pizzaStores);
        act.pizzaStoreListView.setAdapter(mAdapter);
    }

    void fillPizzaStores() {

        pizzaStores.add(new PizzaStore("파파존스", "09:00~21:00", "010-1234-5678", "http://postfiles2.naver.net/20160530_65/ppanppane_1464617766007O9b5u_PNG/%C6%C4%C6%C4%C1%B8%BD%BA_%C7%C7%C0%DA_%B7%CE%B0%ED_%284%29.png?type=w966"));
        pizzaStores.add(new PizzaStore("피자헛", "10:00~21:00", "010-9876-5432", "https://upload.wikimedia.org/wikipedia/en/thumb/d/d2/Pizza_Hut_logo.svg/220px-Pizza_Hut_logo.svg.png"));
        pizzaStores.add(new PizzaStore("도미노피자", "12:00~21:00", "010-2145-7831", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Domino%27s_pizza_logo.svg/120px-Domino%27s_pizza_logo.svg.png"));
        pizzaStores.add(new PizzaStore("미스터피자", "11:00~21:00", "010-0587-1234", "http://postfiles12.naver.net/20160530_171/ppanppane_14646177044221JRNd_PNG/%B9%CC%BD%BA%C5%CD%C7%C7%C0%DA_%B7%CE%B0%ED_%281%29.png?type=w966"));
        pizzaStores.add(new PizzaStore("피자에땅", "09:00~21:00", "010-3481-6247", "https://mblogthumb-phinf.pstatic.net/20160530_214/ppanppane_1464617804922knKn4_PNG/%C7%C7%C0%DA%BF%A1%B6%A5_%B7%CE%B0%ED_%282%29.png?type=w800"));
        pizzaStores.add(new PizzaStore("피자스쿨", "08:00~21:00", "010-2794-1346", "https://modo-phinf.pstatic.net/20150501_269/1430484184544WKwLF_JPEG/mosa7NPaR2.jpeg?type=f320_320"));
        pizzaStores.add(new PizzaStore("피자나라 치킨공주", "13:00~21:00", "010-2015-4751", "http://www.newsworker.co.kr/news/photo/201810/22592_19806_5940.jpg"));
    }


    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
