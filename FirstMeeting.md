# Schedule

1. Core Functionality Development
2. Algorithms implementation   
   a. Searching   
   b. Sorting   
   c. BFS/DFS    
3. GUI development (javafx/java swing)



## Core Functionality Development (mostly from chatGPT)

1. **Create the Java Project**:
   - Use an IDE like IntelliJ IDEA, Eclipse, or a simple text editor with command-line tools.
   - Create a new Java project and set up the package structure, for example, `com.team.dsa.fileexplorer`.

2. **Define Data Structures**:
   - Use classes to represent files and directories. A simple structure can be:
     ```java
     public class VirtualFile {
         private String name;
         private String content; // Optional, for file content
         
         // Constructors, getters, setters, etc.
     }
     
     public class VirtualDirectory {
         private String name;
         private Map<String, VirtualFile> files;
         private Map<String, VirtualDirectory> directories;
         
         // Constructors, getters, setters, etc.
     }
     ```

#### Implementing Core Commands

3. **Create Command Interface**:
   - Define a command interface to enforce a consistent structure for all commands.
     ```java
     public interface Command {
         void execute(String[] args);
     }
     ```

4. **Implement `create_file` Command**:
   - Create a class to handle file creation.
     ```java
     public class CreateFileCommand implements Command {
         private VirtualDirectory root;

         public CreateFileCommand(VirtualDirectory root) {
             this.root = root;
         }

         @Override
         public void execute(String[] args) {
             if (args.length < 1) {
                 System.out.println("Usage: create_file <file_path>");
                 return;
             }
             String filePath = args[0];
             // Logic to create the file in the virtual directory
         }
     }
     ```

5. **Implement `create_dir` Command**:
   - Create a class to handle directory creation.
     ```java
     public class CreateDirCommand implements Command {
         private VirtualDirectory root;

         public CreateDirCommand(VirtualDirectory root) {
             this.root = root;
         }

         @Override
         public void execute(String[] args) {
             if (args.length < 1) {
                 System.out.println("Usage: create_dir <dir_path>");
                 return;
             }
             String dirPath = args[0];
             // Logic to create the directory in the virtual directory
         }
     }
     ```

6. **Implement `delete` Command**:
   - Create a class to handle file and directory deletion.
     ```java
     public class DeleteCommand implements Command {
         private VirtualDirectory root;

         public DeleteCommand(VirtualDirectory root) {
             this.root = root;
         }

         @Override
         public void execute(String[] args) {
             if (args.length < 1) {
                 System.out.println("Usage: delete <path>");
                 return;
             }
             String path = args[0];
             // Logic to delete the file or directory in the virtual directory
         }
     }
     ```

7. **Implement `move` Command**:
   - Create a class to handle moving files and directories.
     ```java
     public class MoveCommand implements Command {
         private VirtualDirectory root;

         public MoveCommand(VirtualDirectory root) {
             this.root = root;
         }

         @Override
         public void execute(String[] args) {
             if (args.length < 2) {
                 System.out.println("Usage: move <source_path> <destination_path>");
                 return;
             }
             String sourcePath = args[0];
             String destinationPath = args[1];
             // Logic to move the file or directory within the virtual directory
         }
     }
     ```

#### Initial Testing

8. **Write Unit Tests**:
   - Use JUnit or another testing framework to write tests for each command.
     ```java
     public class CommandTests {
         private VirtualDirectory root;

         @BeforeEach
         public void setUp() {
             root = new VirtualDirectory("root");
         }

         @Test
         public void testCreateFile() {
             CreateFileCommand createFileCommand = new CreateFileCommand(root);
             createFileCommand.execute(new String[] { "root/file1.txt" });
             // Assertions to check if the file was created
         }

         @Test
         public void testCreateDir() {
             CreateDirCommand createDirCommand = new CreateDirCommand(root);
             createDirCommand.execute(new String[] { "root/dir1" });
             // Assertions to check if the directory was created
         }

         @Test
         public void testDelete() {
             DeleteCommand deleteCommand = new DeleteCommand(root);
             // Pre-create files/directories for deletion test
             deleteCommand.execute(new String[] { "root/file1.txt" });
             // Assertions to check if the file was deleted
         }

         @Test
         public void testMove() {
             MoveCommand moveCommand = new MoveCommand(root);
             // Pre-create source and destination for move test
             moveCommand.execute(new String[] { "root/file1.txt", "root/dir1/file1.txt" });
             // Assertions to check if the file was moved
         }
     }
     ```

9. **Manual Testing**:
   - Run the application and manually test each command using the CLI to ensure they work as expected.

### Example of Command Execution

- **Main Application Loop**:
  ```java
  public class FileExplorer {
      private static Map<String, Command> commands = new HashMap<>();

      public static void main(String[] args) {
          VirtualDirectory root = new VirtualDirectory("root");
          commands.put("create_file", new CreateFileCommand(root));
          commands.put("create_dir", new CreateDirCommand(root));
          commands.put("delete", new DeleteCommand(root));
          commands.put("move", new MoveCommand(root));

          Scanner scanner = new Scanner(System.in);
          while (true) {
              System.out.print("> ");
              String input = scanner.nextLine();
              String[] parts = input.split(" ");
              String command = parts[0];
              String[] commandArgs = Arrays.copyOfRange(parts, 1, parts.length);

              if (commands.containsKey(command)) {
                  commands.get(command).execute(commandArgs);
              } else {
                  System.out.println("Unknown command: " + command);
              }
          }
      }
  }
  ```
