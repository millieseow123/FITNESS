package vttp2022.mini.project.Fitness.services;

public class FitnessException extends Exception {
    private String reason;

    public FitnessException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
