package yourplace.controller;

import static java.lang.Double.parseDouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
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
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("weatherDto", weatherDto);
		mv.setViewName("index");
		
		return mv;
		
	}
}
