import java.util.ArrayList;

/**
 * Represents a node in a tree data structure.
 *
 * @param <T> the type of data stored in the node
 */
class TreeNode<T> {
    private final T root;
    private TreeNode<T> parent;
    private final ArrayList<TreeNode<T>> children;

    /**
     * Constructs a new TreeNode with the specified root data.
     *
     * @param root the root data of the node
     */
    public TreeNode(T root) {
        this.root = root;
        children = new ArrayList<>();
    }

    public TreeNode(T root, ArrayList<TreeNode<T>> children){
        this.root = root;
        this.children = children;
    }

    /**
     * Adds a child node with the specified data to this node.
     *
     * @param child the data of the child node
     * @return the newly added child node
     */
    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    // Nana
    /**
     * Returns the child node at the specified index.
     *
     * @param index the index of the child node
     * @return the child node at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public TreeNode<T> getChildren(int index) {
        if (index < 0 || index >= children.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return children.get(index);
    }

    /**
     * Returns the root data of this node.
     *
     * @return the root data of this node
     */
    public T getRoot() {
        return root;
    }

    /**
     * Checks if this node is the root of the tree.
     *
     * @return true if this node is the root, false otherwise
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Checks if this node is a leaf node (i.e., has no children).
     *
     * @return true if this node is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * Returns the level of this node in the tree.
     *
     * @return the level of this node
     */
    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    /**
     * Returns a string representation of this node.
     *
     * @return a string representation of this node
     */
    @Override
    public String toString() {
        return root != null ? root.toString() : "null";
    }
}
