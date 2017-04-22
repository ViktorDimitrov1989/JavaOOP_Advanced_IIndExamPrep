package app.models.interpreters;

import java.lang.reflect.InvocationTargetException;

public interface Interpreter {

    String interpretCommand(String line) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;

}
