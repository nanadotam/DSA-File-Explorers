import java.util.ArrayList;

class TreeNode<T> {
    private final T root;
    private TreeNode<T> parent;
    private final ArrayList<TreeNode<T>> children;

    public TreeNode(T root) {
        this.root = root;
        children = new ArrayList<>();
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public T getRoot() {
        return root;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

  public int getLevel() {
    if (this.isRoot())
      return 0;
    else
      return parent.getLevel() + 1;
  }

  @Override
  public String toString() {
    return root != null ? root.toString() : "null";
  }
}
