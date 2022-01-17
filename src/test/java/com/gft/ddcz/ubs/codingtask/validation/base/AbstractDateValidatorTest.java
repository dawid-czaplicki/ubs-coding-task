package com.gft.ddcz.ubs.codingtask.validation.base;

import com.gft.ddcz.ubs.codingtask.model.FxTransaction;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import lombok.Getter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.gft.ddcz.ubs.codingtask.validation.base.AbstractDateValidator.Comparison.*;
import static org.assertj.core.api.Assertions.assertThat;

class AbstractDateValidatorTest {

    static final String ERROR = "error";
    static final LocalDate TODAY = LocalDate.now();
    static final LocalDate YESTERDAY = TODAY.minusDays(1L);
    static final LocalDate TOMORROW = TODAY.plusDays(1L);

    static class ClassUnderTest extends AbstractDateValidator<FxTransaction> {
        final LocalDate firstDate;
        final LocalDate secondDate;

        @Getter
        final AbstractDateValidator.Comparison comparison;

        ClassUnderTest(LocalDate firstDate, LocalDate secondDate, Comparison comparison) {
            super(ERROR);
            this.firstDate = firstDate;
            this.secondDate = secondDate;
            this.comparison = comparison;
        }

        @Override
        protected LocalDate getFirstDate(FxTransaction target) {
            return firstDate;
        }

        @Override
        protected LocalDate getSecondDate(FxTransaction target) {
            return secondDate;
        }
    }

    @ParameterizedTest
    @MethodSource("provideForComparisonFails")
    void shouldAddErrorWhenComparisonFails(LocalDate date1, AbstractDateValidator.Comparison comparison, LocalDate date2) {
        var objectUnderTest = new ClassUnderTest(date1, date2, comparison);
        var validationResult = new ValidationResult();

        objectUnderTest.validate(Mockito.mock(FxTransaction.class), validationResult);

        assertThat(validationResult.getErrors()).containsExactly(ERROR);
    }

    static Stream<Arguments> provideForComparisonFails(){
        return Stream.of(
                Arguments.of(TODAY, AFTER, TOMORROW),
                Arguments.of(TOMORROW, BEFORE, TODAY),
                Arguments.of(TOMORROW, NOT_AFTER, TODAY),
                Arguments.of(TODAY, NOT_BEFORE, TOMORROW)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForComparisonSucceeds")
    void shouldAddErrorWhenComparisonSucceeds(LocalDate date1, AbstractDateValidator.Comparison comparison, LocalDate date2) {
        var objectUnderTest = new ClassUnderTest(date1, date2, comparison);
        var validationResult = new ValidationResult();

        objectUnderTest.validate(Mockito.mock(FxTransaction.class), validationResult);

        assertThat(validationResult.getErrors()).isEmpty();
    }

    static Stream<Arguments> provideForComparisonSucceeds(){
        return Stream.of(
                Arguments.of(TODAY, BEFORE, TOMORROW),
                Arguments.of(TOMORROW, AFTER, TODAY),
                Arguments.of(TODAY, NOT_BEFORE, TODAY),
                Arguments.of(TODAY, NOT_AFTER, TOMORROW)
        );
    }
}