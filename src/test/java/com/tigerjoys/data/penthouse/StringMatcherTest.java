package com.tigerjoys.data.penthouse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringMatcherTest {

    @Test
    void ExactMatchTest() {

        StringMatcher stringMatcher = new StringMatcher();

        List<List<String>> X_train = new ArrayList<>();
        List<String> y_train = new ArrayList<>();
        List<List<String>> X_test = new ArrayList<>();
        List<String> y_test = new ArrayList<>();

        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource("mio_parameters.csv").getPath();
        if (filePath.contains(":") && filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        try {
            List<String> entries = Files.readAllLines(Paths.get(filePath));
            for (int i = 1; i < entries.size(); i++) {
                String[] dimArr = entries.get(i).split(",");
                List<String> dimList = new ArrayList<>();
                for (int j = 1; j < 7; j++) {
                    dimList.add(dimArr[j].replaceAll("\"", ""));
                }
                X_train.add(dimList);
                y_train.add(dimArr[0].replaceAll("\"", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        stringMatcher.fit(X_train, y_train);

        //"005af21d-71c3-4845-8dcb-253c4c6061ba","Xiaomi","MI 5s",23,"arm64-v8a","2016-12-20 19:28:00","3.18.20"
        List<String> entry = new ArrayList<>();
        entry.add("Xiaomi");
        entry.add("MI 5s");
        entry.add("23");
        entry.add("arm64-v8a");
        entry.add("2016-12-20 19:28:00");
        entry.add("3.18.20");
        X_test.add(entry);

        String actual = stringMatcher.predict(X_test).get(0);
        assertEquals("005af21d-71c3-4845-8dcb-253c4c6061ba", actual);
    }
}
