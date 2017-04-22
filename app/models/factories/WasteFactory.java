package app.models.factories;

import app.models.wastes.BaseGarbage;
import app.models.wastes.BurnableGarbage;
import app.models.wastes.RecyclableGarbage;
import app.models.wastes.StorableGarbage;
import app.waste_disposal.contracts.Waste;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WasteFactory {
    private static final String WASTES_PACKAGE_PATH = "app.models.wastes.";
    private static final String GARBAGE_CLASS_SUFFIX = "Garbage";

    @SuppressWarnings("unchecked")
    public Waste generateWaste(String... tokens) throws
            ClassNotFoundException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        String classPath = WASTES_PACKAGE_PATH + tokens[3] + GARBAGE_CLASS_SUFFIX;

        Class<? extends BaseGarbage> garbageClass = (Class<? extends BaseGarbage>) Class.forName(classPath);
        Constructor<? extends BaseGarbage> garbageConstructor =
                (Constructor<? extends BaseGarbage>) garbageClass.getDeclaredConstructors()[0];

        String name = tokens[0];
        double weight = Double.parseDouble(tokens[1]);
        double volumePerKg = Double.parseDouble(tokens[2]);

        return garbageConstructor.newInstance(name,weight,volumePerKg);
    }

}
