package com.gft.ddcz.ubs.codingtask.validation.forward;

import com.gft.ddcz.ubs.codingtask.model.Forward;
import com.gft.ddcz.ubs.codingtask.validation.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForwardValueDateValidatorTest {

    public static final LocalDate NOW_DATE = LocalDate.now();

    @InjectMocks
    ForwardValueDateValidator forwardValueDateValidator;

    @Mock
    Clock clock;

    @Mock
    Forward forwardMock;

    @BeforeEach
    void setUp() {
        when(clock.instant()).thenReturn(NOW_DATE.atStartOfDay().toInstant(UTC));
        when(clock.getZone()).thenReturn(UTC);
    }

    @Test
    void valueAfterCurrentDateShouldSucceed() {
        when(forwardMock.getValueDate()).thenReturn(NOW_DATE.plusDays(1L));
        var validationResult = new ValidationResult();

        forwardValueDateValidator.validate(forwardMock, validationResult);

        assertThat(validationResult.getErrors()).isEmpty();
    }

    @Test
    void valueNotAfterCurrentDateShouldFail() {
        when(forwardMock.getValueDate()).thenReturn(NOW_DATE);
        var validationResult = new ValidationResult();

        forwardValueDateValidator.validate(forwardMock, validationResult);

        assertThat(validationResult.getErrors()).containsExactly("forward.value.date.must.be.after.current.date");
    }
}