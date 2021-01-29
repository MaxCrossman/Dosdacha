package ru.itis.maxcrossman.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class SignUpForm {
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "First name should not be empty")
    @Length(min = 2,
            max = 20,
            message = "First name must be between {min} and {max} characters long")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Length(min = 3,
            max = 20,
            message = "Last name must be between {min} and {max} characters long")
    private String lastName;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 8,
            max = 20,
            message = "Password must be between {min} and {max} characters long")
    private String password;

    public SignUpForm(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
