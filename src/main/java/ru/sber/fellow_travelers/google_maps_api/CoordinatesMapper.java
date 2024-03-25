package ru.sber.fellow_travelers.google_maps_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.google_maps_api.response.GeocodeResult;

@Component
public class CoordinatesMapper {
    public Coordinates toCoordinates(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result;

        try {
            result = objectMapper.readValue(response, GeocodeResult.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String latitude = result.getResults()
                .get(0)
                .getGeometry()
                .getGeocodeLocation()
                .getLatitude();
        String longitude = result.getResults()
                .get(0)
                .getGeometry()
                .getGeocodeLocation()
                .getLongitude();

        return new Coordinates(latitude, longitude);
    }
}
