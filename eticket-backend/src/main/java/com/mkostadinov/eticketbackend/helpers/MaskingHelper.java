package com.mkostadinov.eticketbackend.helpers;

import org.springframework.stereotype.Component;

@Component
public class MaskingHelper {

    public String maskStringWithSymbol(String toMask, char mask) {
        int numberOfSymbolsToMask = toMask.length() / 2;

        StringBuilder toReturn = new StringBuilder(toMask);

        for (int i = 0; i <= numberOfSymbolsToMask; i++) {
            toReturn.setCharAt(i, mask);
        }

        return toReturn.toString();
    }
}
