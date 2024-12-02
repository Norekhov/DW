package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class Ads {

    @Schema(type = "integer",
            format = "int32",
            description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<Ad> results;

    @Override
    public String toString() {
        return "Ads{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(count, ads.count) && Objects.equals(results, ads.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Ad> getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }

    public Ads() {
    }

    public Ads(Integer count, List<Ad> results) {
        this.count = count;
        this.results = results;
    }
}



