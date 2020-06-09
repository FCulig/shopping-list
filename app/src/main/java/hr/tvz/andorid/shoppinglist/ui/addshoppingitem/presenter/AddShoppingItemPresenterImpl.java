package hr.tvz.andorid.shoppinglist.ui.addshoppingitem.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import hr.tvz.andorid.shoppinglist.entities.IShoppingCategory;
import hr.tvz.andorid.shoppinglist.entities.ShoppingListItem;
import hr.tvz.andorid.shoppinglist.ui.addshoppingitem.model.AddShoppingItemModelImpl;
import hr.tvz.andorid.shoppinglist.ui.addshoppingitem.view.AddShoppingItemFragmentImpl;

public class AddShoppingItemPresenterImpl implements AddShoppingItemPresenter {
    private AddShoppingItemFragmentImpl addShoppingItemFragment;
    private AddShoppingItemModelImpl addShoppingItemModel;
    private Context context;

    public AddShoppingItemPresenterImpl(AddShoppingItemFragmentImpl addShoppingItemFragment, Context c) {
        this.addShoppingItemFragment = addShoppingItemFragment;
        this.addShoppingItemModel = new AddShoppingItemModelImpl(this);
        this.context = c;
    }

    @Override
    public void addItem(String productName, String quantity, String category) {
        if (isItemInputValid(productName, quantity)) {
            ShoppingListItem newItem = new ShoppingListItem(productName, IShoppingCategory.getIdFromName(category), Integer.parseInt(quantity));
            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String token = mPreferences.getString("token", "");

            addShoppingItemModel.makeAddItemCall(token, newItem);
        }
    }

    @Override
    public boolean isItemInputValid(String productName, String quantity) {
        if (productName == null) {
            addShoppingItemFragment.makeToast("Naziv proizvoda ne smije biti prazan");
            return false;
        }

        if (quantity == null) {
            addShoppingItemFragment.makeToast("Količina ne smije biti prazna");
            return false;
        }

        return true;
    }

    @Override
    public void itemAddSucceeded(){
        addShoppingItemFragment.makeToast("Proizvod je uspješno dodan");
        addShoppingItemFragment.resetInputs();
    }

    @Override
    public void itemAddFailed(){
        addShoppingItemFragment.makeToast("Došlo je do pogreške prilikom prijave");
    }
}
