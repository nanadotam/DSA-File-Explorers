import java.util.Scanner;

public class FileExplorer {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String user_input;

        System.out.println("Welcome to File Manager by File Explorers");
        System.out.println("Type 'help' for help. Enter command:");

        while (true) {
            user_input = scanner.nextLine();
            String[] command_terms = user_input.split(" ");

            switch (command_terms[0]) {
                case "help":
                    displayAvailableCommands();
                    break;

                case "create_file":
                    if (Directory.isValidPath(command_terms[1])) {
                        Directory.createFile(command_terms[1]);
                    } else {
                        System.out.println("Invalid file path. Please try again.");
                    }
                    break;

                case "create_dir":
                    if (Directory.isValidPath(command_terms[1])) {
                        Directory.createDirectory(command_terms[1]);
                    } else {
                        System.out.println("Invalid directory path. Please try again.");
                    }
                    break;

                case "del":
                    if (Directory.isValidPath(command_terms[1])) {
                        if (Directory.pathExists(command_terms[1])) {
                            if (Directory.isFile(command_terms[1])) {
                                Directory.deleteFile(command_terms[1]);
                            } else {
                                System.out.println("File does not exist. Please try again.");
                            }
                        } else {
                            System.out.println("Path does not exist. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid path. Please try again.");
                    }
                    break;

                case "del_dir":
                    if (Directory.isValidPath(command_terms[1])) {
                        if (Directory.pathExists(command_terms[1])) {
                            Directory.deleteDirectory(command_terms[1]);
                        } else {
                            System.out.println("Path does not exist. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid path. Please try again.");
                    }
                    break;

                case "move":
                    if (Directory.isValidPath(command_terms[1]) && Directory.isValidPath(command_terms[2])) {
                        if (Directory.pathExists(command_terms[1])) {
                            Directory.moveFileOrDirectory(command_terms[1], command_terms[2]);
                        } else {
                            System.out.println("Source path does not exist. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid paths. Please try again.");
                    }
                    break;

                case "search":
                    if (command_terms.length == 3) {
                        String attribute = command_terms[1];
                        String value = command_terms[2];
                        Directory.search(attribute, value);
                    } else {
                        System.out.println("Invalid search parameters. Please try again.");
                    }
                    break;

                case "sort":
                    if (Directory.isValidAttribute(command_terms[1])) {
                        Directory.sort(command_terms[1]);
                    } else {
                        System.out.println("Invalid sort attribute. Please try again.");
                    }
                    break;

                case "show_structure":
                    Directory.displayDirectoryOutline();
                    break;

                case "exit":
                    System.exit(0);

                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }
    }

    private static void displayAvailableCommands() {
        System.out.println("Available commands:");
        System.out.println("help");
        System.out.println("create_file <file_path>");
        System.out.println("create_dir <dir_path>");
        System.out.println("del <path>");
        System.out.println("del_dir <dir_path>");
        System.out.println("move <source_path> <destination_path>");
        System.out.println("search <attribute> <value>");
        System.out.println("sort <attribute>");
        System.out.println("show_structure");
        System.out.println("exit");
    }
}
