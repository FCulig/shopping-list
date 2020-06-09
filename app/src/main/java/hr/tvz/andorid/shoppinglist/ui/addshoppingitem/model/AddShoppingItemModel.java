package hr.tvz.andorid.shoppinglist.ui.addshoppingitem.model;

import hr.tvz.andorid.shoppinglist.entities.ShoppingListItem;

public interface AddShoppingItemModel {
    void makeAddItemCall(String token, ShoppingListItem newItem);
}
