package com.project.backend.utils.services;

import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private int score=0;

    public int getScore(String identityNumber)
    {
        if(identityNumber.endsWith("0"))
            score=100;
        else if(identityNumber.endsWith("2"))
            score=200;
        else if(identityNumber.endsWith("4"))
            score=500;
        else if(identityNumber.endsWith("6"))
            score=600;
        else
            score=100;

    return score;

    }

}
