package edu.neu.csye7374.factory;

import edu.neu.csye7374.Stock;
import edu.neu.csye7374.interfaces.AbstractStockFactory;
import edu.neu.csye7374.stock.TeslaStock;

public class TeslaStockFactory implements AbstractStockFactory {

    @Override
    public Stock getObject() {
        return new TeslaStock();
    }
}
