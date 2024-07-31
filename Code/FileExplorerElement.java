/**
 * Represents a generic element in the file explorer, such as a file or folder.
 */
public abstract class FileExplorerElement {
    private String name;
    private String dateModified;
    private String size;

    /**
     * Constructs a new FileExplorerElement with the specified name, date modified, and size.
     *
     * @param name the name of the element
     * @param dateModified the date the element was last modified
     * @param size the size of the element
     */
    public FileExplorerElement(String name, String dateModified, String size) {
        this.name = name;
        this.dateModified = dateModified;
        this.size = size;
    }

    /**
     * Gets the name of the element.
     *
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the element.
     *
     * @param name the new name of the element
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date the element was last modified.
     *
     * @return the date the element was last modified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * Sets the date the element was last modified.
     *
     * @param dateModified the new date the element was last modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * Gets the size of the element.
     *
     * @return the size of the element
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the element.
     *
     * @param size the new size of the element
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Returns a string representation of the element.
     *
     * @return a string representation of the element
     */
    @Override
    public String toString() {
        return name + " (Modified: " + dateModified + ", Size: " + size + ")";
    }
}
