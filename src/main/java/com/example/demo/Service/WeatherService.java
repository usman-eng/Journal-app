package com.example.demo.Service;

import com.example.demo.api.response.WeatherResponse;
import com.example.demo.cache.AppCache;
import com.example.demo.constants.Placeholders;
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
    @Value("${elevenLab_api_key}")
    private String ElevenLabKey;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String Replace=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,Apikey);
       ResponseEntity<WeatherResponse> response =restTemplate.exchange(Replace, HttpMethod.GET,null, WeatherResponse.class);
       WeatherResponse body=response.getBody();
       return body;
    }



}
