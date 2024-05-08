package it.qbsoftware.adapters.in.guava;

import org.junit.jupiter.api.Test;

public class CaseFormatAdapterTest {
    @Test
    void testConvert() {
        String value = "lowerCamel";

        CaseFormatAdapter caseFormatAdapter = new CaseFormatAdapter();
        String result = caseFormatAdapter.convert(value);

        assert result.equals("LOWER_CAMEL");
    }
}
