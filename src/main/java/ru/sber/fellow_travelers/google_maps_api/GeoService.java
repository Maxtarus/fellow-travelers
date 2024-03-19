package ru.sber.fellow_travelers.google_maps_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.google_maps_api.response.Coordinates;
import ru.sber.fellow_travelers.google_maps_api.response.GeocodeResult;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeoService {

    public Coordinates getGeocode(String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f955861c4cmsh9cde810dbd664dfp136987jsnd77ba583120a"/*  Use your API Key here */)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();

        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        String latitude = result.getResults().get(0).getGeometry().getGeocodeLocation().getLatitude();
        String longitude = result.getResults().get(0).getGeometry().getGeocodeLocation().getLongitude();

        return new Coordinates(latitude, longitude);
    }

}