public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }
    public String type() {
        return "[E]";
    }

    @Override
    public String toString() {
        return type() + super.toString()
                + "(from: " + from
                + " to: " + to + ")";
    }
}
