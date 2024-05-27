package ru.sber.fellow_travelers.yandex_geocoder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.yandex_geocoder.client.YandexGeocoderClient;
import ru.sber.fellow_travelers.yandex_geocoder.mapper.CoordinatesMapper;
import ru.sber.fellow_travelers.yandex_geocoder.response.Coordinates;
import ru.sber.fellow_travelers.yandex_geocoder.response.YandexGeocoderResponse;

@Service
@RequiredArgsConstructor
public class YandexGeocoderService {
    private static final String RESPONSE_FORMAT = "json";
    private final YandexGeocoderClient yandexGeocoderClient;
    private final CoordinatesMapper mapper;
    @Value("${api.yandex-geocoder.api-key}")
    private String apiKey;

    public Coordinates getPointCoordinates(String geocode) {
        YandexGeocoderResponse response = yandexGeocoderClient.geocode(apiKey, geocode, RESPONSE_FORMAT, 1);
        return mapper.toCoordinates(response);
    }
}
