package org.example;

public class Comment {
    private int id;
    // 게시물 번호
    private  String comment;
    //댓글
    private  String date;

    Comment(int id, String comment, String date){
        this.id = id;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
