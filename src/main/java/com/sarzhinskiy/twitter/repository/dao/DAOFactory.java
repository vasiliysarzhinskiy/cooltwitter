package com.sarzhinskiy.twitter.repository.dao;

public interface DAOFactory {
	public abstract UserDAO getUserDAO();
	public abstract TwitDAO getTwitDAO();
	public abstract NewsDAO getNewsDAO();
	public abstract CountryDAO getCountryDAO();
	public abstract CityDAO getCityDAO();

}
