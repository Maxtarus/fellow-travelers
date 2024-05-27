package ru.sber.fellow_travelers.yandex_geocoder.response;

import java.util.List;

public record GeoObjectCollection(List<FeatureMember> featureMember) {
}
