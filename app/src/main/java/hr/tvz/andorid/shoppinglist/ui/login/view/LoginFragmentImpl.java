package hr.tvz.andorid.shoppinglist.ui.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import androidx.preference.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tvz.andorid.shoppinglist.R;
import hr.tvz.andorid.shoppinglist.ShoppingList;
import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;
import hr.tvz.andorid.shoppinglist.ui.login.presenter.LoginPresenterImpl;
import hr.tvz.andorid.shoppinglist.ui.register.view.RegisterFragmentImpl;

public class LoginFragmentImpl extends Fragment implements LoginFragment {
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.email_login)
    TextView email;
    @BindView(R.id.password_login)
    TextView password;

    private LoginPresenterImpl loginPresenter;
    private String TAG = "LOGIN_FRAGMENT";

    public static LoginFragmentImpl newInstance() {
        return new LoginFragmentImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginPresenter = new LoginPresenterImpl(this);
        /*hideToolBar();
        hideProgressBar();*/
    }

    @Override
    @OnClick(R.id.register_redirect_text)
    public void openRegister() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, RegisterFragmentImpl.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    @OnClick(R.id.login_button)
    public void loginUser() {
        loginPresenter.loginUser(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void openShoppingList(LoginResponseModel response) {
        Intent myIntent = new Intent(getActivity(), ShoppingList.class);
        Log.d(TAG, response.toString());
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", response.getAccessToken());
        editor.apply();
        myIntent.putExtra("token", response.getAccessToken());
        getActivity().startActivity(myIntent);
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
    public void hideLoginButton() {
        loginButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoginButton() {
        loginButton.setVisibility(View.VISIBLE);
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
