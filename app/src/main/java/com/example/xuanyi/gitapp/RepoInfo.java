package com.example.xuanyi.gitapp;


/*
a class/struct for repo page list item information storing and using
 */
public class RepoInfo {
    String repo_name;
    String owner_name;
    String description;
    String repo_link;

    public RepoInfo(String repo_name, String owner_name, String description, String repo_link) {
        this.repo_name = repo_name;
        this.owner_name = owner_name;
        this.description = description;
        this.repo_link = repo_link;
    }

    public String getRepo_name() {
        return repo_name;
    }


    public String getOwner_name() {
        return owner_name;
    }


    public String getDescription() {
        return description;
    }


    public String getRepo_link() {
        return repo_link;
    }

}
