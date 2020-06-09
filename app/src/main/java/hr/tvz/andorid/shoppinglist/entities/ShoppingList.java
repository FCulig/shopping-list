package hr.tvz.andorid.shoppinglist.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingList {
    @SerializedName("ID")
    private int id;
    @SerializedName("UserID")
    private String userID;
    @SerializedName("Items")
    private List<ShoppingListItem> items;

    public ShoppingList(int id, String userID, List<ShoppingListItem> items) {
        this.id = id;
        this.userID = userID;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", items=" + items +
                '}';
    }
}
