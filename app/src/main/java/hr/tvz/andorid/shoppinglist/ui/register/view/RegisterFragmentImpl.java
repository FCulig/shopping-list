package hr.tvz.andorid.shoppinglist.ui.register.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tvz.andorid.shoppinglist.R;
import hr.tvz.andorid.shoppinglist.ui.login.view.LoginFragmentImpl;
import hr.tvz.andorid.shoppinglist.ui.register.presenter.RegisterPresenterImpl;

public class RegisterFragmentImpl extends Fragment implements RegisterFragment{

    @BindView(R.id.email_register)
    TextView email;
    @BindView(R.id.password_register)
    TextView password;
    @BindView(R.id.password_repeat_register)
    TextView repeatPassword;
    @BindView(R.id.register_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.register_button)
    Button registerButton;

    private RegisterPresenterImpl registerPresenter;

    public static RegisterFragmentImpl newInstance() {
        return new RegisterFragmentImpl();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    @OnClick(R.id.login_redirect_text)
    public void openLogin() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, LoginFragmentImpl.newInstance())
                .commitNow();
    }

    @Override
    @OnClick(R.id.register_button)
    public void registerUser() {
        registerPresenter.registerUser(email.getText().toString(), password.getText().toString(), repeatPassword.getText().toString());
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideToolBar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void hideRegisterButton() {
        registerButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRegisterButton() {
        registerButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


}
