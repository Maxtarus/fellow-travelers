package ru.sber.fellow_travelers.yandex_geocoder.mapper;

import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.yandex_geocoder.response.Coordinates;
import ru.sber.fellow_travelers.yandex_geocoder.response.YandexGeocoderResponse;

@Component
public class CoordinatesMapper {
    public Coordinates toCoordinates(YandexGeocoderResponse response) {

        String coordinates = response.response()
                .geoObjectCollection()
                .featureMember()
                .get(0)
                .geoObject()
                .point()
                .coordinates();

        String longitude = coordinates.split(" ")[0];
        String latitude = coordinates.split(" ")[1];

        return new Coordinates(latitude, longitude);
    }
}
