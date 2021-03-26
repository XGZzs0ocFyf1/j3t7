import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gurzhiy.forTestCase.Strings;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSomeStuffNTimes {


    @ParameterizedTest
    @MethodSource
        // hmm, no method name ...
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        assertTrue(Strings.isBlank(input));
    }

    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ", "йцу");
    }
}
