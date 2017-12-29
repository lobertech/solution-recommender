package com.tigerjoys.data.penthouse;

import com.tigerjoys.data.penthouse.model.SolutionResult;

import java.util.Map;

public class SubRecommendation implements SubRecommendService {
    private Recommendable recommendable;

    public SubRecommendation() {
        this.recommendable = new RomBasedRecommender();
    }

    @Override
    public SolutionResult recommend(Map<String, Object> features) {
        SolutionResult solutionResult = new SolutionResult();
        solutionResult.setRecommender(recommendable.getClass().getSimpleName());

        Map<String, Object> targetResult = recommendable.recommend(features);

        solutionResult.setCode(targetResult.get("keys").toString());
        return solutionResult;
    }
}
