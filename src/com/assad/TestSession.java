package com.assad;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;











import Decoder.BASE64Encoder;

public class TestSession {
	public static final  String sessionId = "JSESSIONID=5AD74FC7C15C92EDEA787347FF5CADAF";
	
	public static void main(String [] args ) {
		
		SimpleClientHttpRequestFactory s = new SimpleClientHttpRequestFactory() {
			@Override
			protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
				super.prepareConnection(connection, httpMethod);

				//Basic Authentication for Police API
				String authorisation = "jack" + ":" + "jill";




				//            byte[] encodedAuthorisation = ;
				connection.setRequestProperty("Authorization", "Basic " + new String(new BASE64Encoder().encode(authorisation.getBytes())));
			}
		};
		

        String json = "{"
					  + "\"name\": \"Assaddfadfads\","
					  +"\"address\": \"kushtia\""
					  +"}";
	
        
        HttpHeaders requestHeaders1 = new HttpHeaders();
        
        requestHeaders1.add("Cookie", sessionId);
        requestHeaders1.setContentType(MediaType.APPLICATION_JSON);
        
               
        HttpEntity<String>  requestEntity1 = new HttpEntity<String>(json, requestHeaders1);
        ResponseEntity response = null;
        RestTemplate restTemplate = new RestTemplate(s);
        response = restTemplate.exchange(
                "http://localhost:8080/gateway/rest/api/pets/123",
                HttpMethod.POST,
                requestEntity1,
                String.class); 
        
        
        String body =  (String)response.getBody();
        response.getHeaders();
        
        System.out.println(body);
        		
	}
}
