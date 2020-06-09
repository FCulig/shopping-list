package hr.tvz.andorid.shoppinglist.entities;

import com.google.gson.annotations.SerializedName;

public class ShoppingCategory {
    @SerializedName("ID")
    private int ID;
    @SerializedName("Name")
    private String name;

    public ShoppingCategory(int ID, String name) {
        this.ID = ID;
        this.name = name;
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


    @Override
    public String toString() {
        return "ShoppingCategory{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
