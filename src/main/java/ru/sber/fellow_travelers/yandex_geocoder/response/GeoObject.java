package ru.sber.fellow_travelers.yandex_geocoder.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeoObject(@JsonProperty("Point") Point point) {
}
