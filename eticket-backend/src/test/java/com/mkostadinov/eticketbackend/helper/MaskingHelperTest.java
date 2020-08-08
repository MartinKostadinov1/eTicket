package com.mkostadinov.eticketbackend.helper;

import com.mkostadinov.eticketbackend.helpers.MaskingHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MaskingHelperTest {

    private MaskingHelper maskingHelperToTest;


    private final String TO_MASK = "Game of Thrones";
    private final char MASK = '$';

    private final String RIGHT_RESULT = "$$$$$$$$Thrones";

    @BeforeEach
    public void setUp() {
        this.maskingHelperToTest = new MaskingHelper();
    }

    @Test
    public void testShouldMaskStringSuccessfully() {
        String result = this.maskingHelperToTest.maskStringWithSymbol(TO_MASK, MASK);
        Assertions.assertEquals(RIGHT_RESULT, result);
    }

    @Test
    public void testShouldMaskStringFail() {
        String result = this.maskingHelperToTest.maskStringWithSymbol(TO_MASK, '€');
        Assertions.assertNotEquals(RIGHT_RESULT, result);
    }

}
