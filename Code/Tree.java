import java.io.*;
import java.util.*;

// java code for above approach

class TreeNode {
    String data;
    List<TreeNode> children;

    public TreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

class FileExplorers {
    TreeNode root;

    public FileExplorers() {
        this.root = null;
    }

    // Get the parent node
    public TreeNode getParentNode(String path) {
        // Implement your logic here to find the parent node based on the given path
        // You can use any traversal algorithm (e.g., BFS, DFS) to search for the parent node
        // Return the parent node if found, otherwise return null
        
        // If the given path is the root path, return null as the root has no parent
        if (path.equals("/")) {
            return null;
        }
        
        // Split the path into individual directories
        String[] directories = path.split("/");
        
        // Start from the root node
        TreeNode currentNode = root;
        
        // Traverse through the directories in the path
        for (int i = 1; i < directories.length - 1; i++) {
            String directory = directories[i];
            
            // Check if the current node has a child with the given directory name
            boolean found = false;
            for (TreeNode child : currentNode.children) {
            if (child.data.equals(directory)) {
                currentNode = child;
                found = true;
                break;
            }
            }
            
            // If the child node is not found, return null as the parent node does not exist
            if (!found) {
            return null;
            }
        }
        
        // Return the parent node
        return currentNode;
    }
}
