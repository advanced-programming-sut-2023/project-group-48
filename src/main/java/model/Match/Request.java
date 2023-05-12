package model.Match;

public class Request {
    private static int idCounter = 0;
    private final int id;
    private final Governance sender;
    private final Governance receiver;
    private final Property property;
    private final int amount;
    private final int price;
    private final String senderMessage;
    private String receiverMessage;
    private boolean isAccepted;

    public Request(Governance sender, Governance receiver, Property property, int amount, int price, String senderMessage) {
        this.id = ++idCounter;
        this.sender = sender;
        this.receiver = receiver;
        this.property = property;
        this.amount = amount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.isAccepted = false;
    }

    public int getId() {
        return id;
    }

    public Governance getSender() {
        return sender;
    }

    public Governance getReceiver() {
        return receiver;
    }

    public Property getProperty() {
        return property;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}