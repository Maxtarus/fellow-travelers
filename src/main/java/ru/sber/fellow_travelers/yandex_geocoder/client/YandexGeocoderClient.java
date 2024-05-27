package ru.sber.fellow_travelers.yandex_geocoder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sber.fellow_travelers.yandex_geocoder.configuaration.FeignConfig;
import ru.sber.fellow_travelers.yandex_geocoder.response.YandexGeocoderResponse;

@FeignClient(
        name = "${api.yandex-geocoder.name}",
        url = "${api.yandex-geocoder.url}",
        configuration = FeignConfig.class
)

public interface YandexGeocoderClient {
    @GetMapping
    YandexGeocoderResponse geocode(@RequestParam("apikey") String apiKey,
                                   @RequestParam("geocode") String geocode,
                                   @RequestParam("format") String format,
                                   @RequestParam("results") int resultsNumber);
}