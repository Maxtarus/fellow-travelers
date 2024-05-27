package ru.sber.fellow_travelers.yandex_geocoder.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FeatureMember(@JsonProperty("GeoObject") GeoObject geoObject) {

}
