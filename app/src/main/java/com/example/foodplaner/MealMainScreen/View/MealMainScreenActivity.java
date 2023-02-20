package com.example.foodplaner.MealMainScreen.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplaner.CheckInternet;
import com.example.foodplaner.MealMainScreen.View.DayChoiceDialogActivity;
import com.example.foodplaner.MealMainScreen.Presenter.MealMainScreenPresenter;
import com.example.foodplaner.MealMainScreen.Presenter.MealMainScreenPresenterInterface;
import com.example.foodplaner.Model.Ingredient;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.R;
import com.example.foodplaner.databinding.ActivityMealMainScreenBinding;
import com.example.foodplaner.rebo.Repository;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealMainScreenActivity extends AppCompatActivity implements MealMainScreenViewInterface,DayChoiceDialogActivity.DayChoiceListenerActivity{
    private ActivityMealMainScreenBinding binding;
    MealMainScreenAdapter adapter;
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    Meal meal;
    ImageView mealImg;
    TextView mealName;
    TextView mealArea;
    TextView Instructions;
    YouTubePlayerView mealVideo;
    String day;
    ImageView calender;

    Ingredient ingredient;
    String ingrdient_img;
    ImageView plan;
    ImageView fav;
    PlanMeal planMeal=new PlanMeal();
    MealMainScreenPresenterInterface mealMainScreenPresenterInterface;
    private AlertDialog.Builder connectionBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMealMainScreenBinding.inflate(getLayoutInflater());
        //setContentView(binding.nestedScrollView.findViewById(R.id.nestedScrollView));
        setContentView(R.layout.activity_meal_main_screen);
        RecyclerView recyclerView = findViewById(R.id.rv_ingredient);
        mealImg = findViewById(R.id.meal_img);
        mealName = findViewById(R.id.meal_name);
        mealArea = findViewById(R.id.meal_area);
        mealVideo=findViewById(R.id.meal_video);
        Instructions = findViewById(R.id.instructionbody);
        calender=findViewById(R.id.calender);
        plan=findViewById(R.id.mealscreenplan);
        fav=findViewById(R.id.mealscreenfav);
        connectionBuilder =new AlertDialog.Builder(this);
        connectionBuilder.setMessage("No Internet Connection");
        isThereConnection();

        mealMainScreenPresenterInterface=new MealMainScreenPresenter(this, Repository.getInstance(MealClient.getINSTANCE(),this));



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            meal = (Meal) extras.getSerializable("MEAL");
        }
        final String[] VideoUrl = {meal.getStrYoutube()};
        mealVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if(VideoUrl[0] !=null&&!VideoUrl[0].isEmpty()&&!VideoUrl[0].equalsIgnoreCase("")){
                    VideoUrl[0] = VideoUrl[0].substring(VideoUrl[0].indexOf("=") + 1);
                    StringTokenizer st = new StringTokenizer(VideoUrl[0], "&");
                    VideoUrl[0] = st.nextToken();
                    youTubePlayer.loadVideo(VideoUrl[0],0);
                    youTubePlayer.pause();
                }
            }
        });




        mealName.setText(meal.getStrMeal());
        mealArea.setText(meal.getStrArea());
        Instructions.setText(meal.getStrInstructions());
        Glide.with(this).load(meal.getStrMealThumb()).into(mealImg);

        fillIngredients(meal);
        Log.i("TAG", "size= " + ingredients.size());

        recyclerView.setLayoutManager(new LinearLayoutManager(MealMainScreenActivity.this));
        adapter = new MealMainScreenAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setMeals(ingredients);
        adapter.notifyDataSetChanged();
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dayChoice= new DayChoiceDialogActivity();
                dayChoice.setCancelable(false);
                dayChoice.show(getSupportFragmentManager(),"Choice");
                planMeal.setIdMeal(meal.getIdMeal());
                planMeal.setMeal(meal);

            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE,meal.getStrMeal());
                intent.putExtra(Intent.EXTRA_EMAIL,"hadiayzmm@gmail.com");
                try{
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MealMainScreenActivity.this,e.getMessage()+"there is no app support this action",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void fillIngredients(Meal meal) {

        if (meal.getStrIngredient1() != null&& !meal.getStrIngredient1().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient1());
            ingredient = new Ingredient(meal.getStrIngredient1(), meal.getStrMeasure1(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient2() != null&& !meal.getStrIngredient2().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient2());
            ingredient = new Ingredient(meal.getStrIngredient2(), meal.getStrMeasure2(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient3() != null&& !meal.getStrIngredient3().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient3());
            ingredient = new Ingredient(meal.getStrIngredient3(), meal.getStrMeasure3(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient4() != null&& !meal.getStrIngredient4().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient4());
            ingredient = new Ingredient(meal.getStrIngredient4(), meal.getStrMeasure4(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient5() != null&& !meal.getStrIngredient5().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient5());
            ingredient = new Ingredient(meal.getStrIngredient5(), meal.getStrMeasure5(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient6() != null&& !meal.getStrIngredient6().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient6());
            ingredient = new Ingredient(meal.getStrIngredient6(), meal.getStrMeasure6(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient7() != null&& !meal.getStrIngredient7().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient7());
            ingredient = new Ingredient(meal.getStrIngredient7(), meal.getStrMeasure7(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient8() != null&& !meal.getStrIngredient8().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient8());
            ingredient = new Ingredient(meal.getStrIngredient8(), meal.getStrMeasure8(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient9() != null&& !meal.getStrIngredient9().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient9());
            ingredient = new Ingredient(meal.getStrIngredient9(), meal.getStrMeasure9(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient10() != null&& !meal.getStrIngredient10().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient10());
            ingredient = new Ingredient(meal.getStrIngredient10(), meal.getStrMeasure10(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient11() != null&& !meal.getStrIngredient11().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient11());
            ingredient = new Ingredient(meal.getStrIngredient11(), meal.getStrMeasure11(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient12() != null&& !meal.getStrIngredient12().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient12());
            ingredient = new Ingredient(meal.getStrIngredient12(), meal.getStrMeasure12(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient13() != null&& !meal.getStrIngredient13().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient13());
            ingredient = new Ingredient(meal.getStrIngredient13(), meal.getStrMeasure13(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient14() != null&& !meal.getStrIngredient14().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient14());
            ingredient = new Ingredient(meal.getStrIngredient14(), meal.getStrMeasure14(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient15() != null&& !meal.getStrIngredient15().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient15());
            ingredient = new Ingredient(meal.getStrIngredient15(), meal.getStrMeasure15(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
                ingrdient_img = new String();
                ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient16());
                ingredient = new Ingredient(meal.getStrIngredient16(), meal.getStrMeasure16(), ingrdient_img);
                ingredients.add(ingredient);

        }
        if (meal.getStrIngredient17() != null&& !meal.getStrIngredient17().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient17());
            ingredient = new Ingredient(meal.getStrIngredient17(), meal.getStrMeasure17(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient18() != null&& !meal.getStrIngredient18().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient18());
            ingredient = new Ingredient(meal.getStrIngredient18(), meal.getStrMeasure18(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient19() != null&& !meal.getStrIngredient19().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient19());
            ingredient = new Ingredient(meal.getStrIngredient19(), meal.getStrMeasure19(), ingrdient_img);
            ingredients.add(ingredient);
        }
        if (meal.getStrIngredient20() != null&& !meal.getStrIngredient20().isEmpty()) {
            ingrdient_img = new String();
            ingrdient_img = String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", meal.getStrIngredient20());
            ingredient = new Ingredient(meal.getStrIngredient20(), meal.getStrMeasure20(), ingrdient_img);
            ingredients.add(ingredient);
        }
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        Log.i("TAG",list[position]+" is selected");
        day=list[position];
        planMeal.setDay(day);
        Completable comp=addToPlan(planMeal);

        comp.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG","added to plan");
                        //Toast.makeText(AllMealsFragment.this,"Added To Favourite", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

    }

    @Override
    public void onNegativeButtonClicked() {

    }


    @Override
    public Completable addToPlan(PlanMeal meal) {
        return mealMainScreenPresenterInterface.addToPlan(meal);

    }

    @Override
    public void addToFav(Meal meal) {

    }
    public void isThereConnection(){
        AlertDialog dialog;
        if(!CheckInternet.getConnectivity(this)){
            dialog=connectionBuilder.create();
            dialog.show();
            // Toast.makeText(this, "No Internet", Toast.LENGTH_LONG).show();
        }
    }
}
