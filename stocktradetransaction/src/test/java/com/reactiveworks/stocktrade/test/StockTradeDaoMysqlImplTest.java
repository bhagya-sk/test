package com.reactiveworks.stocktrade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import com.reactiveworks.stocktrade.StockTradeApplication;
import com.reactiveworks.stocktrade.dao.exceptions.StockTradeDaoException;
import com.reactiveworks.stocktrade.dao.impl.StockTradeDaoMysqlImpl;
import com.reactiveworks.stocktrade.exceptions.StockTradeApplicationStartUpFailureException;
import com.reactiveworks.stocktrade.model.StockTrade;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockTradeDaoMysqlImplTest {
	private static final Logger LOGGER_OBJ = Logger.getLogger(StockTradeDaoMysqlImplTest.class);
	private static StockTradeDaoMysqlImpl stockTrdDaoMysqlImpl;
	private static List<StockTrade> stockTradesList;

	/**
	 * Starts the stockTrade application.
	 * 
	 * @throws StockTradeApplicationStartUpFailureException when unable to start the
	 *                                                      application.
	 * @throws StockTradeDaoException                       when unable to access
	 *                                                      the database.
	 */
	@BeforeClass
	public static void startApplication() throws StockTradeApplicationStartUpFailureException, StockTradeDaoException {
		LOGGER_OBJ.debug("execution of startApplication() started");
		ApplicationContext context = StockTradeApplication.startApplication();
		stockTrdDaoMysqlImpl = context.getBean("stockTradeDaoMysqlImpl", StockTradeDaoMysqlImpl.class);
		stockTradesList = stockTrdDaoMysqlImpl.getStockTradeRecords();
		LOGGER_OBJ.debug("execution of startApplication() completed");
	}

	/**
	 * ShutsDown the stockTrade application.
	 */
	@AfterClass
	public static void applicationShutdown() {
		LOGGER_OBJ.debug("execution of applicationShutdown() started");
		StockTradeApplication.stopApplication();
		LOGGER_OBJ.debug("execution of applicationShutdown() started");
	}

	/**
	 * Tests working of getStockTradeRecords() in positive scenario.
	 * 
	 * @throws StockTradeDaoException when unable to access the database.
	 */
	public void stockTradeRecordsVerificationTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeRecordsVerificationTest() started");
		int expectedStockTrdListSize = 481;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertEquals(expectedStockTrdListSize, actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeRecordsVerificationTest() started");
	}

	/**
	 * Tests the working of getStockTradeRecords() in negative scenario.
	 * 
	 * @throws StockTradeDaoException when unable to access the database.
	 */
	public void stockTradeRecordsVerificationFailTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeRecordsVerificationFailTest() started");
		int expectedStockTrdListSize = 480;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertTrue(expectedStockTrdListSize != actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeRecordsVerificationFailTest() started");
	}

	/**
	 * tests the working of updateStockTradeRecord() in positive scenario.
	 * 
	 * @throws StockTradeDaoException when unable to access the database.
	 */
	public void stockTradeUpdationVerification() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeUpdationVerification() started");
		stockTrdDaoMysqlImpl.updateStockTradeRecord(1, 21.0);
		double expectedStockTrdVolume = 21.0;
		double actualStockTrdVolume = stockTrdDaoMysqlImpl.getStockTradeRecords().get(0).getVolume();
		assertTrue(expectedStockTrdVolume == actualStockTrdVolume);
		LOGGER_OBJ.debug("execution of stockTradeUpdationVerification() started");
	}

	/**
	 * tests the working of updateStockTradeRecord() in negative scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	public void stockTradeUpdationVerificationFailTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeUpdationFailVerification() started");
		stockTrdDaoMysqlImpl.updateStockTradeRecord(1, 21.0);
		double expectedStockTrdVolume = 20.0;
		double actualStockTrdVolume = stockTrdDaoMysqlImpl.getStockTradeRecords().get(0).getVolume();
		assertTrue(expectedStockTrdVolume != actualStockTrdVolume);
		LOGGER_OBJ.debug("execution of stockTradeUpdationFailVerification() started");
	}

	/**
	 * Tests the working of createStockTradeRecord() in positive scenario.
	 * 
	 * @throws StockTradeDaoException when dao operation fails.
	 */
	public void stockTradeRecordCreationVerification() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeRecordCreationVerification() started");
		StockTrade stockTradeObj = stockTradesList.get(0);
		stockTrdDaoMysqlImpl.createStockTradeRecord(stockTradeObj);
		int expectedStockTrdListSize = 482;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertEquals(expectedStockTrdListSize, actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeRecordCreationVerification() started");
	}

	/**
	 * Tests the working of createStockTradeRecord() in negative scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	public void stockTradeRecordCreationVerificationFailTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeRecordCreationFailVerification() started");
		StockTrade stockTradeObj = stockTradesList.get(0);
		stockTrdDaoMysqlImpl.createStockTradeRecord(stockTradeObj);
		int expectedStockTrdListSize = 482;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertTrue(expectedStockTrdListSize != actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeRecordCreationFailVerification() started");
	}

	/**
	 * Tests the working of createStockTradeRecords() in positive scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	public void stockTradesRecordCreationVerification() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradesRecordCreationVerification() started");
		int expectedStockTrdListSize = 493;
		List<StockTrade> insertionList = stockTradesList.subList(0, 10);
		stockTrdDaoMysqlImpl.createStockTradeRecords(insertionList);
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertEquals(expectedStockTrdListSize, actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradesRecordCreationVerification() started");

	}

	/**
	 * Tests the working of deleteStockTradeRecord() in positive scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	public void stockTradeDeletionVerification() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeDeletionVerification() started");
		stockTrdDaoMysqlImpl.deleteStockTradeRecord(1);
		int expectedStockTrdListSize = 492;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertEquals(expectedStockTrdListSize, actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeDeletionVerification() started");
	}

	/**
	 * Tests the working of deleteStockTradeRecord() in negative scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	public void stockTradeDeletionVerificationFailTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradeDeletionFailVerification() started");
		stockTrdDaoMysqlImpl.deleteStockTradeRecord(1);
		int expectedStockTrdListSize = 490;
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertTrue(expectedStockTrdListSize != actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradeDeletionFailVerification() started");
	}

	/**
	 * Tests the working of createStockTradeRecords() in positive scenario.
	 * 
	 * @throws DataBaseAccessException when unable to access the database.
	 * @throws StockTradeDaoException  when dao operation fails.
	 */
	@Test(expected = StockTradeDaoException.class)
	public void stockTradesRecordCreationFailTest() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of stockTradesRecordCreationFailVerification() started");
		int expectedStockTrdListSize = 481;
		List<StockTrade> insertionList = stockTradesList.subList(0, 13);
		insertionList.get(12).setSecurity("qwertyuiopasdfghjklzxcv");
		stockTrdDaoMysqlImpl.createStockTradeRecords(insertionList);
		int actualStockTrdListSize = stockTrdDaoMysqlImpl.getStockTradeRecords().size();
		assertTrue(expectedStockTrdListSize == actualStockTrdListSize);
		LOGGER_OBJ.debug("execution of stockTradesRecordCreationFailVerification() started");
	}

	@Test
	public void testStockTradesDaoMethods() throws StockTradeDaoException {
		stockTradeRecordsVerificationTest();
		stockTradeRecordsVerificationFailTest();
		stockTradeUpdationVerification();
		stockTradeUpdationVerificationFailTest();
		stockTradeRecordCreationVerification();
		stockTradeRecordCreationVerificationFailTest();
		stockTradesRecordCreationVerification();
		stockTradeDeletionVerification();
		stockTradeDeletionVerificationFailTest();
	}
}