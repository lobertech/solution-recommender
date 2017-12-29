package com.tigerjoys.data.penthouse;

import java.util.*;

import org.apache.lucene.search.spell.LuceneLevenshteinDistance;

public class StringMatcher implements RecommenderAlgorithm<String, String> {

    private LuceneLevenshteinDistance levenshteinDistance;
    private List<List<String>> features;
    private List<String> targets;

    private Map<List<String>, String> sampleset;

    public Map<List<String>, String> getSampleset() {
        return sampleset;
    }

    public StringMatcher() {
        this.features = null;
        this.targets = null;
        this.levenshteinDistance = new LuceneLevenshteinDistance();
        this.sampleset = new HashMap<>();
    }

    @Override
    public RecommenderAlgorithm fit(List<List<String>> X, List<String> y) {
        if (X != null && !X.isEmpty() && y != null && !y.isEmpty()) {
            this.features = X;
            this.targets = y;
            Iterator<List<String>> itX = this.features.iterator();
            Iterator<String> ity = this.targets.iterator();
            while (itX.hasNext() && ity.hasNext()) {
                List<String> valueX = itX.next();
                String valuey = ity.next();
                this.sampleset.put(valueX, valuey);
            }
            return this;
        }
        return null;
    }

    @Override
    public List<String> predict(List<List<String>> X) {
        float maxSimilarity = -1.0f;
        List<String> candidate = null;
        List<String> targets = null;
        if (X != null && !X.isEmpty() && features != null && !features.isEmpty()) {
            targets = new ArrayList<>();
            for (List<String> dims : X) {
                for (List<String> value : features) {
                    float similar = getStringDistance(
                            String.join("", dims),
                            String.join("", value));
                    if (similar > -1.0f) {
                        if (similar > maxSimilarity) {
                            maxSimilarity = similar;
                            candidate = value;
                        }
                    }
                }
                targets.add(sampleset.get(candidate));
            }
        }
        return targets;
    }

    private float getStringDistance(String str1, String str2) {
        float distance = -1.0f;
        if (str1 != null && !str1.isEmpty() && str2 != null && !str2.isEmpty()) {
            distance = levenshteinDistance.getDistance(str1, str2);
        }
        return distance;
    }
}
