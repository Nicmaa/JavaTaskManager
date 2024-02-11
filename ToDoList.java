import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ToDoList {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final String VIEW_COMMAND = "!view";
    private static final String REMOVE_COMMAND = "!remove";
    private static final String REMOVE_ALL_COMMAND = "!removeAll";
    private static final String EXIT_COMMAND = "!exit";

    public static void main(String[] args) {
        System.out.println("Improved ToDoList - Manage tasks using Java.");
        System.out.println("Type '" + VIEW_COMMAND + "' to view your list. Other command are: " + REMOVE_COMMAND + ", "+REMOVE_ALL_COMMAND+", "+ EXIT_COMMAND+".");
        System.out.println("Enter your tasks (format: task, dueDate - dd-MM-yyyy):");

        try (Scanner userInput = new Scanner(System.in)) {
            while (true) {
                System.out.print("-: ");
                String input = userInput.nextLine().trim();

                if (input.equals(VIEW_COMMAND)) {
                    printTaskList();
                } else if (input.startsWith(REMOVE_COMMAND)) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(REMOVE_COMMAND.length()).trim());
                        removeTask(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for task number.");
                    }
                } else if (input.equals(REMOVE_ALL_COMMAND)) {
                    removeAllTasks();
                } else if (input.equals(EXIT_COMMAND)) {
                    System.out.println("Exiting ToDoList. Have a great day!");
                    break;
                } else {
                    addTasks(input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    private static void addTasks(String input) {
        String[] taskInfo = input.split(",");
        if (taskInfo.length == 2) {
            String description = taskInfo[0].trim();
            String dateString = taskInfo[1].trim();

            try {
                Date dueDate = dateFormat.parse(dateString);
                Task newTask = new Task(description, dueDate);
                taskList.add(newTask);
                System.out.println("Task added.");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            }
        } else {
            System.out.println("Invalid input format. Please enter tasks as 'task, dueDate - dd-MM-yyyy'.");
        }
    }

    private static void removeTask(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskList.size()) {
            taskList.remove(taskNumber - 1);
            System.out.println("Task " + taskNumber + " removed.");
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    private static void removeAllTasks() {
        taskList.clear();
        System.out.println("All tasks removed.");
    }

    private static void printTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("\nYour task list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task.getDescription() + " (Due: " + dateFormat.format(task.getDueDate()) + ")");
            }
        }
    }

    private static class Task {
        private final String description;
        private final Date dueDate;

        public Task(String description, Date dueDate) {
            this.description = description;
            this.dueDate = dueDate;
        }

        public String getDescription() {
            return description;
        }

        public Date getDueDate() {
            return dueDate;
        }
    }
}
