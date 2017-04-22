package app.models.interpreters;
import app.models.factories.WasteFactory;
import app.models.recylcingStation.Station;
import app.waste_disposal.contracts.GarbageProcessor;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

import java.lang.reflect.InvocationTargetException;

public class CommandInterpreter implements Interpreter{
    private WasteFactory wasteFactory;
    private GarbageProcessor garbageProcessor;
    private Station recyclingStation;

    public CommandInterpreter(WasteFactory wasteFactory, GarbageProcessor garbageProcessor, Station recyclingStation) {
        this.wasteFactory = wasteFactory;
        this.garbageProcessor = garbageProcessor;
        this.recyclingStation = recyclingStation;
    }

    @Override
    public String interpretCommand(String line) throws ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        String command = line.split(" ")[0];

        switch (command){
            case "ProcessGarbage":
                String[] wasteTokens = line.split(" ")[1].split("\\|");

                Waste currentWaste;
                ProcessingData currentProcessingData;

                currentWaste = this.wasteFactory.generateWaste(wasteTokens);

                currentProcessingData = this.garbageProcessor.processWaste(currentWaste);

                this.recyclingStation.updateBalance(currentProcessingData.getEnergyBalance(),
                        currentProcessingData.getCapitalBalance());

                return String.format("%.2f kg of %s successfully processed!",
                        currentWaste.getWeight(), currentWaste.getName());
            case "Status":
                return String.format("Energy: %.2f Capital: %.2f", this.recyclingStation.getEnergy(),
                        this.recyclingStation.getCapital());
            default:
                return null;
        }
    }
}
