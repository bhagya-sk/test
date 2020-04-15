package com.reactiveworks.stocktrade.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.StockTradeDaoFactory;
import com.reactiveworks.stocktrade.dao.exceptions.StockTradeDaoException;
import com.reactiveworks.stocktrade.model.StockTrade;
import com.reactiveworks.stocktrade.service.IStockTradeService;
import com.reactiveworks.stocktrade.service.exception.StockTrdServiceFailureException;

/**
 * Service class for StockTrade.
 */
@Component
public class StockTradeService implements IStockTradeService {

	private static final Logger LOGGER_OBJ = Logger.getLogger(StockTradeService.class);
	
	@Autowired
	private StockTradeDaoFactory stockTrdDao;
	
	/**
	 * Gets the list of stockTrade objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 * 
	 */
	public List<StockTrade> readStockTrades() throws StockTrdServiceFailureException {
		LOGGER_OBJ.debug("execution of readStockTrades() method started.");
		IStockTradeDao stockTradeDao=stockTrdDao.getObject();
		List<StockTrade> stockTradeList = null;
		try {
			stockTradeList=stockTradeDao.getStockTradeRecords();
		}  catch (StockTradeDaoException daoExp) {
			LOGGER_OBJ.error("unable to start the stockTrade application");
			throw new StockTrdServiceFailureException("unable to fetch data from the database", daoExp);
			
		} 

		LOGGER_OBJ.debug("execution of readStockTrades() completed");
		return stockTradeList;
	}

	/**
	 * Finds the stockTrade object which has max volume.
	 * 
	 * @param stockTradeObjList list of stockTrade objects.
	 * @return the stockTrade object with the max volume.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Override
	public StockTrade getMaxVolumeTrade() throws StockTrdServiceFailureException {
		LOGGER_OBJ.debug("execution of getMaxVolumeTrade() method started.");
		List<StockTrade> stockTradeList;
		StockTrade stockTradeObj = null;
		try {
			stockTradeList = readStockTrades();
			stockTradeObj = stockTradeList.stream()
					.max((stockTrdObj1, stockTrdObj2) -> (stockTrdObj1.getVolume() > stockTrdObj2.getVolume() ? 1
							: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? -1 : 0))
					.get();
		} catch (StockTrdServiceFailureException exp) {
			LOGGER_OBJ.error("unable to get the stockTrades from the database");
			throw new StockTrdServiceFailureException("unable to find the stockTrade object with max volume", exp);
		}

		LOGGER_OBJ.debug("execution of getMaxVolumeTrade() completed");
		return stockTradeObj;
	}

	/**
	 * Finds the stockTrade object which has min volume.
	 * 
	 * @param stockTradeObjList list of stockTrade objects.
	 * @return the stockTrade object which has min volume.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Override
	public StockTrade getMinVolumeTrade() throws StockTrdServiceFailureException {
		LOGGER_OBJ.debug("execution of getMinVolumeTrade() method started.");
		StockTrade stockTradeObj;
		List<StockTrade> stockTradeList;
		try {
			stockTradeList = readStockTrades();
			stockTradeObj = stockTradeList.stream()
					.max((stockTrdObj1, stockTrdObj2) -> (stockTrdObj1.getVolume() > stockTrdObj2.getVolume() ? -1
							: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? 1 : 0))
					.get();
		} catch (StockTrdServiceFailureException exp) {
			LOGGER_OBJ.error("unable to get the stockTrades from the database");
			throw new StockTrdServiceFailureException("unable to find the stockTrade object with min volume", exp);
		}

		LOGGER_OBJ.debug("execution of getMinVolumeTrade() completed");
		return stockTradeObj;
	}

	/**
	 * Computes the daily trading difference.
	 * 
	 * @param stockTradeObjList the list of stockTrade objects.
	 * @return the map which contains the difference of open vs High with Date being
	 *         the Key
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Override
	public Map<Date, Double> getDailyTradingDifferential() throws StockTrdServiceFailureException {
		LOGGER_OBJ.debug("execution of getDailyTradingDifferential() method started.");
		Map<Date, Double> dailyTradingDiff;

		try {
			List<StockTrade> stockTradeList = readStockTrades();
			dailyTradingDiff = stockTradeList.stream()
					.collect(Collectors.toMap(m -> m.getDate(), m -> (m.getHigh() - m.getLow())));
		} catch (StockTrdServiceFailureException exp) {
			LOGGER_OBJ.error("unable to get the stockTrades from the database");
			throw new StockTrdServiceFailureException("unable to get the dailyTradingDifferential", exp);
		}
		LOGGER_OBJ.debug("execution of getDailyTradingDifferential() completed");
		return dailyTradingDiff;
	}

}