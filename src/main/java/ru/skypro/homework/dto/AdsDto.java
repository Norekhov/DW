package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class AdsDto {

    @Schema(type = "integer",
            format = "int32",
            description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<AdDto> results;

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
        AdsDto adsDto = (AdsDto) o;
        return Objects.equals(count, adsDto.count) && Objects.equals(results, adsDto.results);
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

    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
        this.results = results;
    }

    public AdsDto() {
    }

    public AdsDto(Integer count, List<AdDto> results) {
        this.count = count;
        this.results = results;
    }
}



