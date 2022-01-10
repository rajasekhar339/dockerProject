package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

public class JsonDiff {

    public void diff(String actualJson, String expectedJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type =
                new TypeReference<HashMap<String, Object>>() {
                };

        Map<String, Object> leftMap = mapper.readValue(actualJson, type);
        Map<String, Object> rightMap = mapper.readValue(expectedJson, type);

        System.out.println(leftMap + "--" + rightMap);

        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the left\n--------------------------");
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries only on the right\n--------------------------");
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        difference.entriesDiffering()
                .forEach((key, value) -> System.out.println(key + ": " + value));

    }
}
