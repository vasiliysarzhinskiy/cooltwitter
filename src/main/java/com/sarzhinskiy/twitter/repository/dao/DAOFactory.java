package com.sarzhinskiy.twitter.repository.dao;

import com.sarzhinskiy.twitter.repository.city.CityDAO;
import com.sarzhinskiy.twitter.repository.country.CountryDAO;
import com.sarzhinskiy.twitter.repository.news.NewsDAO;
import com.sarzhinskiy.twitter.repository.twit.TwitDAO;
import com.sarzhinskiy.twitter.repository.user.UserDAO;

public interface DAOFactory {
	public abstract UserDAO getUserDAO();
	public abstract TwitDAO getTwitDAO();
	public abstract NewsDAO getNewsDAO();
	public abstract CountryDAO getCountryDAO();
	public abstract CityDAO getCityDAO();

}
