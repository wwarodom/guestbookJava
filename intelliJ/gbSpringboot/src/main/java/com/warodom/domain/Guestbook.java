package com.warodom.domain;

import javax.persistence.*;

/**
 * Created by wwaro on 7/6/2559.
 */
@Entity
@Table(name="guestbook")
public class Guestbook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String message;

    public Guestbook() {}

    public Guestbook(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public long  getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Guestbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}