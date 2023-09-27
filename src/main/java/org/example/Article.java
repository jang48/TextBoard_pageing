package org.example;

public class Article {
    // 게시물 번호
    private int id;
    // 제목
    private String title;
    // 내용
    private String content;
    // 조회수
    private int hit;
    // 현재 날짜
    private String date;
    // 아이디
    private String joinid;
    private String joinpw;
    // 비밀번호
    private String nickname;
    //로그인 닉네임
    private String comment;
    // 댓글

    private int loveit;
    Article(int id, String title, String content, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
    Article(int id, String title, String content, String date, String joinid){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.joinid = joinid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getHit() {
        return hit;
    }

    public String getDate() {
        return date;
    }

    public String getJoinid() {
        return joinid;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setJoinid(String joinid) {
        this.joinid = joinid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJoinpw() {
        return joinpw;
    }

    public void setJoinpw(String joinpw) {
        this.joinpw = joinpw;
    }

    public int getLoveit() {
        return loveit;
    }

    public void setLoveit(int loveit) {
        this.loveit = loveit;
    }
}
