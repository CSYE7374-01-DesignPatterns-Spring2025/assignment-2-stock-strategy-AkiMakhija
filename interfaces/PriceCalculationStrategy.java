package edu.neu.csye7374.interfaces;

import java.util.List;

public interface PriceCalculationStrategy {

    double calculatePrice(List<Double> bid);
}
