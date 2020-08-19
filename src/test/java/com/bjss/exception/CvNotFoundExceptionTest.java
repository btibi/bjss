package com.bjss.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class CvNotFoundExceptionTest {

    @Test
    public void trowCvNotFoundExceptionHasCorrectMsg() {
        CvNotFoundException e = Assert.assertThrows(CvNotFoundException.class, () -> {
            throw new CvNotFoundException(1L);
        });

        assertThat(e).hasMessageContaining("Could not find CV 1");
    }
}