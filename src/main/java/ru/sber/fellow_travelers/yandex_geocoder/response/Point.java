package ru.sber.fellow_travelers.yandex_geocoder.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Point(@JsonProperty("pos") String coordinates) {
}
