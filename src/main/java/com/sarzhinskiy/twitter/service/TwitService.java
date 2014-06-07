package com.sarzhinskiy.twitter.service;

import java.util.List;

import com.sarzhinskiy.twitter.domain.Image;
import com.sarzhinskiy.twitter.domain.twit.Twit;

public interface TwitService {
	public boolean create(Twit twit);
	public Twit findById(Long twitId);
	public List<Twit> findAllByOwnerId(Long id);
	public List<Twit> findNLastByOwnerId(Long id, int numberTwits);
	public boolean update(Twit twit);
	public boolean updateLikeNumber(Twit twit);
	public boolean remove(Long id);
}


//public List<Twit> findAllByOwnerEmail(String email);
//public boolean removeAll();
//public boolean insertImage(Twit twit, Image image);
//public boolean removeImage(Twit twit);
//public Image findImage(Long twitId);