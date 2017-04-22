package tests;

import app.models.strategies.BurnableDisposalStrategy;
import app.models.strategies.RecyclableDisposalStrategy;
import app.models.strategies.StorableDisposalStrategy;
import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.contracts.StrategyHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tests.fakes.DisposableAnnotation;
import tests.fakes.NonDisposableAnnotation;

public class StrategyHolderShould {
    private StrategyHolder strategyHolder;

    private BurnableDisposalStrategy burnableDisposalStrategyMock;
    private RecyclableDisposalStrategy recyclableDisposalStrategyMock;
    private StorableDisposalStrategy storableDisposalStrategyMock;

    @Before
    public void init(){
        this.strategyHolder = new DefaultStrategyHolder();

        this.burnableDisposalStrategyMock = Mockito.mock(BurnableDisposalStrategy.class);
        this.recyclableDisposalStrategyMock = Mockito.mock(RecyclableDisposalStrategy.class);
        this.storableDisposalStrategyMock = Mockito.mock(StorableDisposalStrategy.class);

    }


    @Test
    public void haveEmptyUnmodifiableHashMapOnInitialization(){
        Assert.assertTrue(this.strategyHolder.getDisposalStrategies().size() == 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void haveUnmodifiableCollection(){
        this.strategyHolder.getDisposalStrategies()
                .put(this.burnableDisposalStrategyMock.getClass(), this.burnableDisposalStrategyMock);
    }

    @Test
    public void returnExpectedStrategyWhenTryingToGetIt(){
        this.strategyHolder.addStrategy(this.recyclableDisposalStrategyMock.getClass(), this.recyclableDisposalStrategyMock);

        Assert.assertEquals(this.recyclableDisposalStrategyMock,
                this.strategyHolder.getDisposalStrategies()
                        .get(this.recyclableDisposalStrategyMock.getClass()));
    }

    @Test
    public void returnFalseOnTryToAddAlreadyExistingStrategy(){
        this.strategyHolder.addStrategy(this.recyclableDisposalStrategyMock.getClass(), this.recyclableDisposalStrategyMock);

        Assert.assertEquals(false,
                this.strategyHolder.addStrategy(this.recyclableDisposalStrategyMock.getClass(), this.recyclableDisposalStrategyMock));
    }

    @Test
    public void returnFalseWhenTryToRemoveNotPresentedStrategy(){
        Assert.assertEquals(false,
                this.strategyHolder.removeStrategy(this.recyclableDisposalStrategyMock.getClass()));
    }

    @Test
    public void returnTrueWhenAddStrategy(){
        Assert.assertEquals(true,
                this.strategyHolder.addStrategy(this.recyclableDisposalStrategyMock.getClass(), this.recyclableDisposalStrategyMock));
    }

    @Test
    public void returnTrueWhenRemoveStrategy(){
        this.strategyHolder.addStrategy(this.recyclableDisposalStrategyMock.getClass(), this.recyclableDisposalStrategyMock);

        Assert.assertEquals(true,
                this.strategyHolder.removeStrategy(this.recyclableDisposalStrategyMock.getClass()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfAddNonDisposableAnnotatedClass(){
        this.strategyHolder.addStrategy(NonDisposableAnnotation.class, this.recyclableDisposalStrategyMock);
    }


}
