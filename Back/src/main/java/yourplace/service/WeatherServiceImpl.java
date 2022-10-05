package yourplace.service;

import static java.lang.Double.parseDouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.domain.Rest;
import yourplace.dto.WeatherDto;
import yourplace.repository.CafeRepository;
import yourplace.repository.RestRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class WeatherServiceImpl implements WeatherService{

	private final CafeRepository cafeRepository;
	private final RestRepository restRepository;
	
	// 날씨 api의 category
	enum WeatherValue{ 
		PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
	}
	
	// 날씨 api json 파싱
	@Override
	public WeatherDto weatherParsing() throws Exception{
			
			WeatherDto weatherDto = new WeatherDto();
			Calendar calendar = new GregorianCalendar();
			SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
			String today = SDF.format(calendar.getTime());		
			
			String baseDate = today;
			
			SimpleDateFormat format = new SimpleDateFormat ( "HH");		
			int hour= (Integer.parseInt((format.format (System.currentTimeMillis())))-1);

			String baseTime;
			if (hour==-1) { // hour가 00시이므로 전날 23시로 설정 
				baseTime ="2300";
				
				//어제날짜 구하기
				calendar.add(Calendar.DATE, -1);		
				String yesterday = SDF.format(calendar.getTime());		
				baseDate = yesterday;
				
			}else if(hour==0){ //hour가 01시면 1시 그대로 출력(00로 입력하면 application error남)
				baseTime = "0100";
			}else {
				baseTime = (Integer.toString(hour))+"00";
			}
			
			
			
			// 변수 설정
			String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?"+
					"serviceKey=K3eWwz0jU2OFSx7ktwhWeKly2%2BtkPvrdtFqlwY13U0jKs9PXfv0O8qXNCUHQaArH1KKgydGBuddB3T%2FHWUJNZw%3D%3D"+
					"&pageNo=4"+
					"&numOfRows=10"+
					"&dataType=JSON"+
					"&base_date="+baseDate+
					"&base_time="+baseTime+
					"&nx=60"+
					"&ny=127"; 
			
			
			URL url = new URL(apiUrl);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+"\n"+"Response code: "+conn.getResponseCode());
			
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode()<=300) {
				// 결과 제대로 받으면
				rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				// 결과값 버퍼로 받아옴
			}else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				// 에러값 버퍼로 받아옴
			}
			
			StringBuilder sb = new StringBuilder();
			String line;
			while((line=rd.readLine())!=null) {
				sb.append(line);
			}
			
			rd.close();
			conn.disconnect();
			String result = sb.toString();
			
		
			
			/**
			 * json 파싱
			 */
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject parseResponse = (JSONObject) jsonObject.get("response");
			JSONObject parseBody = (JSONObject) parseResponse.get("body"); //response로부터 body 파싱
			JSONObject parseItems = (JSONObject) parseBody.get("items"); // body로부터 items 파싱
		
			// items로부터 itemList : 뒤에 [로 시작하므로 jsonArray
			JSONArray parseItem = (JSONArray) parseItems.get("item");
			
			
			// item들을 담을 List를 반복자 안에서 사용하기 위해 명시
			JSONObject object;
			// item 내부의 category를 보고 사용하기 위함
			String category;
			Double value;
			
			// jsonArray를 반복
			for(int i=0 ; i<parseItem.size() ; i++) {
				object = (JSONObject) parseItem.get(i);
				category = (String) object.get("category"); // item에서 카테고리 검색
				
				// error 발생할 수 있음, 받아온 정보를 double이 아니라 문자열로 읽으면 오류
				value = parseDouble((String) object.get("obsrValue")); //날씨 값 파싱
				
				// 입력받은 카테고리를 WeatherValue 타입으로 바꾸기
				WeatherValue weatherValue = WeatherValue.valueOf(category);
				
				switch(weatherValue) {
				case PTY:
					weatherDto.setPTY(value.intValue());
					break;
				case REH:
					weatherDto.setREH(value);
					break;
				case RN1:
					weatherDto.setRN1(value);
					break;
				case T1H:
					weatherDto.setT1H(value);
					break;
				case UUU:
					weatherDto.setUUU(value);
					break;
				case VEC:
					weatherDto.setVEC(value);
					break;
				case VVV:
					weatherDto.setVVV(value);
					break;
				case WSD:
					weatherDto.setWSD(value);
					break;
				default:
					break;
					
				}
			}
			
			weatherDto.setDate(baseDate);
			weatherDto.setTime(baseTime);
			
		return weatherDto;
	}

	// 날씨 맞춤 가게 추천
	@Override
	public List<Rest> selectWeather(int weather, double temp) throws Exception {
			
		List<Rest> result=null;
		if(temp >=28.0) { // 더운날 
			result = restRepository.selectHotWeather();
		}else if(temp <= 10.0) {
			result = restRepository.selectColdWeather();
		}else {
			switch(weather) {
			case 0: // 날씨 좋음
				result = restRepository.selectGoodWeather();
				break;
			case 1 : //비
				result = restRepository.selectRainyWeather();
				break;
			case 2: // 눈
				result = restRepository.selectSnowWeather();
				break;
			case 3: // 눈
				result = restRepository.selectSnowWeather();
				break;
			case 4: // 비 
				result = restRepository.selectRainyWeather();
				break;
			case 5: // 비
				result = restRepository.selectRainyWeather();
				break;
			case 6: // 눈
				result = restRepository.selectSnowWeather();
				break;
			case 7: // 눈
				result = restRepository.selectSnowWeather();
				break;
			default:
				break;
				
			}
		}
		
		
		
		return result;
	}



}
