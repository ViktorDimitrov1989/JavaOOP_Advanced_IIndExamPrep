package app.models.io;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
