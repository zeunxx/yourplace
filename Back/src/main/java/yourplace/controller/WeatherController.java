package yourplace.controller;

import static java.lang.Double.parseDouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.dto.CafeDto;
import yourplace.dto.WeatherDto;
import yourplace.service.WeatherServiceImpl;

@Controller
@RequiredArgsConstructor
public class WeatherController {

	private final WeatherServiceImpl weatherServiceImpl;
	
	@GetMapping("/") // 인덱스 화면 load & 날씨 띄우기
	public ModelAndView callApiWithJson() throws Exception{

		// 날씨 api 파싱 로직
		WeatherDto weatherDto = weatherServiceImpl.weatherParsing();
		
		// 날씨 맞춤 가게 검색
		int weather = weatherDto.getPTY(); // 0: 날씨 좋음 1: 비 : 눈
		double temp = weatherDto.getT1H();	// 최고기운 : 30도 이상이면 더운날!
		
		List<Cafe> cafeList = weatherServiceImpl.selectWeatherPlace(weather,temp);
	
		// Like를 LikeDto로 바꾼후 list로 반환
		List<CafeDto> cafeDto = cafeList.stream().map(CafeDto::new).collect(Collectors.toList());
	
		
		// model and view 리턴
		ModelAndView mv = new ModelAndView();
		mv.addObject("cafeDto",cafeDto);
		mv.addObject("weatherDto", weatherDto);
		mv.setViewName("index");
		
		return mv;
		
	}
}
