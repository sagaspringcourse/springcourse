package rs.saga.domain;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private long id;
    private String name;
    private List<Item> items = new LinkedList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
