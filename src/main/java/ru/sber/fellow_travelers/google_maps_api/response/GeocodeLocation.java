package ru.sber.fellow_travelers.google_maps_api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeLocation {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;
    public GeocodeLocation() { }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
}