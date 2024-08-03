# Changelog

## To be implemented:
### Directory.java
```

- public static void moveFileOrDirectory(String sourcePath, String destinationPath)
- public static void search(String attribute, String value)
- public static void displayDirectoryOutline()
- public static void sort(String attribute)
- public static boolean isValidAttribute(String attribute)
- public static void displayDirectoryOutline()
```

## Changes made:
### Folder.java
- Initialized contents array
- Imported Arraylist

### Directory.java
- temporarily made root private

### Main.java
- Currently Testing Directory.java - waiting for updates

### FileExplorer.java
- Changed from a while loop to do - while loop to enable scanner close properly

- Changed equals operation in while to prevent issues
- added success messages 


## Testing:

- TreeNode - Passed Tests
- Folder - Passed Tests
- File - Passed Tests
- FileExplorerElement - abstract - Passed Tests
- Directory
  - pathExists: Passed Tests
  - isValidAttribute : Passed Tests
  - **moveFileOrDirectory**: cant be determined yet 
  - **isFile**: not working as expected
  - **search**: not working as expected
  - **sort**: cant be determined yet
  - **deleteFile**: not working as expected

  - cant be determined yet = requires files to be displayed in outline
  - not working as expected = output does not match expected in main.java
