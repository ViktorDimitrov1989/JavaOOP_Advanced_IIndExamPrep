package app.models.strategies;

import app.models.processingData.ProcessingDataImpl;
import app.waste_disposal.annotations.Burnable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

@Burnable
public class BurnableDisposalStrategy implements GarbageDisposalStrategy{

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalVolume = garbage.getWeight() * garbage.getVolumePerKg();

        double processingDataEnergy = totalVolume * 0.8;
        double processingDataCapital = 0;

        return new ProcessingDataImpl(processingDataEnergy, processingDataCapital);
    }
}
