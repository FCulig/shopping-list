package hr.tvz.andorid.shoppinglist.ui.register.presenter;

import hr.tvz.andorid.shoppinglist.entities.register.RegisterUserModel;
import hr.tvz.andorid.shoppinglist.ui.register.model.RegisterModelImpl;
import hr.tvz.andorid.shoppinglist.ui.register.view.RegisterFragmentImpl;

public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterFragmentImpl registerFragment;
    private RegisterModelImpl registerModel;

    public RegisterPresenterImpl(RegisterFragmentImpl registerFragment) {
        this.registerFragment = registerFragment;
        registerFragment.hideToolBar();
        registerFragment.hideProgressBar();
        registerModel = new RegisterModelImpl(this);
    }

    @Override
    public void registerUser(String email, String password, String repeatPassword) {
        if (isRegisterFormValid(email, password, repeatPassword)) {
            registerFragment.showProgressBar();
            registerFragment.hideRegisterButton();
            registerModel.makeRegisterUserCall(new RegisterUserModel(email, password, repeatPassword));
        }
    }

    @Override
    public boolean isRegisterFormValid(String email, String password, String repeatPassword) {
        if (email.equals("")) {
            registerFragment.makeToast("Niste unjeli e-mail adresu");
            return false;
        }
        if (password.equals("")) {
            registerFragment.makeToast("Niste unjeli lozinku");
            return false;
        }
        if (repeatPassword.equals("")) {
            registerFragment.makeToast("Niste ponovili lozinku");
            return false;
        }
        if (!password.equals(repeatPassword)) {
            registerFragment.makeToast("Lozinke se ne podudaraju!");
            return false;
        }

        return true;
    }

    @Override
    public void registerFailed() {
        registerFragment.makeToast("Došlo je do pogreške prilikom registracije");
        registerFragment.hideProgressBar();
        registerFragment.showRegisterButton();
    }

    @Override
    public void registerSuccessful() {
        registerFragment.makeToast("Registracija je uspješna!");
        registerFragment.openLogin();
    }
}
