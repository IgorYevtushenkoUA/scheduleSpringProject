package logger;

public class ConsoleLoggerService implements LoggerService {
    public void log(String title, String text) {
        if (title != null)
            System.out.println("\n\t" + title + "\n");
        System.out.println(text);
    }
}