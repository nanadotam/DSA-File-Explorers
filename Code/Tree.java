import java.io.*;
import java.util.*;

// java code for above approach

public class TreeNode<T> {
    private final T root;
    private T parent;
    private List<TreeNode> children;

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


