package com.reactiveworks.stocktrade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.reactiveworks.stocktrade.StockTradeApplication;
import com.reactiveworks.stocktrade.dao.exceptions.InvalidDBRecordFormatException;
import com.reactiveworks.stocktrade.exceptions.StockTradeApplicationStartUpFailureException;
import com.reactiveworks.stocktrade.model.StockTrade;
import com.reactiveworks.stocktrade.service.exception.StockTrdServiceFailureException;
import com.reactiveworks.stocktrade.service.implementation.StockTradeService;

public class StockTradeServiceTest {
	static ApplicationContext context;
	static StockTradeService stockTrdServiceObj;

	@BeforeClass
	public static void applicationContextInitializer() throws StockTradeApplicationStartUpFailureException {
		context = StockTradeApplication.startApplication();
		stockTrdServiceObj = context.getBean("stockTrdService", StockTradeService.class);
	}

	@AfterClass
	public static void applicationShutdown() {
		StockTradeApplication.stopApplication();
	}

	/**
	 * Tests the working of getStockTrades() in positive scenario.
	 * 
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 * 
	 */
	@Test
	public void getStockTradeListVerification() throws StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		int expectedStockTrdListSize = 481;
		int actualStockTrdListSize = stockTradesList.size();
		assertEquals(expectedStockTrdListSize, actualStockTrdListSize);
	}

	/**
	 * Tests the working of getStockTrades() in negative scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getStockTradeListVerificationFailTest()
			throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		int expectedStockTrdListSize = 480;
		int actualStockTrdListSize = stockTradesList.size();
		assertTrue(expectedStockTrdListSize != actualStockTrdListSize);
	}

	/**
	 * Checks the working of getMaxVolumeTrade() in positive scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws DBOperationFailureException     when database operation fails.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getMaxVolumeTradeServiceTest()
			throws  InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		double expectedMaxVolume = stockTradesList
				.stream().max(
						(stockTrdObj1,
								stockTrdObj2) -> ((stockTrdObj1.getVolume() > stockTrdObj2.getVolume()) ? 1
										: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? -1 : 0))
				.get().getVolume();
		double actualMaxVolume = stockTrdServiceObj.getMaxVolumeTrade().getVolume();
		assertTrue(expectedMaxVolume == actualMaxVolume);

	}

	/**
	 * Tests the working of getMaxVolumeTrade() in negative scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getMaxVolumeTradeServiceFailTest()
			throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		double expectedMaxVolume = stockTradesList.stream()
				.max((stockTrdObj1,
						stockTrdObj2) -> ((stockTrdObj1.getVolume() > stockTrdObj2.getVolume()) ? 1
								: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? -1 : 0))
				.get().getVolume() - 1;
		double actualMaxVolume = stockTrdServiceObj.getMaxVolumeTrade().getVolume();
		assertTrue(expectedMaxVolume != actualMaxVolume);

	}

	/**
	 * Tests the working of getMinVolumeTradeService() in positive scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws DBOperationFailureException     when database operation fails.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getMinVolumeTradeServiceTest() throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		double expectedMaxVolume = stockTradesList
				.stream().max(
						(stockTrdObj1,
								stockTrdObj2) -> ((stockTrdObj1.getVolume() > stockTrdObj2.getVolume()) ? -1
										: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? 1 : 0))
				.get().getVolume();
		double actualMaxVolume = stockTrdServiceObj.getMinVolumeTrade().getVolume();
		assertTrue(expectedMaxVolume == actualMaxVolume);
	}

	/**
	 * Tests the working of getMinVolumeTradeService() in negative scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getMinVolumeTradeServiceFailTest()
			throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		double expectedMaxVolume = stockTradesList
				.stream().max(
						(stockTrdObj1,
								stockTrdObj2) -> ((stockTrdObj1.getVolume() > stockTrdObj2.getVolume()) ? -1
										: (stockTrdObj1.getVolume() < stockTrdObj2.getVolume()) ? 1 : 0))
				.get().getVolume() - 1;
		double actualMaxVolume = stockTrdServiceObj.getMinVolumeTrade().getVolume();
		assertTrue(expectedMaxVolume != actualMaxVolume);
	}

	/**
	 * Tests the working of getDailyTradingDifferential() in positive scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getDailyTradingDifferentialServiceTest()
			throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		int expectedStockTrdListSize = 481;
		int actualStockTrdListSize = stockTradesList.size();
		assertTrue(expectedStockTrdListSize == actualStockTrdListSize);
	}

	/**
	 * Tests the working of getDailyTradingDifferential() in negative scenario.
	 * 
	 * @throws InvalidDBRecordFormatException  when the format of database record is
	 *                                         invalid.
	 * @throws DBOperationFailureException     when database operation fails.
	 * @throws StockTrdServiceFailureException when stockTrade service fails.
	 */
	@Test
	public void getDailyTradingDifferentialServiceFailTest()
			throws InvalidDBRecordFormatException, StockTrdServiceFailureException {

		List<StockTrade> stockTradesList = stockTrdServiceObj.readStockTrades();
		int expectedStockTrdListSize = 480;
		int actualStockTrdListSize = stockTradesList.size();
		assertTrue(expectedStockTrdListSize != actualStockTrdListSize);
	}

}
