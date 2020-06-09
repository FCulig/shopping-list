package hr.tvz.andorid.shoppinglist.ui.register.model;

import android.util.Log;

import hr.tvz.andorid.shoppinglist.api.ApiClient;
import hr.tvz.andorid.shoppinglist.entities.register.RegisterUserModel;
import hr.tvz.andorid.shoppinglist.ui.register.presenter.RegisterPresenterImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModelImpl implements RegisterModel {
    private String TAG = "REGISTER_MODEL";
    private RegisterPresenterImpl registerPresenter;

    public RegisterModelImpl(RegisterPresenterImpl registerPresenter) {
        this.registerPresenter = registerPresenter;
    }

    @Override
    public void makeRegisterUserCall(RegisterUserModel registerUserModel){
        Call<ResponseBody> registerCall = ApiClient.getInstance().getEndpoints().registerUser(registerUserModel);

        registerCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    registerPresenter.registerSuccessful();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, call.toString());
                Log.d(TAG, t.toString());
                registerPresenter.registerFailed();
            }
        });
    }
}
