package com.example.socialalert.Issue;


public class DataClass {
    private String title;
    private String description;
    private String department;
    private String location;
    private String username;
    private String uri;
    private String status;

    public DataClass() {
        // Default constructor required for Firebase
    }

    public DataClass(String title, String description, String department, String location, String username, String uri, String status) {
        this.title = title;
        this.description = description;
        this.department = department;
        this.location = location;
        this.username = username;
        this.uri = uri;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
