package com.itheima.pojo;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

public class Student {
    private String name;
    private Date date;
    private Timestamp timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", timestamp=" + timestamp +
                '}';
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Test
    public void fun1(){
        Student student = new Student();
        student.setName("zs");
        student.setDate(new Date());
        student.setTimestamp(new Timestamp(1663171200000L));
        System.out.println(student);
    }
}
