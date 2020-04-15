package com.reactiveworks.stocktrade.model;

import java.util.Comparator;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This class represents StockTrade.
 */
@Component
public class StockTrade implements Comparator<StockTrade>{
	String security;
	Date date;
	Double open;
	Double high;
	Double low;
	Double close;
	Double volume;
	Double adjClose;

	/**
	 * Gets the value of security.
	 * 
	 * @return the current value of the security.
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * Sets the security parameter.
	 * 
	 * @param security the new value of the security.
	 */
	public void setSecurity(String security) {
		this.security = security;
	}

	/**
	 * Gets the value of date.
	 * 
	 * @return the current value of the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date parameter.
	 * 
	 * @param date the new value of the date.
	 */
	public void setDate(Date d) {
		this.date = d;
	}

	/**
	 * Gets the value of open.
	 * 
	 * @return the current value of the open.
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * Sets the open parameter.
	 * 
	 * @param open the new value of the open.
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * Gets the value of high.
	 * 
	 * @return the current value of the high.
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * Sets the high parameter.
	 * 
	 * @param high the new value of the high.
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * Gets the value of low.
	 * 
	 * @return the current value of the low.
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * Sets the low parameter.
	 * 
	 * @param low the new value of the low.
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * Gets the value of close.
	 * 
	 * @return the current value of the close.
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * Sets the close parameter.
	 * 
	 * @param close the new value of the close.
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * Gets the value of volume.
	 * 
	 * @return the current value of the volume.
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * Sets the volume parameter.
	 * 
	 * @param volume the new value of the volume.
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	/**
	 * Gets the value of adjClose.
	 * 
	 * @return the current value of the adjClose.
	 */
	public Double getAdjClose() {
		return adjClose;
	}

	/**
	 * Sets the adjClose parameter.
	 * 
	 * @param adjClose the new value of the adjClose.
	 */
	public void setAdjClose(Double adj_close) {
		this.adjClose = adj_close;
	}

	/**
	 * Converts object details to string format.
	 */
	public String toString() {
		return security + " " + date + " " + " " + open + " " + high + " " + low + " " + close + " " + volume + " "
				+ adjClose;
	}

	@Override
	public int compare(StockTrade o1, StockTrade o2) {
		
		return o1.getVolume()>o2.getVolume()?1:(o1.getVolume()<o2.getVolume())?-1:0;
	}

}
