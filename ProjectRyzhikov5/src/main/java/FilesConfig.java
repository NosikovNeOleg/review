import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesConfig {

    public String GetStringPath(int fileNumber) {
        switch (fileNumber) {
            case 1 -> {
                return "C:\\Users\\Dima\\IdeaProjects\\mts-homework-ryzhikov\\ProjectRyzhikov5\\src\\main\\resources\\animals\\logData.txt";
            }
            case 2 -> {
                return "C:\\Users\\Dima\\IdeaProjects\\mts-homework-ryzhikov\\ProjectRyzhikov5\\src\\main\\resources\\results\\findOlderAnimals.json";
            }
            default -> {
                return "";
            }
        }
    }

    public Path GetPath(int fileNumber) {
        switch (fileNumber) {
            case 1 -> {
                return Paths.get("src", "main", "resources", "animals", "logData.txt");
            }
            case 2 -> {
                return Paths.get("src", "main", "resources", "results", "findOlderAnimals.json");
            }
            default -> {
                return null;
            }
        }
    }
}
