package Tony.FileManager;

import Tony.task.Task;
import Tony.task.Todo;
import Tony.task.Deadline;
import Tony.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";
    private ArrayList<Task> tasks;
    public FileSaver(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String saveTodo(Todo todo) {
        char type = 'T';
        String description = todo.description;
        int doneStatus = todo.toString().contains("[X]") ? 1 : 0;
        String toDoText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + System.lineSeparator();
        return toDoText;
    }

    public String saveDeadline(Deadline deadline) {
        char type = 'D';
        int doneStatus = deadline.toString().contains("[X]") ? 1 : 0;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        return deadlineText;
    }

    public String saveEvent(Event event) {
        char type = 'E';
        int doneStatus = event.toString().contains("[X]") ? 1 : 0;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + doneStatus + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        return eventText;
    }

    public void updateFile() throws IOException {
        checkForEmptyList();
        for (int i = 0; i < tasks.size(); i++) {
            String listItem = tasks.get(i).toString();
            char type = listItem.charAt(1);
            boolean isAppend = i != 0;
            switch (type) {
            case 'T':
                Todo todo = (Todo) tasks.get(i);
                String todoLine = saveTodo(todo);
                saveData(todoLine, isAppend);
                break;
            case 'D':
                Deadline deadline = (Deadline) tasks.get(i);
                String deadlineLine = saveDeadline(deadline);
                saveData(deadlineLine, isAppend);
                break;
            case 'E':
                Event event = (Event) tasks.get(i);
                String eventLine = saveEvent(event);
                saveData(eventLine, isAppend);
                break;
            default:
                saveData("", false);
            }
        }
    }

    private void checkForEmptyList() throws IOException {
        if (tasks.isEmpty()) {
            saveData("", false);
        }
    }

    public void saveData(String taskCommand, boolean isAppend) throws IOException {
        File file = new File(DATA_PATH);
        FileWriter fw = new FileWriter(file, isAppend);
        fw.write(taskCommand);
        fw.close();
    }
}
