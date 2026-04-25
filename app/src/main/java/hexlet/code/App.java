package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        version = "gendiff 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.\n" +
                "filepath1         path to first file\n" +
                "filepath2         path to second file")
public class App implements Runnable {

    @Option(names = { "-f", "--format" },
            description = "output format [default: stylish]",
            paramLabel = "format")
    private String format = "format";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}
