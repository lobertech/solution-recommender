package com.tigerjoys.data.penthouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RomBasedRecommenderTest {

    @Test
    void ExactMatchTest() {

        RomBasedRecommender romBasedRecommender = new RomBasedRecommender();
        List<Map<String, Object>> featTestList = new ArrayList<>();
        List<String> targetTestList = new ArrayList<>();

        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource("mio_parameters.csv").getPath();
        if (filePath.contains(":") && filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            Object[] entries = stream.toArray();
            String[] header;
            if (entries.length > 0) {
                List<String> featureNames = new ArrayList<>();
                header = ((String) entries[0]).split(",");
                if (header.length > 6) {
                    for (int i = 1; i < 6 + 1; i++) {
                        header[i] = header[i].replaceAll("\"", "");
                        featureNames.add(header[i]);
                    }
                    for (int i = 1; i < entries.length; i++) {
                        String[] dimArr = ((String) entries[i]).split(",");
                        Map<String, Object> features = new HashMap<>();
                        for (int j = 1; j < 6 + 1; j++) {
                            features.put(header[j], dimArr[j].replaceAll("\"", ""));
                        }
                        featTestList.add(features);
                        targetTestList.add(dimArr[0].replaceAll("\"", ""));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < featTestList.size(); i++) {
            String key = ((List<String>) romBasedRecommender.recommend(featTestList.get(i)).get("keys")).get(0);
            assertEquals(targetTestList.get(i), key);
        }
    }
}
