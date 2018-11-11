package com.ration.rationstudy.mark.chapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.mark.chapters.data.Dish;
import com.ration.rationstudy.mark.common.BaseActivity;
import com.ration.rationstudy.mark.common.MarkAdapter;
import com.ration.rationstudy.mark.common.MarkViewHolder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FoodMenuChapter extends BaseActivity {

    private RecyclerView rv_data;
    private Button btn_threeHighCaloric;
    private Button btn_clean;
    private Button btn_isVegetarian;
    private Button btn_skip;
    private FoodMenuAdapter adapter;
    private ArrayList<Dish> menuArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu_chapter);
        init();
    }

    @Override
    public void findViews() {
        rv_data = (RecyclerView) findViewById(R.id.rv_data);
        btn_threeHighCaloric = (Button) findViewById(R.id.btn_threeHighCaloric);
        btn_clean = (Button) findViewById(R.id.btn_clean);
        btn_isVegetarian = (Button) findViewById(R.id.btn_isVegetarian);
        btn_skip = (Button) findViewById(R.id.btn_skip);
    }

    @Override
    public void init() {
        findViews();
        setUpMenu();
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodMenuAdapter(this, R.layout.mark_list_menu);
        adapter.setItemList(menuArray);
        rv_data.setAdapter(adapter);

        btn_threeHighCaloric.setOnClickListener(onClickListener);
        btn_clean.setOnClickListener(onClickListener);
        btn_isVegetarian.setOnClickListener(onClickListener);
        btn_skip.setOnClickListener(onClickListener);
    }

    private void setUpMenu() {
        menuArray = new ArrayList<>();
        menuArray.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        menuArray.add(new Dish("beef", false, 700, Dish.Type.MEAT));
        menuArray.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        menuArray.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        menuArray.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        menuArray.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        menuArray.add(new Dish("pizza", true, 550, Dish.Type.OTHER));
        menuArray.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        menuArray.add(new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    View.OnClickListener onClickListener = view -> {
        if (view.getId() == R.id.btn_clean) {
            adapter.setItemList(menuArray);
        } else if (view.getId() == R.id.btn_threeHighCaloric) {
            adapter.setItemList((ArrayList<Dish>) menuArray
                    .stream()
                    .filter(d -> d.getCalories() > 300)
                    .limit(3)
                    .collect(Collectors.toList()));
        } else if (view.getId() == R.id.btn_isVegetarian) {
            adapter.setItemList((ArrayList<Dish>) menuArray
                    .stream()
                    .filter(Dish::isVegetarian)
                    .collect(Collectors.toList()));
        } else if (view.getId() == R.id.btn_skip) {
            adapter.setItemList((ArrayList<Dish>) menuArray
                    .stream()
                    .filter(d -> d.getCalories() > 300)
                    .skip(2)
                    .collect(Collectors.toList()));

        }
    };


    class FoodMenuAdapter extends MarkAdapter<Dish, FoodMenuViewHolder> {

        public FoodMenuAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public FoodMenuViewHolder viewHolder(View view) {
            return new FoodMenuViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodMenuViewHolder foodMenuViewHolder, int i) {
            foodMenuViewHolder.bindTo(getContext(), getArrayList().get(i));
        }
    }

    class FoodMenuViewHolder extends MarkViewHolder<Dish> {

        private TextView tv_name;
        private TextView tv_vegetarian;
        private TextView tv_calories;
        private TextView tv_type;

        public FoodMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_vegetarian = (TextView) itemView.findViewById(R.id.tv_vegetarian);
            tv_calories = (TextView) itemView.findViewById(R.id.tv_calories);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
        }

        @Override
        public void bindTo(Context context, Dish item) {
            String format = String.format("음식이름 : %s", item.getName());
            tv_name.setText(format);
            String vegetarian;
            if (item.isVegetarian()) {
                vegetarian = "채식";
            } else {
                vegetarian = "육식";
            }
            tv_vegetarian.setText(vegetarian);
            format = String.format("칼로리 : %s", item.getCalories());
            tv_calories.setText(format);
            format = String.format("타입 : %s", item.getType().toString());
            tv_type.setText(format);
        }
    }
}
