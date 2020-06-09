package hr.tvz.andorid.shoppinglist.entities;

import java.util.ArrayList;
import java.util.List;

public class IShoppingCategory {
    public static List<ShoppingCategory> categories;

    public static List<String> getCategoryNames() {
        List<String> names = new ArrayList<>();

        for (ShoppingCategory cat : categories) {
            names.add(cat.getName());
        }

        return names;
    }

    public static int getIdFromName(String name) {
        int id = 0;

        for (ShoppingCategory cat : categories) {
            if (cat.getName().equals(name)) {
                id = cat.getID();
            }
        }

        return id;
    }

    public static String getNameFromId(int id) {
        String name = null;

        if (categories != null) {
            for (ShoppingCategory cat : categories) {
                if (cat.getID() == id) {
                    name = cat.getName();
                    break;
                }
            }
        }

        return name;
    }
}
