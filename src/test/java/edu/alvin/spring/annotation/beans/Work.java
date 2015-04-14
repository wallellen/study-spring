package edu.alvin.spring.annotation.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("all")
public class Work implements Serializable {
    private Integer no;
    private String name;
    private LocalDateTime begin;
    private LocalDateTime finish;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("no=")
                .append(no)
                .append(", name=")
                .append(name)
                .append(", begin=")
                .append(begin)
                .append(", finish=")
                .append(finish)
                .append("}")
                .toString();
    }
}
