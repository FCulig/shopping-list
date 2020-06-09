package hr.tvz.andorid.shoppinglist.ui.login.view;

import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;

public interface LoginFragment {
    void openRegister();

    void loginUser();

    void openShoppingList(LoginResponseModel response);

    void makeToast(String message);

    void hideToolBar();

    void hideLoginButton();

    void showLoginButton();

    void hideProgressBar();

    void showProgressBar();
}
