package app.models.strategies;

import app.models.processingData.ProcessingDataImpl;
import app.waste_disposal.annotations.Storable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

@Storable
public class StorableDisposalStrategy implements GarbageDisposalStrategy{

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalVolume = garbage.getWeight() * garbage.getVolumePerKg();

        double processingDataEnergy = -1 * (totalVolume * 0.13);
        double processingDataCapital = -1 * (totalVolume * 0.65);

        return new ProcessingDataImpl(processingDataEnergy, processingDataCapital);
    }
}
