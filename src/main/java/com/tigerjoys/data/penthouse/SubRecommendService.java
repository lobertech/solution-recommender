package com.tigerjoys.data.penthouse;

import com.tigerjoys.data.penthouse.model.SolutionResult;

import java.util.Map;

public interface SubRecommendService {
    SolutionResult recommend(Map<String, Object> features);
}
