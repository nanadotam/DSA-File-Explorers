import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create root directory
        Folder root = Directory.root;

        // Test isValidPath method
        System.out.println("Is Valid Path '/folder1': " + Directory.isValidPath("/folder1")); // Should print true
        System.out.println("Is Valid Path '': " + Directory.isValidPath("")); // Should print false

        // Test createDirectory method
        Directory.createDirectory("folder1");
        Directory.createDirectory("folder1/subfolder1");
        Directory.createDirectory("folder2");

        // Display root directory contents
        System.out.println("Root Directory Contents:");
        for (FileExplorerElement element : root.getContents()) {
            System.out.println(element);
        }

        // Test createFile method
        Directory.createFile("folder1/file1");
        Directory.createFile("folder1/subfolder1/file2");


        // Display folder1 contents
        Folder folder1 = Directory.findDirectory(root, "folder1");
        System.out.println("Folder1 Contents:");
        assert folder1 != null;
        for (FileExplorerElement element : folder1.getContents()) {
            System.out.println(element);
        }


        // Test pathExists method
        System.out.println("Path Exists 'folder1': " + Directory.pathExists("folder1")); // Should print true
        System.out.println("Path Exists 'folder3': " + Directory.pathExists("folder3")); // Should print false

        // Test isFile method
        System.out.println("Is File 'folder1/file1.txt': " + Directory.isFile("folder1/file1")); // Should print true
        System.out.println("Is File 'folder1/subfolder1': " + Directory.isFile("folder1/subfolder1")); // Should print false

        System.out.println();
        // Test deleteFile method
        Directory.deleteFile("folder1/file1");
        System.out.println("Folder1 Contents After Deleting file1.txt:"); // to be worked on
        for (FileExplorerElement element : folder1.getContents()) {
            System.out.println(element);
        }

        System.out.println();

        // Test deleteDirectory method
        Directory.deleteDirectory("folder1/subfolder1");
        System.out.println("Folder1 Contents After Deleting subfolder1:");
        for (FileExplorerElement element : folder1.getContents()) {
            System.out.println(element);
        }

        System.out.println();

        // Test moveFileOrDirectory method (implement logic as needed)

        // Test search method (implement logic as needed)

        // Test isValidAttribute method
        System.out.println("Is Valid Attribute 'Name': " + Directory.isValidAttribute("Name")); // Should print true
        System.out.println("Is Valid Attribute 'InvalidAttr': " + Directory.isValidAttribute("InvalidAttr")); // Should print false

        // Test sort method (implement logic as needed)

        // Test displayDirectoryOutline method (implement logic as needed)
    }
}
