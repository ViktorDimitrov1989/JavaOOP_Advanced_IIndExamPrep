package app.models.recylcingStation;

public interface Station {

    void updateBalance(double energy, double cpaital);

    double getEnergy();

    double getCapital();
}
