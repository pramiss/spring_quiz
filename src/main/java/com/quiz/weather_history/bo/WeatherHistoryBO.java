package com.quiz.weather_history.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.weather_history.domain.WeatherHistory;
import com.quiz.weather_history.mapper.WeatherHistoryMapper;

@Service
public class WeatherHistoryBO {
	
	@Autowired
	private WeatherHistoryMapper weatherHistoryMapper;
	
	// SELECT, input: X, output: List<WeatherHistory>
	public List<WeatherHistory> getWeatherHistoryList() {
		return weatherHistoryMapper.selectWeatherHistoryList();
	}
	
	// INSERT, input: WeatherHistory, output: X
	public void addWeatherHistory(WeatherHistory weatherHistory) {
		weatherHistoryMapper.insertWeatherHistory(weatherHistory);
	}
	
}