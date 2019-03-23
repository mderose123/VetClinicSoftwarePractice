package model.report;

import exceptions.NullArgumentException;
import model.client.Pet;


import java.util.*;

public abstract class ReportCard {
    private Date dateWritten;
    private Calendar referenceCalender;
    private List<Question> questionList;
    private Pet pet;
    private Scanner scan;

    public ReportCard(Pet pet) throws NullArgumentException {
        if(pet == null) {
            throw new NullArgumentException("Pet must not be null");
        }
        referenceCalender= Calendar.getInstance();
        dateWritten = referenceCalender.getTime();
        questionList = new ArrayList<>();
        this.pet = pet;
        scan = new Scanner(System.in);
    }

    public Date getDate() {
        return dateWritten;
    }

    public void setDateWritten(Date date) throws NullArgumentException {
        if(date == null) {
            throw new NullArgumentException("Date must not be null");
        }
        dateWritten = date;
    }

    public void addQuestion(Question question) throws NullArgumentException {
        if(question == null) {
            throw new NullArgumentException("Date must not be null");
        }
        if(!questionList.contains(question)) {
            questionList.add(question);
        }
    }

    public void removeQuestion(Question question) throws NullArgumentException  {
        if(question == null) {
            throw new NullArgumentException("Question must not be null");
        }
        if(questionList.contains(question)) {
            questionList.remove(question);
        }
    }

    public List<Question> getQuestionList() {
        return Collections.unmodifiableList(questionList);
    }

    public void resetQuestionList() {
        System.out.println("Are you sure you want to reset the question list? (Enter 'Y' for yes and 'N' for No");
        String answer = scan.nextLine();
        do {
            System.out.println("Invalid entry please enter 'Y' or 'N'");
        } while (!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n"));
        if(answer.equalsIgnoreCase("y")) {
            questionList.clear();
        } else {
            return;
        }
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) throws NullArgumentException {
        if(pet == null) {
            throw new NullArgumentException("Pet must not be null");
        }
        this.pet = pet;
    }






}
