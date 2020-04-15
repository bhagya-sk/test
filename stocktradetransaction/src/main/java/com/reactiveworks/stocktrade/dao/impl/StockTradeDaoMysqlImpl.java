package com.reactiveworks.stocktrade.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.reactiveworks.stocktrade.dao.IStockTradeDao;
import com.reactiveworks.stocktrade.dao.exceptions.StockTradeDaoException;
import com.reactiveworks.stocktrade.dao.mapper.StockTradeMapper;
import com.reactiveworks.stocktrade.model.StockTrade;

/**
 * MySql implementation of IStockTradeDao.
 */
@Component
//@Transactional
//@EnableTransactionManagement(proxyTargetClass = true) //unable to proxy interface-implementing method
public class StockTradeDaoMysqlImpl extends JdbcDaoSupport implements IStockTradeDao {

	private static final String INSERT_QUERY = "INSERT INTO stocktrade(security,date,open,high,low,close,volume,adj_close) VALUES(?,?,?,?,?,?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM stocktrade WHERE stockTrade_id=?;";
	private static final String UPDATE_QUERY = "UPDATE stocktrade SET volume=? WHERE stockTrade_id=?;";
	private static final String SELECT_QUERY = "SELECT security,date,open,high,low,close,volume,adj_close FROM stocktrade;";
	private static final Logger LOGGER_OBJ = Logger.getLogger(StockTradeDaoMysqlImpl.class);

	public StockTradeDaoMysqlImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * creates the list of stockTrade objects.
	 * 
	 * @return the list of stockTrade objects.
	 * @throws StockTradeDaoException  when dao operation fails.
	 * @throws DataBaseAccessException when unable to access the database.
	 */
	@Transactional(rollbackFor = StockTradeDaoException.class)
	@Override
	public List<StockTrade> getStockTradeRecords() throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of getStockTradeRecords() method started.");
		List<StockTrade> stockTrdList = null;
		try {
			stockTrdList = getJdbcTemplate().query(SELECT_QUERY, new StockTradeMapper());

		} catch (DataAccessException dataAccessExp) {
			LOGGER_OBJ.debug("unable to access the database");
			throw new StockTradeDaoException("unable to get the stocktrade record from the database ", dataAccessExp);
		}
		LOGGER_OBJ.debug("getStockTradeRecords() method execution completed.");
		return stockTrdList;
	}

	/**
	 * Creates the stockTrade record in the database.
	 * 
	 * @param stockTradeObj the stockTrade object to be inserted into the database.
	 * @throws StockTradeDaoException when unable to access the database.
	 */
	@Transactional(rollbackFor = StockTradeDaoException.class)
	@Override
	public void createStockTradeRecord(StockTrade stockTradeObj) throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of createStockTradeRecord() method started.");
		java.sql.Date date = new java.sql.Date(stockTradeObj.getDate().getTime());

		try {
			getJdbcTemplate().update(INSERT_QUERY,
					new Object[] { stockTradeObj.getSecurity(), date, stockTradeObj.getOpen(), stockTradeObj.getHigh(),
							stockTradeObj.getLow(), stockTradeObj.getClose(), stockTradeObj.getVolume(),
							stockTradeObj.getAdjClose() });
		} catch (DataAccessException dataAccessExp) {
			LOGGER_OBJ.debug("unable to access the database");
			throw new StockTradeDaoException("unable to create the stocktrade record ", dataAccessExp);

		}

		LOGGER_OBJ.debug("createStockTradeRecord() method execution completed.");
	}

	/**
	 * inserts multiple stockTrade records into the database.
	 * 
	 * @param stockTradesList the list of stockTrade records to be inserted into the
	 *                        database.
	 * @throws StockTradeDaoException when dao operation fails.
	 */
	@Transactional(rollbackFor = StockTradeDaoException.class)
	@Override
	public void createStockTradeRecords(List<StockTrade> stockTradesList) throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of createStockTradeRecords() method started.");
		try {
			getJdbcTemplate().batchUpdate(INSERT_QUERY, stockTradesList, 10,
					new ParameterizedPreparedStatementSetter<StockTrade>() {

						@Override
						public void setValues(PreparedStatement preparedStatement, StockTrade stockTrade) {
							try {
								preparedStatement.setString(1, stockTrade.getSecurity());
								java.sql.Date date = new java.sql.Date(stockTrade.getDate().getTime());
								preparedStatement.setDate(2, date);
								preparedStatement.setDouble(3, stockTrade.getOpen());
								preparedStatement.setDouble(4, stockTrade.getHigh());
								preparedStatement.setDouble(5, stockTrade.getLow());
								preparedStatement.setDouble(6, stockTrade.getClose());
								preparedStatement.setDouble(7, stockTrade.getVolume());
								preparedStatement.setDouble(8, stockTrade.getAdjClose());
							} catch (SQLException sqlExp) {
								LOGGER_OBJ.error("unable to insert stockTrade object into the database");
							}
						}
					});
		} catch (DataAccessException dataAccessExp) {
			LOGGER_OBJ.debug("unable to create the stocktrade record in the database ");
			throw new StockTradeDaoException("unable to create the stocktrade records in the database ", dataAccessExp);

		}

		LOGGER_OBJ.debug("createStockTradeRecords() method execution completed.");
	}

	/**
	 * deletes the stockTrade record from the database.
	 * 
	 * @param stockTradeId the id of the stockTrade record to be deleted from the
	 *                     database.
	 * @throws StockTradeDaoException when dao operation fails.
	 */
	@Transactional(rollbackFor = StockTradeDaoException.class)
	@Override
	public void deleteStockTradeRecord(int stockTradeId) throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of deleteStockTradeRecord() method started.");
		try {
			getJdbcTemplate().update(DELETE_QUERY, new Object[] { stockTradeId });
		} catch (DataAccessException dataAccessExp) {
			LOGGER_OBJ.debug("unable to access the database");
			throw new StockTradeDaoException("unable to delete the stocktrade record with id= " + stockTradeId,
					dataAccessExp);
		}

		LOGGER_OBJ.debug("deleteStockTradeRecord() method execution completed.");
	}

	/**
	 * Updates the stockTrade record in the database.
	 * 
	 * @param stockTrdId the id of the stockTrade record to be updated .
	 * @param volume     the new updated value of the stockTrade volume.
	 * @throws StockTradeDaoException when dao operation fails.
	 */
	@Transactional(rollbackFor = StockTradeDaoException.class)
	@Override
	public void updateStockTradeRecord(int stockTrdId, double volume) throws StockTradeDaoException {
		LOGGER_OBJ.debug("execution of updateStockTradeRecord() method started.");
		try {
			getJdbcTemplate().update(UPDATE_QUERY, new Object[] { volume, stockTrdId });
		} catch (DataAccessException dataAccessExp) {
			LOGGER_OBJ.debug("unable to access the database");
			throw new StockTradeDaoException("unable to update the stocktrade record with id= " + stockTrdId,
					dataAccessExp);
		}

		LOGGER_OBJ.debug("updateStockTradeRecord() method execution completed.");

	}

}