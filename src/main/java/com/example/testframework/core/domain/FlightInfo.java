package com.example.testframework.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class FlightInfo {
    @JsonProperty("Depart")
    String depart;
    @JsonProperty("Arrive")
    String arrive;
    @JsonProperty("Stops")
    String stops;
    @JsonProperty("Duration")
    String duration;
    @JsonProperty("Price lowest economy")
    String price;

    public static void printJsonInConsole(List<FlightInfo> dataList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
