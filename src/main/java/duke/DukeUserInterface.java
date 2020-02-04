package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import duke.command.Parser;
import duke.task.TaskList;

public class DukeUserInterface {

    private static final String LINE = "    ____________________________________________________________";
    private static final String OPENING_GREETING = "     Hello! I'm Duke\n" + "     What can I do for you?";
    private static final String FAREWELL_CLOSING = LINE + "\n" + "     Bye. Hope to see you again soon!" + "\n" + LINE;

    private InputStreamReader stringReader;
    private BufferedReader commandReader;
    private Storage internalStorage;
    private Parser commandParser;
    private TaskList listOfTasks;

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";



    public DukeUserInterface(String filePath) throws DukeException{
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + "\n" + OPENING_GREETING + "\n" + LINE);

        this.stringReader = new InputStreamReader(System.in);
        this.commandReader = new BufferedReader(this.stringReader);
        this.internalStorage = new Storage(filePath);
        this.listOfTasks = new TaskList(this.internalStorage.load());
        this.commandParser = new Parser(this.listOfTasks);
    }

    public void runDuke() {

        try {
            String input = commandReader.readLine();

            while (!input.equals("bye")) {
                commandParser.executeCommand(input);
                input = commandReader.readLine();
            }

            this.internalStorage.save(this.listOfTasks.getData());
            System.out.println(FAREWELL_CLOSING);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}