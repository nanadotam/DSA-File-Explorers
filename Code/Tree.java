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


