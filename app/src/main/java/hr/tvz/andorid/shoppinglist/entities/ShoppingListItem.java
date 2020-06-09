package hr.tvz.andorid.shoppinglist.entities;

import com.google.gson.annotations.SerializedName;

public class ShoppingListItem {
    @SerializedName("ID")
    private int ID;
    @SerializedName("Name")
    private String name;
    @SerializedName("UserID")
    private String userID;
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("ListID")
    private int listID;
    @SerializedName("CategoryID")
    private int categoryID;
    @SerializedName("Category")
    private ShoppingCategory category;
    @SerializedName("Bought")
    private boolean bought;

    public ShoppingListItem(String name, int categoryID, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public ShoppingListItem(int ID, String name, String userID, int quantity, int listID, int categoryID, ShoppingCategory category, boolean bought) {
        this.ID = ID;
        this.name = name;
        this.userID = userID;
        this.quantity = quantity;
        this.listID = listID;
        this.categoryID = categoryID;
        this.category = category;
        this.bought = bought;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public ShoppingCategory getCategory() {
        return category;
    }

    public void setCategory(ShoppingCategory category) {
        this.category = category;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "ShoppingListItem{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", userID='" + userID + '\'' +
                ", quantity=" + quantity +
                ", listID=" + listID +
                ", categoryID=" + categoryID +
                ", category=" + category +
                ", bought=" + bought +
                '}';
    }
}
