package com.tigerjoys.data.penthouse.model;

import java.util.Map;
import java.util.Objects;

public class DataSample<T> {
    private Map<String, Object> features;
    private T target;

    public Map<String, Object> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Object> features) {
        this.features = features;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSample<?> that = (DataSample<?>) o;
        return Objects.equals(features, that.features) &&
                Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {

        return Objects.hash(features, target);
    }

    @Override
    public String toString() {
        return "DataSample{" +
                "features=" + features +
                ", target=" + target +
                '}';
    }
}
