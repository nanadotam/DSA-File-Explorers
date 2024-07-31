import java.util.ArrayList;
import java.util.List;

/**
 * Represents a folder in the file explorer.
 */
public class Folder extends FileExplorerElement {
    private List<FileExplorerElement> contents;

    /**
     * Constructs a new Folder with the specified name, date modified, and size.
     *
     * @param name the name of the folder
     * @param dateModified the date the folder was last modified
     * @param size the size of the folder
     */
    public Folder(String name, String dateModified, String size) {
        super(name, dateModified, size);
        this.contents = new ArrayList<>();
    }

    /**
     * Gets the contents of the folder.
     *
     * @return the contents of the folder
     */
    public List<FileExplorerElement> getContents() {
        return contents;
    }

    /**
     * Sets the contents of the folder.
     *
     * @param contents the new contents of the folder
     */
    public void setContents(List<FileExplorerElement> contents) {
        this.contents = contents;
    }

    /**
     * Returns a string representation of the folder.
     *
     * @return a string representation of the folder
     */
    @Override
    public String toString() {
        return super.toString() + " [Folder]";
    }
}
