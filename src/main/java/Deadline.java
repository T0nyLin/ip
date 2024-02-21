public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    public String type() {
        return "[D]";
    }

    @Override
    public String toString() {
        return type() + super.toString() + "(by:" + by + ")";
    }
}
