package com.cloudskol.ifeel.feel;

/**
 * Created by tham on 05-11-2016.
 */

public class Feeling {
    private int id;
    private String date;
    private String feeling;
    private String person;
    private String summary;

    public Feeling(int id, String date, String feeling) {
        this.id = id;
        this.date = date;
        this.feeling = feeling;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getFeeling() {
        return feeling;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Feeling{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", feeling='" + feeling + '\'' +
                ", person='" + person + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
