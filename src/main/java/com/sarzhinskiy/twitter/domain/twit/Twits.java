package com.sarzhinskiy.twitter.domain.twit;

import java.io.Serializable;
import java.util.List;

public class Twits implements Serializable {
	
	private List<Twit> twits;
	
	public Twits() {
	}
	
	public Twits(List<Twit> twits) {
		this.twits = twits;
	}
	
	public void setTwits(List<Twit> twits) {
		this.twits = twits;
	}
	
	public List<Twit> getTwits() {
		return twits;
	}

}
