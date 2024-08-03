import java.util.ArrayList;
import java.util.Date;


/**
 * Represents the directory structure and provides methods to manipulate it.
 */
public class Directory {

    static Folder root = new Folder("root", new Date().toString(), "0KB");
    // Should be private later on, made public for testing

    /** TESTED
     * Checks if the given path is valid.
     *
     * @param path the path to validate
     * @return true if the path is valid, false otherwise
     */
    public static boolean isValidPath(String path) {
        return path != null && !path.trim().isEmpty();
    }

    /** TESTED
     * Creates a file at the specified file path.
     *
     * @param filePath the file path
     */
    public static void createFile(String destinationPath, String filename) {
        if (pathExists(destinationPath)) {
            String[] parts = destinationPath.split("/");
            Folder current = root;
    
        for (int i = 0; i < parts.length - 1; i++) {
            current = findDirectory(current, parts[i]);
            if (current == null) {
                return;
            }
        }
        current.getContents().add(new File(filename, new Date().toString(), "1KB")); //Random
        }
    }

    /** TESTED
     * Creates a new folder/directory at the specified directory path.
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

    /** UNTESTED
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

    /** TESTED
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
        for (String part : parts) {
            current = findDirectory(current, part);
            if (current == null) {
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
        if (!isValidPath(path)) {
            return false;
        }
    
        String[] parts = path.split("/");
        Folder current = root;
    
        for (int i = 0; i < parts.length - 1; i++) {
            current = findDirectory(current, parts[i]);
            if (current == null) {
                return false;
            }
        }
    
        // Check if the last part is a file
        String lastPart = parts[parts.length - 1];
        for (FileExplorerElement element : 
        
        
        current.getContents()) {
            if (element instanceof File && element.getName().equals(lastPart)) {
                return true;
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
        if (isFile(filePath)) {
            String[] parts = filePath.split("/");
            Folder current = root;
            for (int i = 0; i < parts.length - 1; i++) {
                current = findDirectory(current, parts[i]);
                if (current == null) {
                    System.out.println("Invalid path: " + filePath);
                    return;
                }
            }
            File fileToDelete = findFile(current, parts[parts.length - 1]);
            if (fileToDelete != null) {
                current.getContents().remove(fileToDelete);
                System.out.println("File deleted: " + filePath);
            } else {
                System.out.println("File not found: " + filePath);
            }
        } else {
            System.out.println("Invalid file path: " + filePath);
        }
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
        // Validate paths
        if (!isValidPath(sourcePath) || !isValidPath(destinationPath)) {
            System.out.println("Invalid path");
            return;
        }

        // Determine if source is file or directory
        boolean isFile = isFile(sourcePath);
        if (!isFile && !pathExists(sourcePath)) {
            System.out.println("Source path does not exist");
            return;
        }

        String[] sourceParts = sourcePath.split("/");
        String[] destParts = destinationPath.split("/");

        // Navigate to source parent directory
        Folder sourceParent = root;
        for (int i = 0; i < sourceParts.length - 1; i++) {
            sourceParent = findDirectory(sourceParent, sourceParts[i]);
            if (sourceParent == null) {
                System.out.println("Invalid source path: " + sourcePath);
                return;
            }
        }

        // Find source element (file or directory)
        FileExplorerElement sourceElement;
        if (isFile) {
            sourceElement = findFile(sourceParent, sourceParts[sourceParts.length - 1]);
        } else {
            sourceElement = findDirectory(sourceParent, sourceParts[sourceParts.length - 1]);
        }

        if (sourceElement == null) {
            System.out.println("Source not found");
            return;
        }

        // Navigate to destination parent directory
        Folder destParent = root;
        for (int i = 0; i < destParts.length - 1; i++) {
            destParent = findDirectory(destParent, destParts[i]);
            if (destParent == null) {
                System.out.println("Invalid destination path: " + destinationPath);
                return;
            }
        }

        // Move the element
        sourceParent.getContents().remove(sourceElement);
        destParent.getContents().add(sourceElement);
        System.out.println("Moved " + sourceElement.getName() + " to " + destinationPath);
    }

    /**
     * Searches for files or directories based on the specified attribute and value.
     *
     * @param attribute the attribute to search by
     * @param value the value of the attribute
     */
    public static ArrayList<FileExplorerElement> search(String attribute, String value) {
        if (!isValidAttribute(attribute)) {
            System.out.println("Invalid attribute");
            return new ArrayList<>();
        }
        return searchRecursive(root, attribute, value);
    }

