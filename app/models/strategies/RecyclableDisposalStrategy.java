package app.models.strategies;

import app.models.processingData.ProcessingDataImpl;
import app.waste_disposal.annotations.Recyclable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

@Recyclable
public class RecyclableDisposalStrategy implements GarbageDisposalStrategy{

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalVolume = garbage.getWeight() * garbage.getVolumePerKg();

        double processingDataEnergy = -1 * (totalVolume * 0.5);
        double processingDataCapital = 400 * garbage.getWeight();

        return new ProcessingDataImpl(processingDataEnergy, processingDataCapital);
    }
}
