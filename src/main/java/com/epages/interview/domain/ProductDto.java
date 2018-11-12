package com.epages.interview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Immutable DTO for external communication.
 * Assumed using with Jackson serialization capabilities.
 * All nullable values and brand field value are excluded.
 */
@JsonInclude(Include.NON_NULL)
public class ProductDto {

    @Nonnull
    private final Long id;
    @Nonnull
    private final String name;
    private final double price;
    @Nonnull
    private final String brand;
    @Nullable
    private final Event event;

    public ProductDto(@Nonnull Long id,
                      @Nonnull String name,
                      double price,
                      @Nonnull String brand,
                      @Nullable Event event) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.price = price;
        this.brand = Objects.requireNonNull(brand, "brand");
        this.event = event;
    }

    @Nonnull
    public Long getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Nonnull
    @JsonIgnore
    public String getBrand() {
        return brand;
    }

    @Nullable
    public Event getEvent() {
        return event;
    }

}
