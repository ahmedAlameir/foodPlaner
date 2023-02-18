package com.example.foodplaner.Network;

import com.example.foodplaner.Network.CallBack.CategoriesCallBack;
import com.example.foodplaner.Network.CallBack.ChipListCallback;
import com.example.foodplaner.Network.CallBack.FilteredCallBack;
import com.example.foodplaner.Network.CallBack.RandomMealCallBack;

public interface RemoteDataInterface {

    public void getRandomMeal(RandomMealCallBack randomMealCallBack);

    public  void  getCategories(CategoriesCallBack callBack);
    void getData(NetworkDelegate networkDelegate, String l);

    void getCategoriesList(ChipListCallback callback);

    void getAreaList(ChipListCallback callback);

    void getIngredientList(ChipListCallback callback);

    void getCategoryFiltered(String s, FilteredCallBack callBack);

    void getAreaFiltered(String s, FilteredCallBack callBack);

    void getIngredientFiltered(String s, FilteredCallBack callBack);

    void getGetMeal(String idMeal, FilteredCallBack callBack);
}
