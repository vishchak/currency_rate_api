package com.gmail.vishchak.denis.entity;

import com.gmail.vishchak.denis.utils.Id;
import lombok.*;

import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    private long id;

    private String baseCurrency;

    private Map<String, Double> currencyConversionRate;

}
