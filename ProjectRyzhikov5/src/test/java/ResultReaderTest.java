import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultReaderTest {

    @Mock
    private FilesConfig filesConfig;

    @InjectMocks
    private ResultReader resultReader;

    //ResultReader resultReader = new ResultReader();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    Path path = Paths.get("src", "test", "resources", "animals", "logDataForResultReader.txt");
    String pathOlders = "C:\\Users\\Dima\\IdeaProjects\\mts-homework-ryzhikov\\ProjectRyzhikov5\\src\\main\\resources\\results\\findOlderAnimals.json";

    @Test
    void printAnimalJson() {
        System.setOut(new PrintStream(output));
        when(filesConfig.GetStringPath(2)).thenReturn(pathOlders);
        resultReader.PrintAnimalJson();
        Assert.assertTrue(output.toString().trim().contains("Persik"));
        Assert.assertTrue(output.toString().trim().contains("Barsik"));
        Assert.assertTrue(output.toString().trim().contains("Ryba"));
        Assert.assertTrue(output.toString().trim().contains("Bobik"));
        System.setOut(null);
    }

    @Test
    void logDataRowsCountPrint() {
        System.setOut(new PrintStream(output));
        when(filesConfig.GetPath(1)).thenReturn(path);
        resultReader.LogDataRowsCountPrint();
        Assert.assertTrue(output.toString().trim().contains("Всего строк в файле лога: 26"));

        System.setOut(null);
    }

    @Test
    void logDataRowsCount() {
        when(filesConfig.GetPath(1)).thenReturn(path);
        Assert.assertEquals(26, resultReader.LogDataRowsCount());
    }
}