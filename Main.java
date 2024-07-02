import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int userInput = prompt();

        while (userInput != 1) {
			// if user input is 2, will use read file function
            switch (userInput) {
                case 2:
                    readFile();
                    userInput = prompt();
                    break;
			// if user input is 3, will use write file function
                case 3:
                    writeFile();
                    userInput = prompt();
                    break;
            }
        }

        System.out.println("Program end!");
    }

    public static int prompt() {
        int choice = -1;
        boolean intOK = false;

        while (!intOK) {
            try {
                menu();
				// Make a Scanner object to read from System.in
                Scanner obj = new Scanner(System.in);
				
				// Read an int from user choice
                choice = obj.nextInt();

				// if choice from user input not in the menu, will prompt user to enter correct number
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter correct number.");
                    prompt();
                } else {
                    intOK = true;
                }
			// go to catch block if input mismatch
            } catch (InputMismatchException e) {
                System.out.println("Please enter correct number.");
            }
        }

        return choice;
    }

    public static void menu() {
        // Instruction
        String[] arr = new String[5];
        arr[0] = "*                 Main Menu                   *";
        arr[1] = "* Please enter your function from below menu  *";
        arr[2] = "* Enter 1 to quit the program                 *";
        arr[3] = "* Enter 2 to read from file                   *";
        arr[4] = "* Enter 3 to write to file                    *";

        // Print the top frame
        for (int i = 0; i <= 46; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print the instruction with the frame
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // Print the bottom frame
        for (int i = 0; i <= 46; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void readFile() {
        try {
            // Prompt the user for a file name
            System.out.print("Please input the file name: ");
			// Make a Scanner userInput to read from System.in
            Scanner userInput = new Scanner(System.in);
            String fileName = userInput.next();

            System.out.println("How many line you would like to read?");
            System.out.println("(If you would like to read the whole file, please type \"all\" and press Enter.)");
            System.out.print("Input: ");
            String lines = userInput.next();
            int counter = 0;
            int stop = 0;
			
			// read all lines in the file
            if (lines.equals("all")) {
                stop = -1;
			// read n lines in the file according to user input 
            } else {
                stop = Integer.parseInt(lines);
            }

            File name = new File(fileName);
            Scanner file = new Scanner(name);
			
            pline();
			
			//print the lines of the file
            while (file.hasNextLine() && counter != stop) {
                String line = file.nextLine();
                System.out.println(line);
                counter += 1;
            }

            pline();
            re();
		// go to catch block if file not found
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
        }
    }

    public static void writeFile() {
        try {
            System.out.print("Please input the file name: ");
			// Make a Scanner object to read from System.in
            Scanner userInput = new Scanner(System.in);
            String fileName = userInput.nextLine();

            File name = new File(fileName);
			// file name duplicate with the Main.java will not be allowed to edit
            if (name.getName().equals("Main.java")) {
                System.out.println("This file cannot be edited.");
                re();
            } else {
				// Create new file if file
                if (name.createNewFile()) {
                    System.out.println("File created: " + name.getName());
                } else {
                    System.out.println("File already existed!");
                }

                System.out.println("Please write your content below");
                System.out.println("Once you finish, please type 'end' and press Enter");
                pline();
				
				// Create a FileWriter to write the file
                FileWriter mywriter = new FileWriter(name);
                String line = "";
				// click end to save the file after input
                while (!line.equals("end\n")) {
                    line = userInput.nextLine() + "\n";
                    if (line.equals("end\n")) {
                        break;
                    }
                    mywriter.write(line);
                }
                mywriter.close();
                pline();
                System.out.println("New content has been created");
                re();
            }
		// go to catch block if exception occured
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    // Print line
    public static void pline() {
        for (int i = 0; i < 45; i++) {
            System.out.print("*");
        }
        System.out.println("*");
    }

    // Notify the user it is about to return to the main menu.
    public static void re() {
        System.out.println("Return to main menu...");
    }
}
