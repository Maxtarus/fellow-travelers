package ru.sber.fellow_travelers.yandex_geocoder.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response(@JsonProperty("GeoObjectCollection") GeoObjectCollection geoObjectCollection) {
}
