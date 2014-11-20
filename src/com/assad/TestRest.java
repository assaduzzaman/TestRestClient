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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;







import Decoder.BASE64Encoder;

public class TestRest {
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
		requestHeaders.add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
		RestTemplate restTemplate = new RestTemplate(s);

		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();   
		body.add("name", "assadxxxxxxx");
		body.add("address", "addressXXX");

		HttpEntity <?>requestEntity = new HttpEntity<Object>( null,requestHeaders);
		ResponseEntity response = null;
/**
		HttpMessageConverter mc = new MappingJacksonHttpMessageConverter();
		List<HttpMessageConverter<?>> msl = new ArrayList<HttpMessageConverter<?>>();
		msl.add(mc);
		restTemplate.setMessageConverters(msl);
		*/
		try {
			response = restTemplate.exchange(
					"http://localhost:8080/gateway/rest/api/pets/123",
					HttpMethod.POST,
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
        
        HttpHeaders requestHeaders1 = new HttpHeaders();
        requestHeaders1.add("Cookie", sessionId);
        
        HttpEntity requestEntity1 = new HttpEntity(null, requestHeaders1);
        response = restTemplate.exchange(
                "http://localhost:8080/gateway/rest/api/json/123",
                HttpMethod.GET,
                requestEntity1,
                String.class); 
        
        String bodyx =  (String)response.getBody();
        
        System.out.println(body);		

	}



}
