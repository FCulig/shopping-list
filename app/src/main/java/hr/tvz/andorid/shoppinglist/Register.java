package hr.tvz.andorid.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hr.tvz.andorid.shoppinglist.ui.register.view.RegisterFragmentImpl;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RegisterFragmentImpl.newInstance())
                    .commitNow();
        }
    }
}
