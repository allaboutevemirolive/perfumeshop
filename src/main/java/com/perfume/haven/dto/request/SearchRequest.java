package com.perfume.haven.dto.request;

import java.util.List;

public class SearchRequest {
    private List<String> perfumers;
    private List<String> genders;
    private Integer price = 0;
    private String searchType;
    private String text;

    public List<String> getPerfumers() {
        return perfumers;
    }

    public void setPerfumers(List<String> perfumers) {
        this.perfumers = perfumers;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "perfumers=" + perfumers +
                ", genders=" + genders +
                ", price=" + price +
                ", searchType='" + searchType + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
