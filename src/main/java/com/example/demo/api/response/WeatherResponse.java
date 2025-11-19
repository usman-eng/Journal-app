package com.example.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
        private Current current;

        @Getter
        @Setter
        public class Current{
            @JsonProperty("observation_time")
            private String observationTime;
            private int temperature;
            @JsonProperty("weather_icons")
            private List<String> weatherIcons;
            @JsonProperty("weather_descriptions")
            private List<String> weatherDescriptions;
            private int pressure;
            private double precip;
            private int humidity;
            private int cloudcover;
            private int feelslike;
            private int visibility;
            @JsonProperty("is_day")
            private String isDay;
        }
    }



