package ru.itis.maxcrossman.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class PageForm {
    @NotEmpty(message = "Name field should not be empty")
    @Length(min = 3,
            max = 50,
            message = "Name must be between {min} and {max} characters long")
    @Pattern(regexp = "^[\\w ]+$",
            message = "Name can contain only letters, digits, spaces, underscores and dashes")
    private String name;

    @NotEmpty(message = "Address field should not be empty")
    @Length(min = 1,
            max = 10,
            message = "Address must be between {min} and {max} characters long")
    @Pattern(regexp = "[a-zA-Z0-9_-]+$",
            message = "Address can contain only letters, digits, underscores and dashes")
    private String address;

    public PageForm(String name, String address) {
        this.name = name;
        this.address = address;
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
}
