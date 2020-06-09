package hr.tvz.andorid.shoppinglist.ui.addshoppingitem.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tvz.andorid.shoppinglist.R;
import hr.tvz.andorid.shoppinglist.entities.IShoppingCategory;
import hr.tvz.andorid.shoppinglist.ui.addshoppingitem.presenter.AddShoppingItemPresenterImpl;

public class AddShoppingItemFragmentImpl extends Fragment implements AddShoppingItemFragment {

    private String token;
    private AddShoppingItemPresenterImpl addShoppingItemPresenter;

    @BindView(R.id.product_name_input)
    TextView productName;
    @BindView(R.id.product_quantity_input)
    TextView quantity;
    @BindView(R.id.item_input_category)
    Spinner category;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        addShoppingItemPresenter = new AddShoppingItemPresenterImpl(this, getActivity().getApplicationContext());

        ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.list_item, IShoppingCategory.getCategoryNames());
        category.setAdapter(adapter);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Novi proizvod");
    }

    @Override
    @OnClick(R.id.save_item)
    public void addItem() {
        addShoppingItemPresenter.addItem(productName.getText().toString(), quantity.getText().toString(), category.getSelectedItem().toString());
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void resetInputs(){
        productName.setText("");
        quantity.setText("");
    }
}
