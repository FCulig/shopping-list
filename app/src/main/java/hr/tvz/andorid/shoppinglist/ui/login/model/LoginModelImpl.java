package hr.tvz.andorid.shoppinglist.ui.login.model;

import android.util.Log;

import hr.tvz.andorid.shoppinglist.api.ApiClient;
import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;
import hr.tvz.andorid.shoppinglist.entities.login.LoginUserModel;
import hr.tvz.andorid.shoppinglist.ui.login.presenter.LoginPresenterImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModelImpl implements LoginModel {
    private String TAG = "LOGIN_MODEL";
    private LoginPresenterImpl loginPresenter;

    public LoginModelImpl(LoginPresenterImpl loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void makeLoginCall(LoginUserModel user) {
        Call<LoginResponseModel> loginCall = ApiClient.getInstance().getEndpoints().loginUser(user.getGrantType(), user.getUsername(), user.getPassword());

        loginCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    loginPresenter.loginSuccessful(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d(TAG, call.toString());
                Log.d(TAG, t.toString());
                loginPresenter.loginFailed();
            }
        });
    }
}
