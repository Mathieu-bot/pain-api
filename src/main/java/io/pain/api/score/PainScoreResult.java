package io.pain.api.score;

public record PainScoreResult(
    int scoreGlobal,
    int scoreData,
    int scoreTime,
    int scoreRisk,
    int confidenceGlobal,
    int confidenceData,
    int confidenceTime,
    int confidenceRisk,
    String version
) {}
