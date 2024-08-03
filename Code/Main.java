public class Main {
    public static void main(String[] args) {
        // Create directories
        Directory.createDirectory("folder1");
        Directory.createDirectory("folder1/subfolder1");
        Directory.createDirectory("folder2");

        // Create files
        Directory.createFile("folder1", "file1");
        Directory.createFile("folder1/subfolder1", "file2");
        Directory.createFile("folder2", "file3");

        // Display directory outline
        System.out.println("Directory Outline:");
        Directory.displayDirectoryOutline();

        // Test isValidPath method
//        System.out.println("\nIs Valid Path '/folder1': " + Directory.isValidPath("/folder1")); // Should print true
//        System.out.println("Is Valid Path '': " + Directory.isValidPath("")); // Should print false

        // Test pathExists method
//        System.out.println("\nPath Exists 'folder1': " + Directory.pathExists("folder1")); // Should print true
//        System.out.println("Path Exists 'folder3': " + Directory.pathExists("folder3")); // Should print false

        // Test isFile method
        System.out.println("\nIs File 'folder1/file1.txt': " + Directory.isFile("folder1/file1.txt")); // Should print true
        System.out.println("Is File 'folder1/subfolder1': " + Directory.isFile("folder1/subfolder1")); // Should print false
        System.out.println();

        // Test deleteFile method
        Directory.deleteFile("folder1/file1.txt");
        System.out.println("\nAfter deleting 'folder1/file1.txt':");
        Directory.displayDirectoryOutline();

        // Test deleteDirectory method
//        Directory.deleteDirectory("folder1/subfolder1");
//        System.out.println("\nAfter deleting 'folder1/subfolder1':");
//        Directory.displayDirectoryOutline();

        // Test moveFileOrDirectory method
        Directory.moveFileOrDirectory("folder2/file3.txt", "folder1/file3.txt");
        System.out.println("\nAfter moving 'folder2/file3.txt' to 'folder1/file3.txt':");
        Directory.displayDirectoryOutline();

        // Test search method
        System.out.println("\nSearch by name 'file3.txt':");
        for (FileExplorerElement element : Directory.search("name", "file3.txt")) {
            System.out.println(element.getName());
        }

        // Test sort method
        Directory.sort("name");
        System.out.println("\nAfter sorting by name:");
        Directory.displayDirectoryOutline();

        // Test search with different attribute
        System.out.println("\nSearch by item type 'file':");
        for (FileExplorerElement element : Directory.search("item type", "file")) {
            System.out.println(element.getName());
        }
    }
}
