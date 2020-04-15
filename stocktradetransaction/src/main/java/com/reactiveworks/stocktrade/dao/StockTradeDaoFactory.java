package com.reactiveworks.stocktrade.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.reactiveworks.stocktrade.dao.impl.StockTradeDaoCsvImpl;
import com.reactiveworks.stocktrade.dao.impl.StockTradeDaoMysqlImpl;

/**
 * Factory for StockTradeDao.
 */
@Component
public class StockTradeDaoFactory implements FactoryBean<IStockTradeDao> {

	@Autowired
	private StockTradeDaoCsvImpl stockTrdDaoCsvImpl;
	@Autowired
	private StockTradeDaoMysqlImpl stockTrdDaoMysqlImpl;

	@Value("${dbType}")
	private String implType;
	private static final String CSV = "csv";
	private static final String MYSQL = "mysql";

	public StockTradeDaoCsvImpl getStockTrdDaoCsvImpl() {
		return stockTrdDaoCsvImpl;
	}

	public void setStockTrdDaoCsvImpl(StockTradeDaoCsvImpl stockTrdDaoCsvImpl) {
		this.stockTrdDaoCsvImpl = stockTrdDaoCsvImpl;
	}

	public StockTradeDaoMysqlImpl getStockTrdDaoMysqlImpl() {
		return stockTrdDaoMysqlImpl;
	}

	public void setStockTrdDaoMysqlImpl(StockTradeDaoMysqlImpl stockTrdDaoMysqlImpl) {
		this.stockTrdDaoMysqlImpl = stockTrdDaoMysqlImpl;
	}

	/**
	 * Returns the StockTradeDao implementation object .
	 * 
	 * @return IStockTradeDao the StockTradeDao implementation object.
	 */
	@Override
	public IStockTradeDao getObject() {

		if (implType.equalsIgnoreCase(CSV)) {
			return stockTrdDaoCsvImpl;
		} else if (implType.equalsIgnoreCase(MYSQL)) {
			return stockTrdDaoMysqlImpl;
		} else {
			return stockTrdDaoCsvImpl;
		}

	}

	@Override
	public Class<IStockTradeDao> getObjectType() {

		return null;
	}

}
