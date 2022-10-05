package yourplace.service;

import java.util.List;

import yourplace.domain.Cafe;
import yourplace.domain.Rest;
import yourplace.dto.WeatherDto;

public interface WeatherService {

	// 날씨 api json 파싱
	public WeatherDto weatherParsing() throws Exception;

	// 날씨 맞춤 가게 추천

	public List<Rest> selectWeather(int weather,double temp) throws Exception;
}