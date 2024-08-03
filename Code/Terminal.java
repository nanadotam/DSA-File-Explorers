import java.util.ArrayList;

public class Terminal {
    public static void main(String[] args) {
        if (args.length == 0) {
            showUsage();
            return;
        }

        String command = args[0];

        try {
            switch (command) {
                case "create_file":
                    if (args.length != 2) {
                        System.out.println("Usage: create_file <file_path>");
                    } else {
                        createFile(args[1]);
                    }
                    break;
                case "create_dir":
                    if (args.length != 2) {
                        System.out.println("Usage: create_dir <dir_path>");
                    } else {
                        createDirectory(args[1]);
                    }
                    break;
                case "delete":
                    if (args.length != 2) {
                        System.out.println("Usage: delete <path>");
                    } else {
                        delete(args[1]);
                    }
                    break;
                case "move":
                    if (args.length != 3) {
                        System.out.println("Usage: move <source_path> <destination_path>");
                    } else {
                        move(args[1], args[2]);
                    }
                    break;
                case "search":
                    if (args.length != 3) {
                        System.out.println("Usage: search <attribute> <value>");
                    } else {
                        search(args[1], args[2]);
                    }
                    break;
                case "sort":
                    if (args.length != 2) {
                        System.out.println("Usage: sort <attribute>");
                    } else {
                        sort(args[1]);
                    }
                    break;
                case "show_structure":
                    if (args.length != 1) {
                        System.out.println("Usage: show_structure");
                    } else {
                        showStructure();
                    }
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    showUsage();
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void createFile(String filePath) throws Exception {
        if (!Directory.pathExists(filePath)) {
            Directory.createFile(filePath);
            System.out.println("File created: " + filePath);
        } else {
            System.out.println("File already exists: " + filePath);
        }
    }

    private static void createDirectory(String dirPath) throws Exception {
        if (!Directory.pathExists(dirPath)) {
            Directory.createDirectory(dirPath);
            System.out.println("Directory created: " + dirPath);
        } else {
            System.out.println("Directory already exists: " + dirPath);
        }
    }

    private static void delete(String path) throws Exception {
        if (Directory.isFile(path)) {
            Directory.deleteFile(path);
            System.out.println("File deleted: " + path);
        } else if (Directory.pathExists(path)) {
            Directory.deleteDirectory(path);
            System.out.println("Directory deleted: " + path);
        } else {
            System.out.println("Path does not exist: " + path);
        }
    }

    private static void move(String sourcePath, String destPath) throws Exception {
        if (Directory.pathExists(sourcePath)) {
            Directory.moveFileOrDirectory(sourcePath, destPath);
            System.out.println("Moved " + sourcePath + " to " + destPath);
        } else {
            System.out.println("Source path does not exist: " + sourcePath);
        }
    }

    private static void search(String attribute, String value) throws Exception {
        ArrayList<FileExplorerElement> results = Directory.search(attribute, value);
        if (results.isEmpty()) {
            System.out.println("No results found for " + attribute + " = " + value);
        } else {
            System.out.println("Search results:");
            for (FileExplorerElement element : results) {
                System.out.println(element.getName());
            }
        }
    }

    private static void sort(String attribute) throws Exception {
        Directory.sort(attribute);
        System.out.println("Sorted by " + attribute);
    }

    private static void showStructure() throws Exception {
        System.out.println("Directory structure:");
        Directory.displayDirectoryOutline();
    }

    private static void showUsage() {
        System.out.println("Usage:");
        System.out.println("  create_file <file_path>: Create a new file at the specified path.");
        System.out.println("  create_dir <dir_path>: Create a new directory at the specified path.");
        System.out.println("  delete <path>: Delete the file or directory at the specified path.");
        System.out.println("  move <source_path> <destination_path>: Move a file or directory from the source path to the destination path.");
        System.out.println("  search <attribute> <value>: Search for files or directories based on a specified attribute and value.");
        System.out.println("  sort <attribute>: Sort files and directories by the specified attribute.");
        System.out.println("  show_structure: Display the current directory and file structure in a tree format.");
    }
}
