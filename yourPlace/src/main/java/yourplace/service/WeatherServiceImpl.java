package yourplace.service;

import static java.lang.Double.parseDouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import yourplace.dto.WeatherDto;

@Service
public class WeatherServiceImpl implements WeatherService{

	// 날씨 api의 category
	enum WeatherValue{ 
		PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
	}
	
	// 날씨 api json 파싱
	@Override
	public WeatherDto weatherParsing() throws Exception{
			
			WeatherDto weatherDto = new WeatherDto();
			
			LocalDate now = LocalDate.now();
			String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        String baseTime = "1200";
			// 변수 설정
			String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?"+
					"serviceKey=K3eWwz0jU2OFSx7ktwhWeKly2%2BtkPvrdtFqlwY13U0jKs9PXfv0O8qXNCUHQaArH1KKgydGBuddB3T%2FHWUJNZw%3D%3D"+
					"&pageNo=4"+
					"&numOfRows=10"+
					"&dataType=JSON"+
					"&base_date="+baseDate+
					"&base_time="+baseTime+
					"&nx=60"+
					"&ny=127"; // 서울의 8월 5일(today로 바꿔야 함) 12시 날씨 정보
			
			
			URL url = new URL(apiUrl);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+"\n"+"Response code: "+conn.getResponseCode());
			
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
			
			weatherDto.setDate("20220805");
			weatherDto.setTime("1200");
			
		return weatherDto;
	}
}
