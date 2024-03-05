package kr.co.sist.vo;

import java.sql.Date;

public class WorkJdbcVO {
    private int id;
    private String name;
    private int age;
    private byte[] image;
    private Date inputDate;

    public WorkJdbcVO() {
    }

    public WorkJdbcVO(int id, String name, int age, byte[] image, Date inputDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.image = image;
        this.inputDate = inputDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public String toString() {
        return "WorkJdbcVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", inputDate=" + inputDate +
                '}';
    }
}
