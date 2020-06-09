package hr.tvz.andorid.shoppinglist.ui.shoppinglist.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.tvz.andorid.shoppinglist.R;
import hr.tvz.andorid.shoppinglist.ui.shoppinglist.presenter.ShoppingListPresenterImpl;

public class ShoppingListFragmentImpl extends Fragment implements ShoppingListFragment {

    @BindView(R.id.shopping_list)
    public RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;

    private ShoppingListPresenterImpl shoppingListPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        shoppingListPresenter = new ShoppingListPresenterImpl(this, getActivity().getApplicationContext());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        shoppingListPresenter.setupRecyclerView(getActivity().findViewById(R.id.toolbar));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ");
        shoppingListPresenter.getShoppingItems();
    }

    public void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
