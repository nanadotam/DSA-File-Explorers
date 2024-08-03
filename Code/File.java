/**
 * Represents a file in the file explorer.
 */
public class File extends FileExplorerElement {
    private String fileType; //.docx, .pdf etc
    private String content;

    /**
     * Constructs a new File with the specified name, date modified, file type, and size.
     *
     * @param name the name of the file
     * @param dateModified the date the file was last modified
     * @param fileType the type of the file
     * @param size the size of the file
     */
    public File(String name, String dateModified, String size) {
        super(name, dateModified, size);
        String[] nameParts = name.split("\\.");
        this.fileType = nameParts[nameParts.length - 1];
        this.content = "";
    }

    /**
     * Sets the content of the file.
     *
     * @param content the new content of the file
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the content of the file.
     *
     * @return The content of the file.
     */
    public String viewContent() {
        return this.content;
    }
    /**
     * Gets the file type.
     *
     * @return the file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the new file type
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Returns a string representation of the file, including its type.
     *
     * @return a string representation of the file
     */
    @Override
    public String toString() {
        return getName() + fileType + " (Modified: " + getDateModified() + ", Size: " + getSize() + ")";
    }
}
