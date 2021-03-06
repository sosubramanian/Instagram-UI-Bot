package instagram.messages;

public enum Messages {

    REQUEST_ACCEPTED ("Request accepted and will be processed immediately"),
    REQUEST_REJECTED ("Your request cannot be accepted at this time, please try again later"),
    REQUEST_QUEUED ("Your request is queued. Will be processed as soon we have availability"),
    REQUEST_EXISTS ("Request cannot be accepted since your previous request is already in place");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
