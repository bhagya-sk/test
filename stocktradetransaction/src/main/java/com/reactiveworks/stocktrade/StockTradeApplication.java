package com.reactiveworks.stocktrade;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.reactiveworks.stocktrade.exceptions.StockTradeApplicationStartUpFailureException;
import com.reactiveworks.stocktrade.spring.configuration.StockTradeConfiguration;

/**
 * spring util class for the application.
 */
public class StockTradeApplication {

	private static final Logger LOGGER_OBJ = Logger.getLogger(StockTradeApplication.class);
	private static ApplicationContext context;

	/**
	 * Starts the application by creating the ApplicationContext.
	 * 
	 * @return the applicationContext object.
	 * @throws StockTradeApplicationStartUpFailureException when unable to create
	 *                                                      the application context.
	 */
	public static ApplicationContext startApplication() throws StockTradeApplicationStartUpFailureException {

		try {
			if (context == null) {
				context = new AnnotationConfigApplicationContext(StockTradeConfiguration.class);
			}

		} catch (BeansException beansExp) {
			LOGGER_OBJ.debug("unable to start the application");
			throw new StockTradeApplicationStartUpFailureException("Context creation failed", beansExp);
		}

		return context;
	}

	/**
	 * Stops the Application by closing the applicationContext.
	 */
	public static void stopApplication() {
		if (context != null) {
			((AnnotationConfigApplicationContext) (context)).close();
		}

	}

}