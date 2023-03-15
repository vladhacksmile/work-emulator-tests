package task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.NaN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Double.NaN;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SinFunctionTest {

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/2, -Math.PI/3, -Math.PI/4,
            -Math.PI/6, 0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2})
    void rightHalfSinTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        Assertions.assertEquals(expected, SinFunction.sin(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {4*Math.PI/6, 3*Math.PI/4,
            2*Math.PI/3, Math.PI, 7*Math.PI/6, 5*Math.PI/4, 5*Math.PI/3})
    void leftHalfSinTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        Assertions.assertEquals(expected, SinFunction.sin(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {22, 32.7777, 4, 19, 0.15, 18, 1.3, 0.000001})
    void differentAnglesTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        Assertions.assertEquals(expected, SinFunction.sin(value));
    }

    @Test
    void nanArgTest() {
        boolean isNan = Double.isNaN(SinFunction.sin(NaN));
        Assertions.assertTrue(isNan);
    }

}