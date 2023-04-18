package com.alpha.femulator.Model;

public class APIResponse {
    String prediction;

    public APIResponse(String prediction) {
        this.prediction = prediction;
    }

    public String getPrediction() {
        return prediction;
    }
}
