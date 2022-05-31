package uz.unicorn.rentme.enums.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransportColor {

    BLACK("Black"),
    METEOR_GREY("Meteor Grey"),
    BRIGHT_WHITE("Bright White"),
    CANDY_WHITE("Candy White"),
    BRILLIANT_SILVER("Brilliant Silver"),
    ENERGY_BLUE("Energy Blue"),
    RACE_BLUE("Race Blue"),
    VELVET_RED("Velvet Red"),
    CORRIDA_RED("Corrida Red"),
    YELLOW("Yellow"),
    ORANGE("Orange");

    private final String name;

}
