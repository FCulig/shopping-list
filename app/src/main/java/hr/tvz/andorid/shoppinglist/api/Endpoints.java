package hr.tvz.andorid.shoppinglist.api;

import java.util.ArrayList;

import hr.tvz.andorid.shoppinglist.entities.ShoppingCategory;
import hr.tvz.andorid.shoppinglist.entities.ShoppingList;
import hr.tvz.andorid.shoppinglist.entities.ShoppingListItem;
import hr.tvz.andorid.shoppinglist.entities.login.LoginResponseModel;
import hr.tvz.andorid.shoppinglist.entities.register.RegisterUserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Endpoints {
    @POST("api/Account/Register")
    Call<ResponseBody> registerUser(@Body RegisterUserModel user);

    @FormUrlEncoded
    @POST("Token")
    Call<LoginResponseModel> loginUser(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("api/ShoppingLists")
    Call<ShoppingList> getShoppingList(@Header("Authorization") String token);

    @GET("api/ShoppingCategories")
    Call<ArrayList<ShoppingCategory>> getShoppingCategoires(@Header("Authorization") String token);

    @POST("api/ShoppingItems")
    Call<ShoppingListItem> addItem(@Header("Authorization") String token, @Body ShoppingListItem item);

    @DELETE("api/ShoppingItems/{id}")
    Call<ResponseBody> deleteItem(@Header("Authorization") String token, @Path("id") int itemID);

    @PUT("api/ShoppingItems/{id}/check")
    Call<ResponseBody> checkItem(@Header("Authorization") String token, @Path("id") int itemID);
}
