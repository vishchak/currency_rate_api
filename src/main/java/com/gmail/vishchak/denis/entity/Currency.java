package com.gmail.vishchak.denis.entity;

import com.gmail.vishchak.denis.utils.Id;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    private long id;

    private String baseCurrency;

    private String currency;

    private double conversionRate;
}
