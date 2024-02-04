import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("How many tasks do you have to do? ");
        int dim = in.nextInt();
        ArrayList<String> list = new ArrayList<>(dim);
        in.nextLine(); // Used to remove the nextLine that is pressed when confirming the previous input
        System.out.println("Enter the tasks to do (separated by a comma): ");
        String inputList = in.nextLine();

        String[] input = inputList.split(","); // Splits a string at the comma character
        for(String a : input) {
            list.add(a.trim()); // Trim is used to remove white spaces
        }

        System.out.println("\nYour daily task list:");
        for(int m=0; m<list.size();m++) {
            System.out.println(m+1+". "+list.get(m)); // Using m+1 to make the program more user-friendly
        }
        
        boolean b = true;
        while (b) {
            System.out.println("\nMenu:\n1. Add task\n2. Remove task\n3. Remove all tasks\n4. Print list\n5. Exit\nChoose the action to perform [1-5]:");
            int menu = in.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("\nWhich task do you want to add? ");
                    in.nextLine();
                    String addTask = in.nextLine();
                    list.add(addTask);
                    break;
                case 2:
                    System.out.println("\nWhich task do you want to remove? [Write the task number]");
                    int removeTask = in.nextInt();
                    list.remove(removeTask-1); // Decreasing the value by 1 to remove the correct task
                    break;
                case 3:
                    list.clear();
                    break;
                case 4:
                    System.out.println("\nYour updated daily task list:");
                    if(list.size()==0) {
                        System.out.println("Empty list.");
                        break;
                    }
                    for(int m=0; m<list.size();m++) {
                        System.out.println(m+1+". "+list.get(m));
                    }
                    break;
                case 5:
                    b=false;
                    break;
                default:
                    System.out.println("\nChoose a number between 1 and 5. Invalid number selected.");
                    break;
            }
        }
    }
}
