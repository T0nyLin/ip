package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand implements Command{
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private FileSaver fileSaver;
    private final Parser parser;
    public MarkCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        this.tasks = tasks;
        this.fileSaver = new FileSaver(this.tasks);
        try {
            String[] subCommand = USER_INPUT.split(" ");
            int num = Integer.parseInt(subCommand[1]);
            parser.checkNumberWithinRange(num);
            markTaskCommand(subCommand, num);
        } catch (NumberFormatException nfe) {
            System.out.println("Suggest only number after 'mark'!");
        } catch (TonyException | IOException e) {
            System.out.println("To mark a task, suggest a number available in the list!");
        }
    }
    public void markTaskCommand(String[] subCommand, int num) throws IOException {
        if (subCommand[0].equals("mark")) {
            tasks.get(num - 1).markDone();
            System.out.println("\tNice! I've marked this task as done:"
                    + System.lineSeparator() + "\t " + tasks.get(num - 1));
        } else {
            tasks.get(num - 1).markNotDone();
            System.out.println("\tOK, I've marked this task as not done yet:"
                    + System.lineSeparator() + "\t " + tasks.get(num - 1));
        }
        fileSaver.updateFile();
    }
}
