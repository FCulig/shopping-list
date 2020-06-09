package hr.tvz.andorid.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import hr.tvz.andorid.shoppinglist.api.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingList extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getShoppingCategories();
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void test() {
        Log.d("TAG", "test: ");
    }

    private void getShoppingCategories() {
        Intent i = getIntent();
        token = i.getStringExtra("token");
        Log.d("CAT", token);

        Call<hr.tvz.andorid.shoppinglist.entities.ShoppingList> getCategoriesCall = ApiClient.getInstance().getEndpoints().getShoppingList(token);

        getCategoriesCall.enqueue(new Callback<hr.tvz.andorid.shoppinglist.entities.ShoppingList>() {
            @Override
            public void onResponse(Call<hr.tvz.andorid.shoppinglist.entities.ShoppingList> call, Response<hr.tvz.andorid.shoppinglist.entities.ShoppingList> response) {
                Log.d("CAT", response.message());
                if (response.isSuccessful()) {
                    Log.d("CAT", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<hr.tvz.andorid.shoppinglist.entities.ShoppingList> call, Throwable t) {
                Log.d("TAG", call.toString());
                Log.d("TAG", t.toString());
                showToast("Došlo je do pogreške prilikom dohvaćanja podataka");
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.shopping_list, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
