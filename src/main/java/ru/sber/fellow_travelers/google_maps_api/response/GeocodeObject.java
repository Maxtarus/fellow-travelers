package ru.sber.fellow_travelers.google_maps_api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {
    GeocodeGeometry geometry;

    public GeocodeObject() { }

    public GeocodeGeometry getGeometry() {
        return geometry;
    }

}
