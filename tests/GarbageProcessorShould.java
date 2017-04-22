package tests;

import app.models.strategies.RecyclableDisposalStrategy;
import app.models.wastes.RecyclableGarbage;
import app.waste_disposal.DefaultGarbageProcessor;
import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.annotations.Recyclable;
import app.waste_disposal.contracts.GarbageProcessor;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.StrategyHolder;
import app.waste_disposal.contracts.Waste;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tests.fakes.NonDisposableWaste;
import tests.fakes.NonStrategyWaste;

public class GarbageProcessorShould {
    private GarbageProcessor garbageProcessor;
    private StrategyHolder strategyHolder;

    private ProcessingData processingDataMock;
    private Waste garbageMock;
    private Waste nondisposableWasteMock;
    private Waste nonStrategyWasteMock;


    @Before
    public void init(){
        this.strategyHolder = new DefaultStrategyHolder();
        this.strategyHolder.addStrategy(Recyclable.class, new RecyclableDisposalStrategy());

        this.garbageProcessor = new DefaultGarbageProcessor(this.strategyHolder);

        this.garbageMock = Mockito.mock(RecyclableGarbage.class);
        this.nondisposableWasteMock = Mockito.mock(NonDisposableWaste.class);
        this.nonStrategyWasteMock = Mockito.mock(NonStrategyWaste.class);
    }


    @Test
    public void haveSameStrategyHolderIfPresent(){
        Assert.assertEquals(this.garbageProcessor.getStrategyHolder(), this.strategyHolder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfStrategyHolderPassedIsNull(){
        this.garbageProcessor = new DefaultGarbageProcessor(null);
    }

    @Test
    public void processWasteMethodShouldReturnProcessingDataObject(){
        this.processingDataMock = this.garbageProcessor.processWaste(this.garbageMock);

        Assert.assertTrue(this.processingDataMock != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void processWasteThrowExceptionWhenProcessingNotAnnotatedWaste(){
        this.garbageProcessor.processWaste(this.nondisposableWasteMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void processWasteThrowExceptionWhenProcessingWasteWithoutSupportedStrategyInStrategyHolder(){
        this.garbageProcessor.processWaste(this.nonStrategyWasteMock);
    }

}
