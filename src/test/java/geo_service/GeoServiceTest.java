package geo_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceTest {

    @ParameterizedTest
    @MethodSource("sourceLocalization")
    public void testService(String ip, Location location) {
        GeoService geoService = new GeoServiceImpl();

        Location expected = geoService.byIp(ip);

        Assertions.assertEquals(expected, location);
    }

    private static Stream<Arguments> sourceLocalization() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("196.105.23.6", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @Test
    public void testByCoordinates() {
        GeoService geoService = new GeoServiceImpl();

        Assertions.assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(0,0);
        }, "Error");
    }
}
