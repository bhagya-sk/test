package com.reactiveworks.stocktrade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.reactiveworks.stocktrade.model.StockTrade;
import com.reactiveworks.stocktrade.service.exception.StockTrdServiceFailureException;

public interface IStockTradeService {

	/**
	 * Gets the list of stockTrade objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	public List<StockTrade> readStockTrades() throws StockTrdServiceFailureException;

	/**
	 * Finds the stockTrade object which has max volume.
	 * 
	 * @param stockTradeObjList list of stockTrade objects.
	 * @return the stockTrade object with the max volume.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	public StockTrade getMaxVolumeTrade() throws StockTrdServiceFailureException;

	/**
	 * Finds the stockTrade object which has min volume.
	 * 
	 * @param stockTradeObjList list of stockTrade objects.
	 * @return the stockTrade object which has min volume.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	public StockTrade getMinVolumeTrade() throws StockTrdServiceFailureException;

	/**
	 * Computes the daily trading difference.
	 * 
	 * @param stockTradeObjList the list of stockTrade objects.
	 * @return the map which contains the difference of open vs High with Date being
	 *         the Key
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	public Map<Date, Double> getDailyTradingDifferential() throws StockTrdServiceFailureException;

}