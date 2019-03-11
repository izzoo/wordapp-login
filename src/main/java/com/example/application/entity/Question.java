package com.example.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String answer;

    @Transient
    private String maskedAnswer;

    @Transient
    private boolean guessed = false;

    @Transient
    private boolean firstMaskedIndexInit = true;

    @Transient
    int[] maskedIndex;

    public int[] getMaskedIndex() {
        return maskedIndex;
    }

    public int getMaskedIndexIndex( int i) {
        return maskedIndex[i];
    }

    public void setMaskedIndex(int[] maskedIndex) {
        this.maskedIndex = maskedIndex;
    }

    public void setMaskedIndexIndex(int i, int j) {
        this.maskedIndex[i] = j;
    }

    public String getMaskedAnswer() {
        return maskedAnswer;
    }

    public void setMaskedAnswer(String maskedAnswer) {
        this.maskedAnswer = maskedAnswer;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public boolean isFirstMaskedIndexInit() {
        return firstMaskedIndexInit;
    }

    public void setFirstMaskedIndexInit(boolean firstMaskedIndexInit) {
        this.firstMaskedIndexInit = firstMaskedIndexInit;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
