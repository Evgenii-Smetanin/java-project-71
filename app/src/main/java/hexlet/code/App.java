package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff  1.0",
    description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {
    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String path1;

    @Parameters(paramLabel = "filepath1", index = "0", description = "path to second file")
    private String path2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public void run() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
