# Virtual File System Organiser

## Project Overview
This project presents the design and implementation of a file management or explorer system in Java, utilizing a tree data structure to represent hierarchical directory structures. The system comprises two primary classes: `File` and `Folder`, encapsulated within a `FileExplorerElement` superclass to ensure polymorphic behavior. The `Folder` class serves as a tree node, containing a list of `FileExplorerElement` objects as its children, thus enabling the representation of directories containing both files and subdirectories.

## Objectives
1. Develop a functional file explorer with a user-friendly GUI.
2. Implement essential file operations such as create, delete, and move.
3. Ensure accurate display of directory structures.
4. Provide a CLI for advanced users.
5. Optimize the application for performance.

## Design and Implementation Details

### Code Structure
- **Directory**: Manages the directory structure and file operations.
- **Folder**: Represents a storage container for files and other folders. Folders make up the directory and are the reason for the implementation of a tree data structure.
- **File**: Represents files.
- **FileExplorerElement**: Base class for files and folders.
- **FileExplorer**: Main GUI application class.
- **Main**: CLI interface for the file explorer.

### Description of Classes
- **Directory**: Contains methods for creating, deleting, moving files and directories, and displaying the directory structure.
- **Folder**: Extends `FileExplorerElement`, representing directories.
- **File**: Extends `FileExplorerElement`, representing files.
- **FileExplorer**: Implements the GUI using Java Swing, providing an interface for users to interact with the file system.
- **Main**: Provides a CLI interface for file operations.

## Data Structures and Algorithms Used

### Data Structures
- **Tree Structure**: Used to represent the file and directory hierarchy, enabling efficient traversal and management. It was chosen because of the hierarchical nature of file management systems.
- **ArrayList**: Used to store the contents of directories, allowing dynamic resizing and easy access.

### Algorithms
- **Depth-First Search (DFS)**: Used for displaying the directory structure and searching files.
- **Sorting Algorithm**: Custom sorting is implemented to sort files and directories based on various attributes (e.g. name, item type, size, date modified).

## Performance Analysis and Optimization Techniques

### Time Complexity, Worst Case Scenario Analysis
- **File and Directory Creation**: O(n), where n is the number of directories in the path.
- **File and Directory Deletion**: O(n), where n is the number of elements in the directory.
- **Directory Display**: O(n), where n is the total number of files and directories.
- **Sort**: O(n log n), where n is the total number of files and directories.
- **Search**: O(n), where n is the total number of files and directories.

### Optimization Techniques
- Efficient use of data structures like `ArrayList` and Trees for managing file and directory operations.
- Recursive algorithms for displaying and traversing directories to minimize code complexity.

## Challenges Faced and Solutions Implemented

### Challenge: Accurate Indentation for Directory Display
- **Solution**: Modified the recursive method to properly indent directory levels using a multiplier for spaces.

### Challenge: File Creation in Nested Directories
- **Solution**: Enhanced the `createFile` method to navigate through the directory path and create the file at the correct location.

### Challenge: Ensuring Robust Error Handling
- **Solution**: Introduced try-catch blocks across all methods to handle exceptions and provide meaningful error messages.

### GUI Integration
- **Solution**: Seamlessly integrated file operations with the GUI, ensuring user inputs are correctly handled and reflected in the file system.

## Instructions for Using the Application

### Running the GUI Application
1. Compile and run the `FileExplorer.java` file.
2. Use the provided GUI to create, delete, move files and directories, and view the directory structure.

### Running the CLI
1. Compile and run the `Main.java` file.
2. Enter commands as per the CLI instructions to perform file operations.


## Using the CLI
- `create_file <file_path>`: Create a new file at the specified path.
- `create_dir <dir_path>`: Create a new directory at the specified path.
- `delete <path>`: Delete the file or directory at the specified path.
- `move <source_path> <destination_path>`: Move a file or directory from the source path to the destination path.
- `search <attribute> <value>`: Search for files or directories based on a specified attribute and value.
- `sort <attribute>`: Sort files and directories by the specified attribute.
- `show_structure`: Display the current directory and file structure in a tree format.
