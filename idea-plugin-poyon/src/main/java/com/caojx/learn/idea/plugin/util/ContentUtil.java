package com.caojx.learn.idea.plugin.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class ContentUtil {

    public static String getContent(){
        RestTemplate restTemplate = new RestTemplate();
        try{

            ResponseEntity<Map> forEntity = restTemplate.getForEntity("https://api.nextrt.com/V1/Dutang", Map.class);
            HttpStatus httpStatus = forEntity.getStatusCode();
            if(httpStatus.is2xxSuccessful()){
                List data = (List) forEntity.getBody().get("data");
                Map<String, String> contentMap = (Map<String, String>) data.get(0);
                return contentMap.get("content");
            }
        }catch (Exception ex){
            return "汤碗都碎了";
        }
        return "今天没有毒鸡汤";
    }
}
