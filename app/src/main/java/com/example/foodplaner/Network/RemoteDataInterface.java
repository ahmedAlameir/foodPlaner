package com.example.foodplaner.Network;

import com.example.foodplaner.Network.CallBack.RandomMealCallBack;

public interface RemoteDataInterface {
    public void getData(NetworkDelegate networkDelegate);

    public void getRandomMeal(RandomMealCallBack randomMealCallBack);


}
