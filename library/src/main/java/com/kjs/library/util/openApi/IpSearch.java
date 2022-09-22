package com.kjs.library.util.openApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Component
public class IpSearch {
	
	private static String serviceKey = "u0SwpgWPRoZ9krokqduxWflQDgCLLdpmo4O8zP2%2BSvKWX%2F%2BnGpgdhdD395qZ7%2BRr2R%2BFRQJ9J0z9iaGjV%2Bk%2BdQ%3D%3D";
	
	/**
	 * 주어진 주소가 국내 아이피이면서 사설 아이피가 아닌 경우 TRUE
	 * @param ip, String
	 * @return 주어진 주소가 국내 아이피이면서 사설 아이피가 아닌 경우 TRUE, 아니면 FALSE
	 * @throws  IOException
	 * */
	public Boolean 국내아이피다(String ip) throws IOException {
		 
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551505/whois/ip_address"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(ip, "UTF-8")); /*등록정보를 조회 할IP 주소*/
        urlBuilder.append("&" + URLEncoder.encode("answer","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답형식(XML/JSON) 을 지정(없으면 XML으로 응답)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
		while ((line = rd.readLine()) != null) {
		    sb.append(line);
		}
		rd.close();
		conn.disconnect();
        
		JSONObject jObject = new JSONObject(sb.toString());
		JSONObject jObjectResponse =  jObject.getJSONObject("response");// response
		//log.info("jObject2 {}",jObjectResponse.toString());
		
		JSONObject jObjectResult=  jObjectResponse.getJSONObject("result"); // response/result
		//log.info("jObject30 {}",jObject30.toString());
		
		int resultCode =  jObjectResult.getInt("result_code");// response/result/result_code
		   // log.info("resultCode {}", resultCode);//결과 코드
		
		JSONObject jObjectWhois =  jObjectResponse.getJSONObject("whois");// response/whois
		
		String registry =  jObjectWhois.getString("registry"); // response/whois/registry
		//log.info("registry {}",registry);//registry 코드
		
		String countryCode =  jObjectWhois.getString("countryCode"); // response/whois/countryCode
		   // log.info("countryCode {}", countryCode);//registry 코드
		
		/* conn.getResponseCode() == 200 -> 응답 성공
		 	 result_code == 10000 -> 조회 성공
			 registry.equals("KRNIC" ) -> 지역 레지스트리가 한국임(http://www.ktword.co.kr/test/view/view.php?m_temp1=4864)
			 registry가 SPECIAL인 경우 사설 아이피로 판단함
			 countryCode.equals("KR") -> ip가 한국에 할당됨
		*/
		if(conn.getResponseCode() == 200 && resultCode == 10000 && registry.equals("KRNIC" ) && countryCode.equals("KR")) {
			//log.info("true");
			return true;
		}else {
		 	//log.info("false");
			return false;
		}

    }
}
