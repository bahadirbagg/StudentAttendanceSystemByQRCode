package com.example.proje;

public class Student {
    int id;
    String no;
    String course;

    public Student() {

    }

    public Student(String no ,String course){

        this.no = no;
        this.course = course;
    }
    public Student (int id, String no, String course){
        this.id = id;
        this.no = no;
        this.course = course;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
