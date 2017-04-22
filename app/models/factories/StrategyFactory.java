package app.models.factories;

import app.waste_disposal.annotations.Disposable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.StrategyHolder;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StrategyFactory {
    private static final String STRATEGIES_PACKAGE_PATH = "app.models.strategies";

    private StrategyHolder strategyHolder;

    public StrategyFactory(StrategyHolder strategyHolder) throws ClassNotFoundException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        this.strategyHolder = strategyHolder;
    }

    public void initializeFactories() throws
            ClassNotFoundException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        String userDirPath = System.getProperty("user.dir");


        File file = new File(userDirPath + "\\src\\" + STRATEGIES_PACKAGE_PATH.replaceAll("\\.", "\\\\"));
        File[] classesFiles = file.listFiles();

        GarbageDisposalStrategy currentStrategy;
        Class<?> currentCLass;
        for (File classesFile : classesFiles) {
            if(classesFile.isFile()){
                String className = classesFile.getName().substring(0,classesFile.getName().indexOf("."));

                currentCLass =
                        Class.forName(STRATEGIES_PACKAGE_PATH + "." + className);

                Constructor<?> currentConstructor = currentCLass.getDeclaredConstructors()[0];

                currentStrategy = (GarbageDisposalStrategy) currentConstructor.newInstance();

                Annotation[] strategyAnnotations = currentCLass.getAnnotations();
                if(strategyAnnotations[0].annotationType().isAnnotationPresent(Disposable.class)){
                    this.strategyHolder.addStrategy(strategyAnnotations[0].annotationType(), currentStrategy);
                }
            }
        }
    }
}
