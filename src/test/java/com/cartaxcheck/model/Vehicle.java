package com.cartaxcheck.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Vehicle {

    @CsvBindByName(column="registration")
    String registration;

    @CsvBindByName(column="make")
    String make;

    @CsvBindByName(column="model")
    String model;

    @CsvBindByName(column="color")
    String colour;

    @CsvBindByName(column="year")
    String year;

    @Override
    public String toString() {
        return "Vehicle{" +
                "Registration: '" + registration + '\'' +
                ", Make: '" + make + '\'' +
                ", Model: '" + model + '\'' +
                ", Colour: '" + colour + '\'' +
                ", Year: '" + year + '\'' +
                '}';
    }

}
