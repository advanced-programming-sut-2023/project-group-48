package model.Match;

public class Request {
    private final Governance sender;
    private final Governance receiver;
    private final Property property;
    private final int amount;
    private final int price;
    private final String senderMessage;
    private String receiverMessage;
    private boolean isAccepted;

    public Request(Governance sender, Governance receiver, Property property, int amount, int price, String senderMessage) {
        this.sender = sender;
        this.receiver = receiver;
        this.property = property;
        this.amount = amount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.isAccepted = false;
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