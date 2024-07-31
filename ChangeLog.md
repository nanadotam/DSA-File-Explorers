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


## Testing:

- TreeNode - Passed Tests
- Folder - Passed Tests
- File - Passed Tests
- FileExplorerElement - abstract - Passed Tests
- Directory
  - pathExists: not working as expected
  - isFile: not working as expected
  - isValidAttribute : not working as expected
