package com.example.demo.Service;

import com.example.demo.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String Apikey;
    private static final String URL="http://api.weatherstack.com/current?access_key=Apikey&query=CITY";
    @Value("${elevenLab_api_key}")
    private String ElevenLabKey;
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String Replace=URL.replace("CITY",city).replace("Apikey",Apikey);
       ResponseEntity<WeatherResponse> response =restTemplate.exchange(Replace, HttpMethod.GET,null, WeatherResponse.class);
       WeatherResponse body=response.getBody();
       return body;
    }



}
