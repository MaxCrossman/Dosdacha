package ru.itis.maxcrossman.models;

public class User {
    private Long id;
    private String email;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private String profilePicturePath;

    public User(Long id, String email, String hashPassword, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String hashPassword, String firstName, String lastName) {
        this.email = email;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
