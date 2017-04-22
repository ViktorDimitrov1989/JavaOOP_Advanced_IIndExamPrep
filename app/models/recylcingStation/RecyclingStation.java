package app.models.recylcingStation;

public class RecyclingStation implements Station {
    private double energyBalance;
    private double capitalBalance;


    public RecyclingStation() {
        this.energyBalance = 0;
        this.capitalBalance = 0;
    }


    @Override
    public void updateBalance(double energy, double capital) {
        this.energyBalance += energy;
        this.capitalBalance += capital;
    }

    @Override
    public double getEnergy() {
        return this.energyBalance;
    }

    @Override
    public double getCapital() {
        return this.capitalBalance;
    }
}
