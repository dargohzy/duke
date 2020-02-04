package duke.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Task implements Serializable {

    // task = Full input line
    // type = todo, event, or deadline
    // done = done or not done

    String task;
    String type;
    boolean isDone = false;
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");


    // Tasks represented by their initials
    private final static String TODO = "T";
    private final static String DEADLINE = "D";
    private final static String EVENT = "E";

    public Task(String type, String task) {

        if (type.equals("todo")) {
            this.type = TODO;
        } else if (type.equals("deadline")) {
            this.type = DEADLINE;
        } else if (type.equals("event")) {
            this.type = EVENT;
        }

        this.task = task;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public String print_Format() throws DukeException {
        try {
            String checkmark = "N";

            if (this.isDone == true) {
                checkmark = "Y";
            }

            String output = "[" + this.type + "]" + "[" + checkmark + "] ";
            if (this.type.equals(EVENT)) {
                String task_Name1 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
                String day_And_Time = task.substring(task.indexOf("/") + 4);
                output += task_Name1 + " (at: " + day_And_Time + ")";
            } else if (this.type.equals(DEADLINE)) {
                String task_Name2 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
                String day = task.substring(task.indexOf("/") + 4);
                output += task_Name2 + " (by: " + day + ")";
            } else {
                String task_Name3 = task.substring(task.indexOf(" "));
                output += task_Name3;

            }

            return output;
        } catch (Exception e) {
            throw new DukeException(type);
        }
    }

    @Override
    public String toString() {

        String output = "";

        try {
            output += this.print_Format();
        } catch (DukeException e) {
            System.out.println(e);
        }

        return output;
    }
}
