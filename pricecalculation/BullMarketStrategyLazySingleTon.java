package edu.neu.csye7374.pricecalculation;

import edu.neu.csye7374.interfaces.PriceCalculationStrategy;

import java.util.List;

public class BullMarketStrategyLazySingleTon implements PriceCalculationStrategy {

    private static BullMarketStrategyLazySingleTon instance = null;

    private BullMarketStrategyLazySingleTon() {

    }

    public static BullMarketStrategyLazySingleTon getInstance() {
        if(instance == null) {
            instance = new BullMarketStrategyLazySingleTon();
        }
        return instance;
    }

    @Override
    public double calculatePrice(List<Double> bid) {
        double avg = 0.0;
        for(double p : bid) {
            avg+= p;
        }
        avg = avg/bid.size() * 1.1;
        return avg;
    }
}
