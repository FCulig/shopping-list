package hr.tvz.andorid.shoppinglist.ui.shoppinglist.model;

import android.util.Log;

import java.util.ArrayList;

import hr.tvz.andorid.shoppinglist.api.ApiClient;
import hr.tvz.andorid.shoppinglist.entities.IShoppingCategory;
import hr.tvz.andorid.shoppinglist.entities.ShoppingCategory;
import hr.tvz.andorid.shoppinglist.entities.ShoppingList;
import hr.tvz.andorid.shoppinglist.ui.shoppinglist.presenter.ShoppingListPresenterImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListModelImpl implements ShoppingListModel {
    private ShoppingListPresenterImpl shoppingListPresenter;
    private String token;
    private final String TAG = "SHOPPING_LIST_MODEL";

    public ShoppingListModelImpl(ShoppingListPresenterImpl shoppingListPresenter, String token) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.token = token;
    }

    public void makeShoppingListCall() {
        Call<ShoppingList> getItems = ApiClient.getInstance().getEndpoints().getShoppingList("Bearer " + token);
        getItems.enqueue(new Callback<ShoppingList>() {
            @Override
            public void onResponse(Call<ShoppingList> call, Response<ShoppingList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    shoppingListPresenter.callSucceeded(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShoppingList> call, Throwable t) {
                Log.d(TAG, call.toString());
                Log.d(TAG, t.toString());
                shoppingListPresenter.callFailed();
            }
        });
    }

    public void makeShoppingCategoriesCall() {
        Call<ArrayList<ShoppingCategory>> getCategoriesCall = ApiClient.getInstance().getEndpoints().getShoppingCategoires("Bearer " + token);
        getCategoriesCall.enqueue(new Callback<ArrayList<ShoppingCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<ShoppingCategory>> call, Response<ArrayList<ShoppingCategory>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    IShoppingCategory.categories = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShoppingCategory>> call, Throwable t) {
                Log.d(TAG, call.toString());
                Log.d(TAG, t.toString());
                shoppingListPresenter.callFailed();
            }
        });
    }

    public void makeDeleteShoppingItemCall(int itemID){
        Call<ResponseBody> getCategoriesCall = ApiClient.getInstance().getEndpoints().deleteItem("Bearer " + token, itemID);
        getCategoriesCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, response.message());
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, call.toString());
                Log.d(TAG, t.toString());
                shoppingListPresenter.callFailed();
            }
        });
    }
}
