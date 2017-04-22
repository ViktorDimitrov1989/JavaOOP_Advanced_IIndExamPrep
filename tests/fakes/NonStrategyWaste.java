package tests.fakes;

import app.models.wastes.BaseGarbage;

@DisposableAnnotation
public class NonStrategyWaste extends BaseGarbage{
    protected NonStrategyWaste(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
