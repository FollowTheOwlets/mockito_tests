package localization_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceTest {

    @ParameterizedTest
    @MethodSource("sourceLocalization")
    public void testService(Country country, String message) {
        LocalizationService localizationService = new LocalizationServiceImpl();

        String expected = localizationService.locale(country);

        Assertions.assertEquals(expected, message);
    }

    private static Stream<Arguments> sourceLocalization() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome")
        );
    }
}
