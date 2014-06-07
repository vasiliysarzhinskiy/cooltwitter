package com.sarzhinskiy.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarzhinskiy.twitter.domain.twit.Twit;
import com.sarzhinskiy.twitter.repository.twit.TwitDAO;

@Service("TwitService")
public class TwitServiceImpl implements TwitService {

	private TwitDAO repository;
	
	@Autowired
	public TwitServiceImpl(TwitDAO repository) {
		this.repository = repository;
	}
	
	@Override
	public boolean create(Twit twit) {
		return repository.create(twit);
	}

	@Override
	public List<Twit> findAllByOwnerId(Long id) {
		return repository.findAllByOwnerId(id);
	}

	@Override
	public List<Twit> findNLastByOwnerId(Long id, int numberTwits) {
		return repository.findLastByOwnerId(id, numberTwits);
	}
	
	@Override
	public boolean update(Twit twit) {
		return repository.update(twit);
	}

	@Override
	public boolean updateLikeNumber(Twit twit) {
		return repository.updateLikeNumber(twit);
	}

	@Override
	public boolean remove(Long id) {
		return repository.remove(id);
	}

	@Override
	public Twit findById(Long twitId) {
		return repository.findById(twitId);
	}

}
