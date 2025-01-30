public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFile() {
        return "T" + super.toFile() ;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}