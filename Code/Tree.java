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

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
  }

    public TreeNode<T> getRoot() { 
        return root;
    }

    
    
}


