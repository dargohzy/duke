package duke;

public class DukeException extends Exception{

    private String error;

    private final static String line = "    ____________________________________________________________";

    public DukeException(String error) {
        super(error);
        this.error = error;
    }

    @Override
    public String toString() {
        String output = line + "\n";
        if (this.error.equals("todo") || this.error.equals("deadline") || this.error.equals("event")) {
            output += "     :(  OOPS! The description of a " + this.error + " cannot be empty." + "\n";
        } else if (this.error.equals("dateTime")) {
            output += "     :(  OOPS! Please input deadline DATE and TIME in the format: <task> /by DD/MM/YYYY 2359." +
                    "\n";
        } else {
            output += "     :(  OOPS! I'm sorry, but I don't know what that means :(" + "\n";
        }
        output += line;

        return output;
    }

}
