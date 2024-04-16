package it.qbsoftware.adapters.in.jmaplib.utils;

import it.qbsoftware.business.ports.in.utils.CaseFormatPort;
import com.google.common.base.CaseFormat;

public class CaseFormatAdapter implements CaseFormatPort {
    @Override
    public String convert(String value) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, value);
    }
}
