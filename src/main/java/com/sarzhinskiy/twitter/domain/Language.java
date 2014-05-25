package com.sarzhinskiy.twitter.domain;

import java.util.HashMap;
import java.util.Map;

public class Language {
	private Map<String, String> languageMap;

	public Language() {
		languageMap = new HashMap<String, String>();
		languageMap.put("en", "English");
		languageMap.put("uk", "Ukrainian");
		languageMap.put("ru", "Russian");
		languageMap.put("fr", "French");
	}
	
	public Map<String, String> getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map<String, String> languageMap) {
		this.languageMap = languageMap;
	} 
	
	
}
