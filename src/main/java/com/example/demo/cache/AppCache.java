package com.example.demo.cache;

import com.example.demo.Repository.ConfigRepository;
import com.example.demo.entry.ConfigEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    public Map<String, String> appCache;
    @Autowired
    private ConfigRepository configRepository;

    @PostConstruct
    public void init() {
        appCache= new HashMap<>();
        List<ConfigEntry> all= configRepository.findAll();
        for (ConfigEntry configEntry : all)
        {
            appCache.put(configEntry.getKey(), configEntry.getValue());
        }
    }
}
