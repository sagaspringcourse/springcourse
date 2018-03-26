package rs.saga.domain;

public class Item {
    private long id;
    private String name;
    private String comment;
    private boolean isBought;
    private ShoppingCart cart;

    public Item() {
    }

    public Item(String name, ShoppingCart itemsList) {
        this.name = name;
        this.cart = itemsList;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
