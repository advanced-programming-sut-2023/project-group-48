package controller;

import model.Match.Property;
import model.Match.Request;
import model.User;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class TradeMenuController {
    private final Controller controller;

    public TradeMenuController(Controller controller) {
        this.controller = controller;
    }

    public String addTrade(String receiverUsername, String propertyType, int amount, int price, String message) {
        Property property = Property.getProperty(propertyType);
        User receiver = controller.getGame().getUserByUsername(receiverUsername);
        if (receiver == null) return "User not found";
        if (property == null) return "Invalid property";
        if (amount <= 0) return "Invalid resource amount";
        if (price <= 0) return "Invalid price";
        if (!controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().canStore(property))
            return "You don't have enough space in your storages!";

        controller.getGame().getCurrentMatch().sendTradeRequest(controller.getGame().getCurrentMatch().getCurrentPlayer(), receiver, property, amount, price, message);
        return "Trade request sent successfully!";
    }

    public ArrayList<Request> tradeList() {
        ArrayList<Request> requests = controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getReceivedRequests();
//        StringBuilder result = new StringBuilder("your trades:");
//        for (Request receivedRequest : requests) {
//            result.append("\n").append(receivedRequest.getId()).append(". Sender: ").append(receivedRequest.getSender().getOwner().getUsername()).
//                    append(", Property: ").append(receivedRequest.getProperty().getPropertyInString()).append(", Amount: ").
//                    append(receivedRequest.getAmount()).append(", Price: ").append(receivedRequest.getPrice()).
//                    append(", Sender's Message: ").append(receivedRequest.getSenderMessage());
//        }
//        return result.toString();
        return requests;
    }

    public String answerRequest(int id, String message, String answer) {
        boolean accepted = answer.equals("yes");
        Request request = controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getRequestById(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getReceivedRequests(), id);
        if (request == null) return "Invalid request id";
        if (request.getReceiver() != controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance())
            return "You are not the receiver of this request";
        if (!answer.equals("yes") && !answer.equals("no")) return "invalid answer!";
        if ((request.getReceiver().getPropertyCount(request.getProperty()) < request.getAmount()) && accepted)
            return "You don't have enough resources";
        if ((request.getSender().getPropertyCount(Property.COIN) < request.getPrice() * request.getAmount()) && accepted)
            return "The sender doesn't have enough gold";

        request.setAccepted(accepted);
        request.setReceiverMessage(message);
        if (accepted) {
            request.getReceiver().reduceProperty(request.getProperty(), request.getAmount());
            request.getSender().addProperty(request.getProperty(), request.getAmount());
            request.getReceiver().addProperty(Property.COIN, request.getPrice() * request.getAmount());
            request.getSender().reduceProperty(Property.COIN, request.getPrice() * request.getAmount());
        }
        request.getReceiver().getReceivedRequests().remove(request);
        request.getReceiver().getRequestsHistory().add(request);
        request.getSender().getSentRequests().remove(request);
        request.getSender().getRequestsHistory().add(request);
        return "Request answered successfully!";
    }

    public ArrayList<Request> tradeHistory() {
        ArrayList<Request> requests = controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getRequestsHistory();
//        StringBuilder result = new StringBuilder("your trade history:");
//        for (Request request : requests) {
//            result.append("\n").append(request.getId()).append(". Sender: ").append(request.getSender().getOwner().getUsername()).
//                    append(", Property: ").append(request.getProperty().getPropertyInString()).append(", Amount: ").
//                    append(request.getAmount()).append(", Price: ").append(request.getPrice()).append(", Sender's Message: ").
//                    append(request.getSenderMessage()).append(", Receiver's Message: ").append(request.getReceiverMessage()).
//                    append(", Answer: ").append(request.isAccepted() ? "Yes" : "No");
//        }
//        return result.toString();
        return requests;
    }
}