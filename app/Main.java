package app;

import app.models.engine.Engine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Engine engine = new Engine();

        engine.run();
    }
}
