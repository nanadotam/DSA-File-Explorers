/**
 * The Main class is the entry point of the program. It demonstrates the usage of the Directory class
 * to create directories, create files, display directory outline, perform various operations like
 * checking path validity, checking if a path exists, checking if a path points to a file, deleting
 * files and directories, moving files and directories, searching for files by name, sorting the
 * directory, and displaying the directory tree.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Create directories
            Directory.createDirectory("folder1");
            Directory.createDirectory("folder1/subfolder1");
            Directory.createDirectory("folder2");

            // Create files
            Directory.createFile("root/folder1", "file1.txt");
            Directory.createFile("root/folder1/subfolder1", "file2.txt");
            Directory.createFile("root/folder2", "file3.txt");
            Directory.createFile("root/folder3", "file4.txt");  // Folder doesn't exist; should print false
            Directory.createFile("root", "test.txt"); 
            Directory.createFile("root", "gh.txt");     
            Directory.createFile("root", "un.txt");     


            // Display directory outline
            System.out.println("Directory Outline:");
            Directory.tree();

            // Test isValidPath method
            System.out.println("\nIs Valid Path 'folder1': " + Directory.isValidPath("folder1")); // Should print true
            System.out.println("Is Valid Path '': " + Directory.isValidPath("")); // Should print false

            // Test pathExists method
            System.out.println("\nPath Exists 'folder1': " + Directory.pathExists("folder1")); // Should print true
            System.out.println("Path Exists 'folder3': " + Directory.pathExists("folder3")); // Should print false

            // Test isFile method
            System.out.println("\nIs File 'folder1/file1.txt': " + Directory.isFile("folder1/file1.txt")); // Should print true
            System.out.println("Is File 'folder1/subfolder1': " + Directory.isFile("folder1/subfolder1")); // Should print false

            // Test deleteFile method
            Directory.deleteFile("un.txt");
            System.out.println("\nAfter deleting 'un'.txt':");
            Directory.deleteFile("folder1/file1.txt");
            System.out.println("\nAfter deleting 'folder1/file1.txt':");
            Directory.tree();

            // Test deleteDirectory method
            Directory.deleteDirectory("folder1/subfolder1");
            System.out.println("\nAfter deleting 'folder1/subfolder1':");
            Directory.tree();

            // Test moveFileOrDirectory method
            Directory.moveFileOrDirectory("folder2/file3.txt", "folder1/file3.txt");
            System.out.println("\nAfter moving 'folder2/file3.txt' to 'folder1/file3.txt':");
            Directory.tree();

            // Test search method
            System.out.println("\nSearch by name 'file3.txt':");
            for (FileExplorerElement element : Directory.search("name", "file3.txt")) {
                System.out.println(element.getName());
            }

            // Test sort method
            Directory.sort("name");
            System.out.println("\nAfter sorting by name:");
            Directory.tree();

            // Test search with different attribute
            System.out.println("\nSearch by item type 'file':");
            for (FileExplorerElement element : Directory.search("item type", "file")) {
                System.out.println(element.getName());
            }

            // Test tree method
            System.out.println();
            Directory.tree();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
