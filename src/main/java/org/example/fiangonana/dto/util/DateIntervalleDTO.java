package org.example.fiangonana.dto.util;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateIntervalleDTO {
    private LocalDate dateMin;
    private LocalDate dateMax;

    public DateIntervalleDTO() {
    }

    public DateIntervalleDTO(LocalDate dateMin, LocalDate dateMax) {
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }

    @Override
    public String toString() {
        return "DateIntervalleDTO{" +
                "dateMin=" + dateMin +
                ", dateMax=" + dateMax +
                '}';
    }
}
