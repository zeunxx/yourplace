package yourplace.service;

import yourplace.dto.WeatherDto;

public interface WeatherService {

	// 날씨 api json 파싱
	public WeatherDto weatherParsing() throws Exception;

}