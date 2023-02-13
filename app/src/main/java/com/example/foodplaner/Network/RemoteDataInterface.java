package com.example.foodplaner.Network;

import com.example.foodplaner.Network.CallBack.CategoriesCallBack;
import com.example.foodplaner.Network.CallBack.RandomMealCallBack;

public interface RemoteDataInterface {
    public void getData(NetworkDelegate networkDelegate);

    public void getRandomMeal(RandomMealCallBack randomMealCallBack);

    public  void  getCategories(CategoriesCallBack callBack);

}
