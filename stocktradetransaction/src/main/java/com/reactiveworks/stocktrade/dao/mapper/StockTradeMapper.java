package com.reactiveworks.stocktrade.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * Mapper for StockTrade object.
 */
public class StockTradeMapper implements RowMapper<StockTrade> {
	/**
	 * Maps each row in database to the stockTrade object.
	 */
	@Override
	public StockTrade mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		StockTrade stockTradeObj = new StockTrade();
		stockTradeObj.setSecurity(resultSet.getString(1));
		stockTradeObj.setDate(resultSet.getDate(2));
		stockTradeObj.setOpen(resultSet.getDouble(3));
		stockTradeObj.setHigh(resultSet.getDouble(4));
		stockTradeObj.setLow(resultSet.getDouble(5));
		stockTradeObj.setClose(resultSet.getDouble(6));
		stockTradeObj.setVolume(resultSet.getDouble(7));
		stockTradeObj.setAdjClose(resultSet.getDouble(8));
		return stockTradeObj;
	}
}
