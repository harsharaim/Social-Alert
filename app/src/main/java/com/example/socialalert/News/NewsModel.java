package com.example.socialalert.News;

import com.example.socialalert.News.Articles;

import java.util.ArrayList;

public class NewsModel {
    private int totalResults;
    private String status;
    private ArrayList<Articles> articles;

    public NewsModel(String status, int totalResults, ArrayList<Articles> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getarticles() {
//

        System.out.println("--------------------------------------");
       // System.out.println("--------------------------------------"+Articles.size());
        System.out.println("--------------------------------------");


        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
