import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the directory structure and provides methods to manipulate it.
 */
public class Directory {

    static Folder root = new Folder("root", new Date().toString(), "0KB");

    /**
     * Normalizes the given path to ensure it starts with root.
     *
     * @param path the path to normalize
     * @return the normalized path starting with root
     */
    private static String normalizePath(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "root";
        }
        path = path.trim();
        if (path.startsWith("root/")) {
            return path;
        }
        return "root/" + path;
    }

    /**
     * Checks if the given path is valid.
     *
     * @param path the path to validate
     * @return true if the path is valid, false otherwise
     */
    public static boolean isValidPath(String path) {
        return path != null && !path.trim().isEmpty();
    }
    
    
    // add check where program prompts user to create a file before creating a directory when directory does not exist

    /**
     * Creates a file at the specified file path.
     *
     * @param destinationPath the file path
     * @param filename        the name of the file
     */
    public static void createFile(String destinationPath, String filename) {
        if (!destinationPath.equals("root")) {
            destinationPath = normalizePath(destinationPath);
        }
    
        try {
            String[] parts = destinationPath.split("/");
            Folder current = root;
    
            if (parts.length == 1 && parts[0].equals("root")) {
                current.getContents().add(new File(filename, new Date().toString(), "1KB"));
            } else {
                for (int i = 1; i < parts.length; i++) {
                    current = findDirectory(current, parts[i]);
                    if (current == null) {
                        System.out.println("Directory does not exist. Create the directory first.");
                        return;
                    }
                }
                current.getContents().add(new File(filename, new Date().toString(), "1KB"));
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the file: " + filename);
            e.printStackTrace();
        }
    }    
    
    /**
     * Creates a new folder/directory at the specified directory path.
     *
     * @param dirPath the directory path
     */
    public static void createDirectory(String dirPath) {
        dirPath = normalizePath(dirPath);
    
        try {
            String[] parts = dirPath.split("/");
            Folder current = root;
            for (int i = 1; i < parts.length; i++) { // Skip "root"
                Folder next = findDirectory(current, parts[i]);
                if (next == null) {
                    next = new Folder(parts[i], new Date().toString(), "0KB");
                    current.getContents().add(next);
                }
                current = next;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the directory: " + dirPath);
            e.printStackTrace();
        }
    }
    

    /**
     * Checks if the specified path exists.
     *
     * @param path the path to check
     * @return true if the path exists, false otherwise
     */
    public static boolean pathExists(String path) {
        path = normalizePath(path);
        if (!isValidPath(path)) {
            return false;
        }
        String[] parts = path.split("/");
        Folder current = root;
        for (int i = 0; i < parts.length; i++) { // Skip "root"
            current = findDirectory(current, parts[i]);
            if (current == null) {
                return false;
            }
        }
        String lastPart = parts[parts.length - 1];
        // Check if it's a directory
        if (findDirectory(current, lastPart) != null) {
            return true;
        }
        // Check if it's a file
        if (findFile(current, lastPart) != null) {
            return true;
        }

        return false; // Neither directory nor file was found 
    }

    /**
     * Checks if the specified path is a file.
     *
     * @param path the path to check
     * @return true if the path is a file, false otherwise
     */
    public static boolean isFile(String path) {
        path = normalizePath(path);
        if (!pathExists(path)) {
            return false;
        }
    
        String[] parts = path.split("/");
        Folder current = root;
    
        for (int i = 1; i < parts.length - 1; i++) { // Skip "root"
            current = findDirectory(current, parts[i]);
            if (current == null) {
                return false;
            }
        }
    
        // Check if the last part is a file
        String lastPart = parts[parts.length - 1];
        for (FileExplorerElement element : current.getContents()) {
            if (element instanceof File && element.getName().equals(lastPart)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a file or directory with the given name in the specified folder.
     *
     * @param current the current folder
     * @param name    the name of the file or directory to find
     * @param type    the type of the element to find ("file" or "folder")
     * @return the found file or directory, or null if not found
     */

    public static FileExplorerElement findFileExplorerElement(Folder current, String name, String type) {
        for (FileExplorerElement element : current.getContents()) {
            if (element.getName().equals(name)) {
                if (type.equalsIgnoreCase("file") && element instanceof File) {
                    return (File) element;
                } else if (type.equalsIgnoreCase("folder") && element instanceof Folder) {
                    return (Folder) element;
                }
            }
        }
        return null;
    }


    /**
     * Finds a file with the given name in the specified folder.
     *
     * @param current the current folder
     * @param name    the name of the file to find
     * @return the found file, or null if not found
     */
    public static File findFile(Folder current, String name) {
        FileExplorerElement element = findFileExplorerElement(current, name, "file");
        return (element instanceof File) ? (File) element : null;
    }

    /**
     * Finds a directory with the given name in the specified folder.
     *
     * @param current the current folder
     * @param name    the name of the directory to find
     * @return the found directory, or null if not found
     */
    public static Folder findDirectory(Folder current, String name) {
        FileExplorerElement element = findFileExplorerElement(current, name, "folder");
        return (element instanceof Folder) ? (Folder) element : null;
    }



    /**
     * Sets the content of a file at the specified file path.
     *
     * @param filePath The path of the file to set the content for.
     * @param content The new content to set for the file.
     */
    public static void setContent(String filePath, String content) {
        if (!filePath.equals("root")) {
            filePath = normalizePath(filePath);
        }
    
        try {
            String[] parts = filePath.split("/");
            Folder current = root;
    
            if (parts.length == 1 && parts[0].equals("root")) {
                System.out.println("Cannot set content for the root directory.");
                return;
            } else {
                for (int i = 1; i < parts.length - 1; i++) {
                    current = findDirectory(current, parts[i]);
                    if (current == null) {
                        System.out.println("Invalid path: " + filePath);
                        return;
                    }
                }
    
                File file = findFile(current, parts[parts.length - 1]);
                if (file != null) {
                    file.setContent(content);
                    System.out.println("Content set for file: " + filePath);
                } else {
                    System.out.println("File not found: " + filePath);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while setting content for the file: " + filePath);
            e.printStackTrace();
        }
    }
    

    /**
     * Displays the content of a file specified by the given file path.
     * If the file path is "root", it indicates the root directory and the content cannot be viewed.
     * The file path can be a relative or absolute path.
     * 
     * @param filePath The path of the file to view its content.
     */
    public static void viewContent(String filePath) {
        if (!filePath.equals("root")) {
            filePath = normalizePath(filePath);
        }
    
        try {
            String[] parts = filePath.split("/");
            Folder current = root;
    
            if (parts.length == 1 && parts[0].equals("root")) {
                System.out.println("Cannot view content for the root directory.");
                return;
            } else {
                for (int i = 1; i < parts.length - 1; i++) {
                    current = findDirectory(current, parts[i]);
                    if (current == null) {
                        System.out.println("Invalid path: " + filePath);
                        return;
                    }
                }
    
                File file = findFile(current, parts[parts.length - 1]);
                if (file != null) {
                    System.out.println("Content of file " + filePath + ":");
                    System.out.println(file.viewContent());
                } else {
                    System.out.println("File not found: " + filePath);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while viewing content for the file: " + filePath);
            e.printStackTrace();
        }
    }
    

    /**
     * Deletes a file at the specified file path.
     *
     * @param filePath the file path
     */
    public static void deleteFile(String filePath) {
        if (!filePath.equals("root")) {
            filePath = normalizePath(filePath);
        }
    
        try {
            String[] parts = filePath.split("/");
            Folder current = root;
    
            if (parts.length == 1 && parts[0].equals("root")) {
                System.out.println("Cannot delete the root directory.");
                return;
            } else {
                for (int i = 1; i < parts.length - 1; i++) {
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
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the file: " + filePath);
            e.printStackTrace();
        }
    }
    
    

    /**
     * Deletes a directory at the specified directory path.
     *
     * @param dirPath the directory path
     */
    public static void deleteDirectory(String dirPath) {
        if (!dirPath.equals("root")) {
            dirPath = normalizePath(dirPath);
        }
    
        try {
            String[] parts = dirPath.split("/");
            Folder current = root;
    
            if (parts.length == 1 && parts[0].equals("root")) {
                System.out.println("Cannot delete the root directory.");
                return;
            } else {
                for (int i = 1; i < parts.length; i++) {
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
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the directory: " + dirPath);
            e.printStackTrace();
        }
    }    

    /**
     * Moves a file or directory from the source path to the destination path.
     *
     * @param sourcePath      the source path
     * @param destinationPath the destination path
     */
    public static void moveFileOrDirectory(String sourcePath, String destinationPath) {
        if (!sourcePath.equals("root")) {
            sourcePath = normalizePath(sourcePath);
        }
        if (!destinationPath.equals("root")) {
            destinationPath = normalizePath(destinationPath);
        }
    
        try {
            if (sourcePath.equals("root")) {
                System.out.println("Cannot move the root directory.");
                return;
            }
    
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
            for (int i = 1; i < sourceParts.length - 1; i++) { // Skip "root"
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
            for (int i = 1; i < destParts.length - 1; i++) { // Skip "root"
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
        } catch (Exception e) {
            System.out.println("An error occurred while moving from " + sourcePath + " to " + destinationPath);
            e.printStackTrace();
        }
    }
    
    

    /**
     * Searches for files or directories based on the specified attribute and value.
     *
     * @param attribute the attribute to search by
     * @param value     the value of the attribute
     * @return a list of matching file explorer elements
     */
    public static ArrayList<FileExplorerElement> search(String attribute, String value) {
        try {
            if (!isValidAttribute(attribute)) {
                System.out.println("Invalid attribute");
                return new ArrayList<>();
            }
            return searchRecursive(root, attribute, value);
        } catch (Exception e) {
            System.out.println("An error occurred while searching for " + attribute + ": " + value);
            e.printStackTrace();
            return new ArrayList<>();
        }
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
        try {
            if (!isValidAttribute(attribute)) {
                System.out.println("Invalid attribute");
                return;
            }
            sortRecursive(root, attribute);
        } catch (Exception e) {
            System.out.println("An error occurred while sorting by " + attribute);
            e.printStackTrace();
        }
    }
    

    // DFS, recursive implementation
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
        try {
            displayDirectoryRecursive(root, 0);
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the directory outline");
            e.printStackTrace();
        }
    }
    
    private static void displayDirectoryRecursive(Folder current, int level) {
        try {
            String indent = " ".repeat(level * 2);
            System.out.println(indent + current.getName() + "/");
            for (FileExplorerElement element : current.getContents()) {
                if (element instanceof Folder) {
                    displayDirectoryRecursive((Folder) element, level + 1);
                } else if (element instanceof File) {
                    System.out.println(indent + " " + element.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the directory structure");
            e.printStackTrace();
        }
    }
    

    public static void tree() {
        try {
            treeRecursive(root, "", true);
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the tree");
            e.printStackTrace();
        }
    }
    
    private static void treeRecursive(Folder current, String indent, boolean last) {
        try {
            System.out.println(indent + (last ? "└── " : "├── ") + current.getName() + "/");
            ArrayList<FileExplorerElement> contents = (ArrayList<FileExplorerElement>) current.getContents();
            for (int i = 0; i < contents.size(); i++) {
                FileExplorerElement element = contents.get(i);
                boolean isLast = (i == contents.size() - 1);
                if (element instanceof Folder) {
                    treeRecursive((Folder) element, indent + (last ? "    " : "│   "), isLast);
                } else if (element instanceof File) {
                    System.out.println(indent + (last ? "    " : "│   ") + (isLast ? "└── " : "├── ") + element.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying the tree structure");
            e.printStackTrace();
        }
    }

    public static void populateDefaultStructure() {
        try {
            // Create default folders
            String[] folders = {"computer", "networks", "trash_bin", "documents", "images", "apps", "school"};
            for (String folder : folders) {
                createDirectory("root/" + folder);
            }

            // Create default files
            createFile("root", "system.info");
            createFile("root/documents", "Valerie.c25");
            createFile("root/documents", "Amoako.c26");
            createFile("root/documents", "Ewura Abena.c25");
            createFile("root/documents", "Ben Charles.c25");
            createFile("root/school", "SummaCumLaude.GPA");

            // Add content to specific files
            Folder rootFolder = root;
            File systemInfo = findFile(rootFolder, "system.info");
            if (systemInfo != null) {
                systemInfo.setContent("Created by File Explorers: Valerie, Amoako, Ewura Abena, Ben Charles");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while populating the default structure.");
            e.printStackTrace();
        }
    }
    
}
