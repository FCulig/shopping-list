package hr.tvz.andorid.shoppinglist.ui.addshoppingitem.presenter;

public interface AddShoppingItemPresenter {
    void addItem(String productName, String quantity, String category);

    boolean isItemInputValid(String productName, String quantity);

    void itemAddSucceeded();

    void itemAddFailed();
}
