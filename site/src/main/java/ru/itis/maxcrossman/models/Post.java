package ru.itis.maxcrossman.models;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private Long boardId;
    private Long authorId;
    private String name;
    private String value;
    private LocalDateTime timestamp;
    private Boolean isThread;
    private Long threadId;

    public Post(Long id, Long boardId, Long authorId, String name, String value, LocalDateTime timestamp, Boolean isThread, Long threadId) {
        this.id = id;
        this.boardId = boardId;
        this.authorId = authorId;
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
        this.isThread = isThread;
        this.threadId = threadId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean isThread() {
        return isThread;
    }

    public void setIsThread(Boolean isThread) {
        this.isThread = isThread;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }
}
