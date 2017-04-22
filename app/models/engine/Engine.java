package app.models.engine;

import app.models.factories.StrategyFactory;
import app.models.factories.WasteFactory;
import app.models.interpreters.CommandInterpreter;
import app.models.interpreters.Interpreter;
import app.models.io.ConsoleReader;
import app.models.io.ConsoleWriter;
import app.models.io.Reader;
import app.models.io.Writer;
import app.models.recylcingStation.RecyclingStation;
import app.models.recylcingStation.Station;
import app.waste_disposal.DefaultGarbageProcessor;
import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.contracts.GarbageProcessor;
import app.waste_disposal.contracts.StrategyHolder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
    private Reader reader;
    private Writer writer;
    private StrategyHolder strategyHolder;
    private StrategyFactory strategyFactory;
    private WasteFactory wasteFactory;
    private GarbageProcessor garbageProcessor;
    private Interpreter commandInterpreter;
    private Station recyclingStation;

    public Engine() throws ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        this.initializeDependencies();

    }

    private void initializeDependencies() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.strategyHolder = new DefaultStrategyHolder();
        this.strategyFactory = new StrategyFactory(this.strategyHolder);
        this.wasteFactory = new WasteFactory();
        this.recyclingStation = new RecyclingStation();
        this.strategyFactory.initializeFactories();
        this.garbageProcessor = new DefaultGarbageProcessor(this.strategyHolder);
        this.commandInterpreter = new CommandInterpreter(this.wasteFactory, this.garbageProcessor, this.recyclingStation);
    }

    @Override
    public void run() {

        String line;
        try {
            while (!"TimeToRecycle".equals(line = reader.read())){
                writer.writeLine(this.commandInterpreter.interpretCommand(line));
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
