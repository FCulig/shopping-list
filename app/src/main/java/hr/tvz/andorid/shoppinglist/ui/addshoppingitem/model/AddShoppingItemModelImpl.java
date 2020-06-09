package hr.tvz.andorid.shoppinglist.ui.addshoppingitem.model;

import android.util.Log;

import hr.tvz.andorid.shoppinglist.api.ApiClient;
import hr.tvz.andorid.shoppinglist.entities.ShoppingListItem;
import hr.tvz.andorid.shoppinglist.ui.addshoppingitem.presenter.AddShoppingItemPresenterImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddShoppingItemModelImpl implements AddShoppingItemModel {
    private AddShoppingItemPresenterImpl addShoppingItemPresenter;
    private String TAG = "ADD_SHOPPING_ITEM_MODEL";

    public AddShoppingItemModelImpl(AddShoppingItemPresenterImpl addShoppingItemPresenter) {
        this.addShoppingItemPresenter = addShoppingItemPresenter;
    }

    @Override
    public void makeAddItemCall(String token, ShoppingListItem newItem) {
        Call<ShoppingListItem> addItemCall = ApiClient.getInstance().getEndpoints().addItem("Bearer " + token, newItem);

        addItemCall.enqueue(new Callback<ShoppingListItem>() {
            @Override
            public void onResponse(Call<ShoppingListItem> call, Response<ShoppingListItem> response) {
                Log.d(TAG, response.message().toString());
                if (response.isSuccessful()) {
                    Log.d("TAG", response.body().toString());
                    addShoppingItemPresenter.itemAddSucceeded();
                }
            }

            @Override
            public void onFailure(Call<ShoppingListItem> call, Throwable t) {
                Log.d("TAG", call.toString());
                Log.d("TAG", t.toString());
                addShoppingItemPresenter.itemAddFailed();
            }
        });
    }
}
