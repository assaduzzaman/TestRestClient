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

public class TestRest2 {
	public static void main(String [] args) {

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


		HttpHeaders requestHeaders = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate(s);

		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		HttpEntity requestEntity = new HttpEntity( requestHeaders);
		ResponseEntity response = null;

		try {
			response = restTemplate.exchange(
					"http://localhost:8081/gateway/rest/api/json/123",
					HttpMethod.GET,
					requestEntity,
					String.class);    

		} catch(Throwable t){

			System.out.println(t.getMessage());

			t.printStackTrace();
		} 
		
		
		
        HttpHeaders reqq = response.getHeaders();
        
        System.out.println(reqq);
        
        String ss = reqq.get("Set-Cookie").get(0);
        

        
        
                
        
        System.out.println(response + " response cookies" + ss);
        
        String sessionId = ss.substring(ss.indexOf("JSESSIONID="),ss.indexOf(";"));
        
  
        
        String json = "{"
					  + "\"name\": \"Assaddfadfads\","
					  +"\"address\": \"kushtia\""
					  +"}";
	
        
        HttpHeaders requestHeaders1 = new HttpHeaders();
        
        requestHeaders1.add("Cookie", sessionId);
        requestHeaders1.setContentType(MediaType.APPLICATION_JSON);
        
               
        HttpEntity<String>  requestEntity1 = new HttpEntity<String>(json, requestHeaders1);
        
        response = restTemplate.exchange(
                "http://localhost:8081/gateway/rest/api/pets/123",
                HttpMethod.POST,
                requestEntity1,
                String.class); 
        
        
        String body =  (String)response.getBody();
        System.out.println(body);
        
        HttpHeaders reshh2 = response.getHeaders();
        
        HttpHeaders requestHeaders2 = new HttpHeaders();
        
//        requestHeaders2.add("Cookie", sessionId);
        requestHeaders2.setContentType(MediaType.APPLICATION_JSON);    
        HttpEntity<String>  requestEntity2 = new HttpEntity<String>(json, requestHeaders2);
        
        
//        System.out.println(body);		
        response = restTemplate.exchange(
                "http://localhost:8081/gateway/rest/api/pets/123",
                HttpMethod.POST,
                requestEntity2,
                String.class); 
        HttpHeaders reshh3 = response.getHeaders();
        
        System.out.println(reshh3.toString());
	
	}



}
