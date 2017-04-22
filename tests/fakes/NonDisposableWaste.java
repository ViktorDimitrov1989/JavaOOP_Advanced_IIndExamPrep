package tests.fakes;


import app.models.wastes.BaseGarbage;
import app.waste_disposal.contracts.Waste;

public class NonDisposableWaste extends BaseGarbage{

    protected NonDisposableWaste(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
