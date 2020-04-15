package com.reactiveworks.stocktrade.dao.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.exceptions.DaoOperationNotSupportedException;
import com.reactiveworks.stocktrade.dao.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.dao.exceptions.StockTradeDaoException;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * mysql implementation of IStockTradeDao .
 */
@Component
public class StockTradeDaoCsvImpl implements IStockTradeDao {
	private static final Logger LOGGER_OBJ = Logger.getLogger(StockTradeDaoCsvImpl.class);
	private static final String FILE_NAME = "CISCO.txt";

	/**
	 * creates the list of stockTrade objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws StockTradeDaoException when unable to access the database.
	 */
	@Override
	public List<StockTrade> getStockTradeRecords() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of getStockTradeRecords() method started ");
		File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());

		List<StockTrade> stockTrdList = null;

		try (Stream<String> line = Files.lines(Paths.get(file.toURI()))) {

			stockTrdList = line.skip(1).map(strLine -> {
				try {

					return parseStockCSVLine(strLine);

				} catch (InvalidDBRecordFormatException invalidStockTrdExp) {
					LOGGER_OBJ.error("format of the stocktrade in " + strLine + " is invalid" + invalidStockTrdExp);
				}
				return null;

			}).collect(Collectors.toList());

		} catch (IOException ioExp) {

			throw new StockTradeDaoException("unable to access the DB " + FILE_NAME + ioExp);
		}
		LOGGER_OBJ.debug("execution of getStockTradeRecords() method completed ");
		return stockTrdList;
	}

	/**
	 * converts one line in csv file to the stockTrade object.
	 * 
	 * @param strLine string in each line of the csv file.
	 * @return the stockTrade object.
	 * @throws InvalidDBRecordFormatException when format of the db record is
	 *                                        invalid.
	 */
	private StockTrade parseStockCSVLine(String strLine) throws InvalidDBRecordFormatException {
		LOGGER_OBJ.debug("execution of parseStockCSVLine() method started ");
		StockTrade stockTradeObj = new StockTrade();
		String stockInfo[] = strLine.split(",");
		stockTradeObj.setSecurity(stockInfo[0]);
		Date date;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(stockInfo[1]);
			stockTradeObj.setDate(date);
			stockTradeObj.setOpen(Double.parseDouble(stockInfo[2]));
			stockTradeObj.setHigh(Double.parseDouble(stockInfo[3]));
			stockTradeObj.setLow(Double.parseDouble(stockInfo[4]));
			stockTradeObj.setClose(Double.parseDouble(stockInfo[5]));
			stockTradeObj.setVolume(Double.parseDouble(stockInfo[6]));
			stockTradeObj.setAdjClose(Double.parseDouble(stockInfo[7]));
		} catch (NumberFormatException numformatexp) {
			LOGGER_OBJ.error("number format of the stocktrade field in " + strLine + " is invalid.");
			throw new InvalidDBRecordFormatException("invalid number format.", numformatexp);
		} catch (ParseException parseExp) {
			LOGGER_OBJ.error("date format of the stocktrade field in " + strLine + " is invalid.");
			throw new InvalidDBRecordFormatException("invalid date format.", parseExp);
		}
		LOGGER_OBJ.debug("execution of parseStockCSVLine() method started ");
		return stockTradeObj;

	}

	/**
	 * Creates the stockTrade record in the database.
	 * 
	 * @param stockTradeObj the stockTrade object to be inserted into the database.
	 * 
	 * @throws DaoOperationNotSupportedException when operation is not supported by
	 *                                           the database.
	 */
	@Override
	public void createStockTradeRecord(StockTrade stockTradeObj) throws DaoOperationNotSupportedException {
		throw new DaoOperationNotSupportedException("creating a record in the database is not supported.");

	}

	/**
	 * inserts multiple stockTrade records into the database.
	 * 
	 * @param stockTradesList the list of stockTrade records to be inserted into the
	 *                        database.
	 * @throws DaoOperationNotSupportedException when operation is not supported by
	 *                                           the database.
	 */
	@Override
	public void createStockTradeRecords(List<StockTrade> stockTradesList) throws DaoOperationNotSupportedException {

		throw new DaoOperationNotSupportedException("creating a records in the database is not supported.");
	}

	/**
	 * deletes the stockTrade record from the database.
	 * 
	 * @param stockTradeId the id of the stockTrade record to be deleted from the
	 *                     database.
	 * @throws DaoOperationNotSupportedException when operation is not supported by
	 *                                           the database.
	 */
	@Override
	public void deleteStockTradeRecord(int stockTradeId) throws DaoOperationNotSupportedException {

		throw new DaoOperationNotSupportedException("deleting a record from the database is not supported.");
	}

	/**
	 * Updates the stockTrade record in the database.
	 * 
	 * @param stockTrdId the id of the stockTrade record to be updated .
	 * @param volume     the new updated value of the stockTrade volume.
	 * 
	 * @throws DaoOperationNotSupportedException when operation is not supported by
	 *                                           the database.
	 */
	@Override
	public void updateStockTradeRecord(int stockTrdId, double volume) throws DaoOperationNotSupportedException {
		throw new DaoOperationNotSupportedException("updating a record in the database is not supported.");
	}

}
