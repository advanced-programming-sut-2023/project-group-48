package model.Match;

public class Trade {
    private final Resource resource;
    private final int amount;
    private final int price;
    private final String message;

    public Trade(Resource resource, int amount, int price, String message) {
        this.resource = resource;
        this.amount = amount;
        this.price = price;
        this.message = message;
    }

    public Resource getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }
}
