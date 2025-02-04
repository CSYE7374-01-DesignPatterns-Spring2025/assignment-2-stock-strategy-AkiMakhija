package edu.neu.csye7374.pricecalculation;

import edu.neu.csye7374.interfaces.PriceCalculationStrategy;

import java.util.List;

public class BearMarketStrategyEarlySingleTon implements PriceCalculationStrategy {

    private static BearMarketStrategyEarlySingleTon instance = new BearMarketStrategyEarlySingleTon();

    private BearMarketStrategyEarlySingleTon() {

    }

    public static BearMarketStrategyEarlySingleTon getInstance() {
        return instance;
    }

    @Override
    public double calculatePrice(List<Double> bid) {
        double avg = 0.0;
        for(double p : bid) {
            avg+= p;
        }
        avg = avg/bid.size() * 0.9;
        return avg;
    }
}
