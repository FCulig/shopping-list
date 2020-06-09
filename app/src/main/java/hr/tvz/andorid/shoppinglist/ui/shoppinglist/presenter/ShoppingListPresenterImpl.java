package hr.tvz.andorid.shoppinglist.ui.shoppinglist.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.tvz.andorid.shoppinglist.entities.ShoppingList;
import hr.tvz.andorid.shoppinglist.ui.shoppinglist.model.ShoppingListModelImpl;
import hr.tvz.andorid.shoppinglist.ui.shoppinglist.view.ShoppingListFragmentImpl;

public class ShoppingListPresenterImpl implements ShoppingListPresenter {
    private ShoppingListFragmentImpl shoppingListFragment;
    private ShoppingListModelImpl shoppingListModel;
    private ShoppingList shoppingList;
    private Context context;
    private String token;

    public ShoppingListPresenterImpl(ShoppingListFragmentImpl shoppingListFragment, Context context) {
        this.shoppingListFragment = shoppingListFragment;
        this.context = context;
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        token = mPreferences.getString("token", "");

        shoppingListModel = new ShoppingListModelImpl(this, token);
    }


    public void setupRecyclerView(Toolbar toolbar) {
        shoppingListFragment.recyclerView.setHasFixedSize(true);
        shoppingListFragment.layoutManager = new LinearLayoutManager(context);
        shoppingListFragment.recyclerView.setLayoutManager(shoppingListFragment.layoutManager);
        getShoppingItems();
        shoppingListFragment.mAdapter = new ShoppingListAdapter(shoppingList, context);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(shoppingListFragment.recyclerView);
        shoppingListFragment.recyclerView.setAdapter(shoppingListFragment.mAdapter);
        getShoppingCategories();
        //Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Podsjetnik");
    }

    public void getShoppingCategories() {
        shoppingListModel.makeShoppingCategoriesCall();
    }

    public void getShoppingItems() {
        shoppingListModel.makeShoppingListCall();
    }

    public ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            shoppingListModel.makeDeleteShoppingItemCall(shoppingList.getItems().get(viewHolder.getAdapterPosition()).getID());
            shoppingList.getItems().remove(viewHolder.getAdapterPosition());
            shoppingListFragment.mAdapter.notifyDataSetChanged();
        }
    };

    public void callSucceeded(ShoppingList list) {
        shoppingList = list;
        shoppingListFragment.mAdapter = new ShoppingListAdapter(shoppingList, context);
        shoppingListFragment.recyclerView.setAdapter(shoppingListFragment.mAdapter);
    }

    public void callFailed() {
        shoppingListFragment.makeToast("Došlo je do pogreške prilikom dohvaćanja podataka");
    }
}
