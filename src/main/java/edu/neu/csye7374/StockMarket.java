package edu.neu.csye7374;


import java.util.ArrayList;
import java.util.List;

import edu.neu.csye7374.factory.NikeStockFactory;
import edu.neu.csye7374.factory.TeslaStockFactory;
import edu.neu.csye7374.interfaces.PriceCalculationStrategy;
import edu.neu.csye7374.interfaces.Tradable;
import edu.neu.csye7374.pricecalculation.BearMarketStrategy;
import edu.neu.csye7374.pricecalculation.BullMarketStrategyLazySingleTon;

public class StockMarket {

    private static StockMarket instance;
    private List<Tradable> stocks;

    private StockMarket() {
        stocks = new ArrayList<>();
    }

    public static StockMarket getInstance() {
        if (instance == null) {
            synchronized (StockMarket.class) {
                if (instance == null) {
                    instance = new StockMarket();
                }
            }
        }
        return instance;
    }

    public void addStock(Tradable stock) {
        stocks.add(stock);
    }

    public void showAllStocks() {
        for (Tradable stock : stocks) {
            System.out.println(stock);
        }
    }

    public void tradeStocks(String name, double bid) {
        for (Tradable stock : stocks) {
            if(stock.getID().equals(name))
                stock.setBid(bid);
        }
    }

    public void tradeStocks(String name, double bid, PriceCalculationStrategy priceCalculationStrategy) {
        for (Tradable stock : stocks) {
            if(stock.getID().equals(name))
                stock.tradeStockUsingStrategy(priceCalculationStrategy, bid);
        }
    }

    public void removeStock(String name) {
        int index = -1;
        for (int i=0;i < stocks.size();i++) {
            if(stocks.get(i).getID().equals(name))
                index = i;
        }
        if(index!=-1)
            stocks.remove(index);
    }

    public void startTrading() {

        System.out.println("Trading Started");

        BearMarketStrategy bearMarketStrategy = new BearMarketStrategy();

        BullMarketStrategyLazySingleTon bullMarketStrategyLazySingleTon = BullMarketStrategyLazySingleTon.getInstance();

        Stock tesla = new TeslaStockFactory().getObject();
        tesla.setPrice(20);
        tesla.setID("TESLA");
        tesla.setDescription("Tesla");

        this.addStock(tesla);

        Stock nike = new NikeStockFactory().getObject();
        nike.setPrice(20);
        nike.setID("NIKE");
        nike.setDescription("Nike");

        this.addStock(nike);

        System.out.println("All  the stocks avaialble in the stock market at the start");
        showAllStocks();

        tradeStocks("TESLA", 23, bearMarketStrategy);
        tradeStocks("NIKE", 24, bullMarketStrategyLazySingleTon);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid one");
        showAllStocks();
        tradeStocks("TESLA", 26);
        tradeStocks("NIKE", 21);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid two");
        showAllStocks();
        tradeStocks("TESLA", 28);
        tradeStocks("NIKE", 18);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid three");
        showAllStocks();
        tradeStocks("TESLA", 23);
        tradeStocks("NIKE", 15);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid four");
        showAllStocks();
        tradeStocks("TESLA", 22,bullMarketStrategyLazySingleTon);
        tradeStocks("NIKE", 20,bearMarketStrategy);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid five");
        showAllStocks();
        tradeStocks("TESLA", 25);
        tradeStocks("NIKE", 23);
        System.out.println("Current status of the Stocks in the stock market after the competion of bid six");
        showAllStocks();
        showAllStocks();

        removeStock("TESLA");
        System.out.println("Stocks avaialble in the stock market after removing a stock");
        showAllStocks();
    }


}
