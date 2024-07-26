import java.io.*;
import java.util.*;

// java code for above approach

class TreeNode<T> {
    T data;
    List<TreeNode> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

class FileExplorers {
    TreeNode root;

    public FileExplorers() {
        this.root = null;
    }


