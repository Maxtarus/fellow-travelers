package ru.sber.fellow_travelers.google_maps_api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static ru.sber.fellow_travelers.google_maps_api.GooGleMapsApiStringConstants.*;

@Service
public class GeoService {
    @Value("${google-maps.api-key}")
    private static String API_KEY;

    private final CoordinatesMapper mapper;

    public GeoService(CoordinatesMapper mapper) {
        this.mapper = mapper;
    }

    public Coordinates getPointCoordinates(String address) {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        Request request = new Request.Builder()
                .url(REQUEST_URL + encodedAddress)
                .get()
                .addHeader(HOST_HEADER, HOST)
                .addHeader(API_KEY_HEADER, API_KEY)
                .build();

        String response;
        try {
            response = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mapper.toCoordinates(response);
    }

}