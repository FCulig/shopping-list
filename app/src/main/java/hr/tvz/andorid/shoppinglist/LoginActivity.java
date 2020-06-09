package hr.tvz.andorid.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hr.tvz.andorid.shoppinglist.ui.login.view.LoginFragmentImpl;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragmentImpl.newInstance())
                    .commitNow();
        }
    }
}
