package hr.tvz.andorid.shoppinglist.ui.register.view;

public interface RegisterFragment {
    void openLogin();

    void registerUser();

    void makeToast(String message);

    void hideToolBar();

    void hideRegisterButton();

    void showRegisterButton();

    void hideProgressBar();

    void showProgressBar();
}
