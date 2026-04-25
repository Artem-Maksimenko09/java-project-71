package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

import static hexlet.code.Differ.printFiles;

@Command(
        name = "gendiff",
        version = "gendiff 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Parameters(index = "0",
            paramLabel = "filepath1",
            description = "path to first file")
    String filepath1;

    @Parameters(index = "1",
            paramLabel = "filepath2",
            description = "path to second file")
    String filepath2;

    @Option(names = { "-f", "--format" },
            description = "output format [default: stylish]",
            paramLabel = "format")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            printFiles(filepath1, filepath2);
        } catch (IOException e) {
            System.out.println("Проверьте что файл"
                    + " существует и к нему есть доступ");
        }
        return 0;
    }
}
