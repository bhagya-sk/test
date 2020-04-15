package com.reactiveworks.stocktrade.dao;

import java.util.List;

import com.reactiveworks.stocktrade.dao.exceptions.DaoOperationNotSupportedException;
import com.reactiveworks.stocktrade.dao.exceptions.StockTradeDaoException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * Interface for StockTradeDao implementation.
 */
public interface IStockTradeDao {

	/**
	 * creates the list of stockTrade objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws StockTradeDaoException when unable to perform dao operations.
	 */
	public List<StockTrade> getStockTradeRecords() throws StockTradeDaoException;

	/**
	 * Creates the stockTrade record in the database.
	 * 
	 * @param stockTradeObj the stockTrade object to be inserted into the database.
	 * 
	 * 
	 */
	public void createStockTradeRecord(StockTrade stockTradeObj)
			throws StockTradeDaoException, DaoOperationNotSupportedException;

	/**
	 * inserts multiple stockTrade records into the database.
	 * 
	 * @param stockTradesList the list of stockTrade records to be inserted into the
	 *                        database.
	 * 
	 */
	public void createStockTradeRecords(List<StockTrade> stockTradesList)
			throws StockTradeDaoException, DaoOperationNotSupportedException;

	/**
	 * deletes the stockTrade record from the database.
	 * 
	 * @param stockTradeId the id of the stockTrade record to be deleted from the
	 *                     database.
	 */
	public void deleteStockTradeRecord(int stockTradeId)
			throws StockTradeDaoException, DaoOperationNotSupportedException;

	/**
	 * Updates the stockTrade record in the database.
	 * 
	 * @param stockTrdId the id of the stockTrade record to be updated .
	 * @param volume     the new updated value of the stockTrade volume.
	 * 
	 */
	public void updateStockTradeRecord(int stockTrdId, double volume)
			throws StockTradeDaoException, DaoOperationNotSupportedException;
}