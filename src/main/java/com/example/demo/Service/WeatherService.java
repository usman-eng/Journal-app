package com.example.demo.Service;

import com.example.demo.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String Apikey="4dcce3ec83267757ebd020e2330918b11";
    private static final String URL="http://api.weatherstack.com/current?access_key=Apikey&query=CITY";
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String Replace=URL.replace("CITY",city).replace("Apikey",Apikey);
       ResponseEntity<WeatherResponse> response =restTemplate.exchange(Replace, HttpMethod.GET,null, WeatherResponse.class);
       WeatherResponse body=response.getBody();
       return body;
    }



}
