package it.qbsoftware.adapters.in.guava;

import com.google.common.base.CaseFormat;

import it.qbsoftware.business.ports.in.guava.CaseFormatPort;

public class CaseFormatAdapter implements CaseFormatPort {
    @Override
    public String convert(String value) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);
    }
}
