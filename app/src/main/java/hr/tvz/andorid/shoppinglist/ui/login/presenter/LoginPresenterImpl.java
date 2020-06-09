package hr.tvz.andorid.shoppinglist.ui.login.presenter;

import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;
import hr.tvz.andorid.shoppinglist.entities.login.LoginUserModel;
import hr.tvz.andorid.shoppinglist.ui.login.model.LoginModelImpl;
import hr.tvz.andorid.shoppinglist.ui.login.view.LoginFragmentImpl;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginFragmentImpl loginFragment;
    private LoginModelImpl loginModel;

    public LoginPresenterImpl(LoginFragmentImpl loginFragment) {
        this.loginFragment = loginFragment;
        this.loginFragment.hideProgressBar();
        this.loginFragment.hideToolBar();
        loginModel = new LoginModelImpl(this);
    }

    @Override
    public void loginUser(String email, String password) {
        if (isLoginValid(email, password)) {
            loginFragment.showProgressBar();
            loginFragment.hideLoginButton();
            loginModel.makeLoginCall(new LoginUserModel(email, password));
        }
    }

    @Override
    public boolean isLoginValid(String email, String password) {
        if (email.equals("")) {
            loginFragment.makeToast("Niste unjeli e-mail adresu");
            return false;
        }
        if (password.equals("")) {
            loginFragment.makeToast("Niste unjeli lozinku");
            return false;
        }

        return true;
    }

    @Override
    public void loginFailed() {
        loginFragment.makeToast("Došlo je do pogreške prilikom prijave");
        loginFragment.hideProgressBar();
        loginFragment.showLoginButton();
    }

    @Override
    public void loginSuccessful(LoginResponseModel response) {
        loginFragment.openShoppingList(response);
    }
}
