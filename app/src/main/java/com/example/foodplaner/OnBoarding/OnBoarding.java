package com.example.foodplaner.OnBoarding;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import com.example.foodplaner.MainActivity;
import com.example.foodplaner.R;


public class OnBoarding extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private AppCompatButton skipButton;
    private TextView[] dots;
    private OnBoardingAdapter viewPagerAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        skipButton = findViewById(R.id.skip_btn);
        viewPager = findViewById(R.id.slideViewPager);
        dotLayout = findViewById(R.id.indicator_layout);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(OnBoarding.this, MainActivity.class));
            }
        });



        viewPagerAdapter = new OnBoardingAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        setUpIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }
    public void setUpIndicator(int position) {

        dots = new TextView[3];
        dotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.white, this.getTheme()));
            }
            dotLayout.addView(dots[i]);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.black, this.getTheme()));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            setUpIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

}
