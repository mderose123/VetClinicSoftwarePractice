package model.report;

import exceptions.EmptyStringException;

import java.util.Objects;

public class Question {
    private String questionString;
    private String answerString;

    public Question(String questionString) throws EmptyStringException {
         if( questionString == null || questionString.isEmpty()) {
             throw new EmptyStringException("Question must not be null/empty");
         }
         this.questionString = questionString;
         this.answerString = "No Answer Entered";
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setAnser(String answer) throws EmptyStringException {
        if( answer == null || answer.isEmpty()) {
            throw new EmptyStringException("Question must not be null/empty");
        }
        this.answerString = answer;
    }

    public String getAnswer() {
        return answerString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionString.equals(question.questionString) &&
                answerString.equals(question.answerString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionString, answerString);
    }
}
