import java.util.Date;

/**
 * Represents the directory structure and provides methods to manipulate it.
 */
public class Directory {

    private static Folder root = new Folder("root", "01/01/2024", "0KB");

    /**
     * Checks if the given path is valid.
     *
     * @param path the path to validate
     * @return true if the path is valid, false otherwise
     */
    public static boolean isValidPath(String path) {
        return path != null && !path.trim().isEmpty();
    }

    /**
     * Creates a file at the specified file path.
     *
     * @param filePath the file path
     */
    public static void createFile(String filePath) {
        String[] parts = filePath.split("/");
        Folder current = root;
        if (pathExists(filePath)) {
            // file type will depend on user
            // file size...
            current.getContents().add(new File(parts[parts.length - 1], new Date().toString(), "txt", "1KB"));
        }
    }

    /**
     * Creates a directory at the specified directory path.
     *
     * @param dirPath the directory path
     */
    public static void createDirectory(String dirPath) {
        String[] parts = dirPath.split("/");
        Folder current = root;
        for (String part : parts) {
            Folder next = findDirectory(current, part);
            if (next == null) {
                next = new Folder(part, new Date().toString(), "0KB");
                current.getContents().add(next);
            }
            current = next;
        }
    }

    /**
     * Finds a directory with the given name in the specified folder.
     *
     * @param current the current folder
     * @param name the name of the directory to find
     * @return the found directory, or null if not found
     */
    public static Folder findDirectory(Folder current, String name) {
        for (FileExplorerElement element : current.getContents()) {
            if (element.getName().equals(name) && element instanceof Folder) {
                return (Folder) element;
            }
        }
        return null;
    }

    /**
     * Checks if the specified path exists.
     *
     * @param path the path to check
     * @return true if the path exists, false otherwise
     */
    public static boolean pathExists(String path) {
        if (!isValidPath(path)) {
            return false;
        }
        String[] parts = path.split("/");
        Folder current = root;
        for (int i = 0; i < parts.length - 1; i++) {
            current = findDirectory(current, parts[i]);
            if (current == null) {
                System.out.println("Invalid path: " + path);
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the specified path is a file.
     *
     * @param path the path to check
     * @return true if the path is a file, false otherwise
     */
    public static boolean isFile(String path) {
        if (isValidPath(path) && pathExists(path)) {
            String[] parts = path.split("/");
            Folder current = root;
            current.getContents().add(new File(parts[parts.length - 1], new Date().toString(), "txt", "1KB"));

            // Check if the last part of the path is a file
            for (FileExplorerElement element : current.getContents()) {
                if (element instanceof File && element.getName().equals(parts[parts.length - 1])) {
                    return true;
                }
            }
        }
    
        return false;
    }

    

    /**
     * Finds a file with the given name in the specified folder.
     *
     * @param current the current folder
     * @param name the name of the file to find
     * @return the found file, or null if not found
     */
    public static File findFile(Folder current, String name) {
        for (FileExplorerElement element : current.getContents()) {
            if (element.getName().equals(name) && element instanceof File) {
                return (File) element;
            }
        }
        return null;
    }

    /**
     * Deletes a file at the specified file path.
     *
     * @param filePath the file path
     */
    public static void deleteFile(String filePath) {
        // Implement logic to delete a file at filePath
    }

    /**
     * Deletes a directory at the specified directory path.
     *
     * @param dirPath the directory path
     */
    public static void deleteDirectory(String dirPath) {
        String[] parts = dirPath.split("/");
        Folder current = root;
        for (int i = 0; i < parts.length; i++) {
            if (i == parts.length - 1) {
                Folder dirToDel = findDirectory(current, parts[i]);
                if (dirToDel != null) {
                    current.getContents().remove(dirToDel);
                    System.out.println("Directory deleted: " + dirPath);
                } else {
                    System.out.println("Directory not found: " + dirPath);
                }
            } else {
                current = findDirectory(current, parts[i]);
                if (current == null) {
                    System.out.println("Invalid path: " + dirPath);
                    return;
                }
            }
        }
    }

    /**
     * Moves a file or directory from the source path to the destination path.
     *
     * @param sourcePath the source path
     * @param destinationPath the destination path
     */
    public static void moveFileOrDirectory(String sourcePath, String destinationPath) {
        if (isFile(sourcePath)) {
            //move the file to the destination 
        }


    }

    /**
     * Searches for files or directories based on the specified attribute and value.
     *
     * @param attribute the attribute to search by
     * @param value the value of the attribute
     */
    public static void search(String attribute, String value) {
        // Implement logic to search for files or directories based on attribute and value
    }

    /**
     * Checks if the specified attribute is valid.
     *
     * @param attribute the attribute to check
     * @return true if the attribute is valid, false otherwise
     */
    public static boolean isValidAttribute(String attribute) {
        // Valid Attributes: Name, Size, Item Type, Date Modified
        return true;
    }

    /**
     * Sorts files and directories based on the specified attribute.
     *
     * @param attribute the attribute to sort by
     */
    public static void sort(String attribute) {
        // Implement logic to sort files and directories based on attribute
    }

    /**
     * Displays the directory outline.
     */
    public static void displayDirectoryOutline() {
        // Implement logic to display the directory outline
    }

}
