package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff  1.0",
    description = "Compares two configuration files and shows a difference."
)
public final class App implements Runnable {
    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String path1;

    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String path2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        try {
            System.out.println(Differ.generate(path1, path2, format));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
