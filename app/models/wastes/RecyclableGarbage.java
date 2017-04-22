package app.models.wastes;

import app.waste_disposal.annotations.Recyclable;
import app.waste_disposal.contracts.Waste;

@Recyclable
public class RecyclableGarbage extends BaseGarbage{

    public RecyclableGarbage(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
