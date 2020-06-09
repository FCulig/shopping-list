package hr.tvz.andorid.shoppinglist.ui.register.presenter;

public interface RegisterPresenter {
    void registerSuccessful();

    void registerFailed();

    boolean isRegisterFormValid(String email, String password, String repeatPassword);

    void registerUser(String email, String password, String repeatPassword);
}
