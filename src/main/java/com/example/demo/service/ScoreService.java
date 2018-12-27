package com.example.demo.service;

import com.example.demo.entity.Score;

public interface ScoreService {
    Score queryScore(Integer uId);
    boolean addScore(Score score);
    boolean updateScore(Score score);
}
