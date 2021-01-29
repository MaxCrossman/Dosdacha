package ru.itis.maxcrossman.models;

public class Page {
    private Long id;
    private String name;
    private String address;
    private Boolean isOpen;
    private Boolean isBoard;

    public Page(Long id, String name, String address, Boolean isOpen, Boolean isBoard) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isOpen = isOpen;
        this.isBoard = isBoard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean isBoard() {
        return isBoard;
    }

    public void setIsBoard(Boolean isBoard) {
        this.isBoard = isBoard;
    }
}
