package hr.tvz.andorid.shoppinglist.ui.shoppinglist.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.tvz.andorid.shoppinglist.R;
import hr.tvz.andorid.shoppinglist.api.ApiClient;
import hr.tvz.andorid.shoppinglist.entities.IShoppingCategory;
import hr.tvz.andorid.shoppinglist.entities.ShoppingList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder> {
    private ShoppingList shoppingList;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemQuantity;
        public TextView itemCategory;
        public CheckBox boughtIndicator;

        public MyViewHolder(View v) {
            super(v);
            itemName = v.findViewById(R.id.item_name);
            itemQuantity = v.findViewById(R.id.item_quantity);
            boughtIndicator = v.findViewById(R.id.bought_indicator);
            itemCategory = v.findViewById(R.id.item_category);
        }
    }

    public ShoppingListAdapter(ShoppingList myDataset, Context c) {
        context = c;
        shoppingList = myDataset;
    }

    @Override
    public ShoppingListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemName.setText(shoppingList.getItems().get(position).getName());
        holder.itemQuantity.setText("x" + shoppingList.getItems().get(position).getQuantity());
        holder.itemCategory.setText("(" + IShoppingCategory.getNameFromId(shoppingList.getItems().get(position).getCategoryID()) + ")");
        holder.boughtIndicator.setChecked(shoppingList.getItems().get(position).isBought());

        holder.boughtIndicator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkItem(shoppingList.getItems().get(position).getID());
            }
        });
    }

    private void checkItem(int itemID) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPreferences.edit();

        String token = mPreferences.getString("token", "");
        Call<ResponseBody> checkItemCall = ApiClient.getInstance().getEndpoints().checkItem("Bearer " + token, itemID);
        checkItemCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("CAT", response.message());
                if (response.isSuccessful()) {
                    Log.d("CAT", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", call.toString());
                Log.d("TAG", t.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (shoppingList == null) {
            return 0;
        }
        return shoppingList.getItems().size();
    }
}
