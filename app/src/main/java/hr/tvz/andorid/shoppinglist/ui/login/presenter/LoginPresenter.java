package hr.tvz.andorid.shoppinglist.ui.login.presenter;

import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;

public interface LoginPresenter {
    void loginUser(String email, String password);

    boolean isLoginValid(String email, String password);

    void loginFailed();

    void loginSuccessful(LoginResponseModel response);
}