    private static ArrayList<FileExplorerElement> searchRecursive(Folder current, String attribute, String value) {
        ArrayList<FileExplorerElement> results = new ArrayList<>();
        for (FileExplorerElement element : current.getContents()) {
            if (matchesAttribute(element, attribute, value)) {
                results.add(element);
            }
            if (element instanceof Folder) {
                results.addAll(searchRecursive((Folder) element, attribute, value));
            }
        }
        return results;
    }

    private static boolean matchesAttribute(FileExplorerElement element, String attribute, String value) {
        switch (attribute.toLowerCase()) {
            case "name":
                return element.getName().equalsIgnoreCase(value);
            case "size":
                return element.getSize().equalsIgnoreCase(value);
            case "item type":
                return (element instanceof File && value.equalsIgnoreCase("file")) ||
                        (element instanceof Folder && value.equalsIgnoreCase("folder"));
            case "date modified":
                return element.getDateModified().equalsIgnoreCase(value);
            default:
                return false;
        }
    }

    /**
     * Checks if the specified attribute is valid.
     *
     * @param attribute the attribute to check
     * @return true if the attribute is valid, false otherwise
     */
    public static boolean isValidAttribute(String attribute) {
        // Valid Attributes: Name, Size, Item Type, Date Modified
        return attribute.equalsIgnoreCase("name") ||
                attribute.equalsIgnoreCase("size") ||
                attribute.equalsIgnoreCase("item type") ||
                attribute.equalsIgnoreCase("date modified");
    }

    /**
     * Sorts files and directories based on the specified attribute.
     *
     * @param attribute the attribute to sort by
     */
    public static void sort(String attribute) {
        if (!isValidAttribute(attribute)) {
            System.out.println("Invalid attribute");
            return;
        }
        sortRecursive(root, attribute);
    }

    //DFS, recursive implementation
    private static void sortRecursive(Folder current, String attribute) {
        current.getContents().sort((e1, e2) -> compareByAttribute(e1, e2, attribute));
        for (FileExplorerElement element : current.getContents()) {
            if (element instanceof Folder) {
                sortRecursive((Folder) element, attribute);
            }
        }
    }

    private static int compareByAttribute(FileExplorerElement e1, FileExplorerElement e2, String attribute) {
        switch (attribute.toLowerCase()) {
            case "name":
                return e1.getName().compareToIgnoreCase(e2.getName());
            case "size":
                return e1.getSize().compareToIgnoreCase(e2.getSize());
            case "item type":
                return (e1 instanceof File ? 1 : 0) - (e2 instanceof File ? 1 : 0);
            case "date modified":
                return e1.getDateModified().compareToIgnoreCase(e2.getDateModified());
            default:
                return 0;
        }
    }

    /**
     * Displays the directory outline.
     */
    public static void displayDirectoryOutline() {
        displayDirectoryRecursive(root, 0);
    }

    //DFS, recursive implementation
    private static void displayDirectoryRecursive(Folder current, int level) {
        String indent = " ".repeat(level * 2);
        System.out.println(indent + current.getName() + "/");
        for (FileExplorerElement element : current.getContents()) {
            if (element instanceof Folder) {
                displayDirectoryRecursive((Folder) element, level + 1);
            } else if (element instanceof File) {
                System.out.println(indent + " " + element.getName());
            }
        }
    }
}
