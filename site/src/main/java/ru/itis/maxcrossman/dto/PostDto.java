package ru.itis.maxcrossman.dto;

public class PostDto {
    private String author;
    private String name;
    private String value;
    private String timestamp;
    private String link;

    public PostDto(String author, String name, String value, String timestamp, String link) {
        this.author = author;
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
