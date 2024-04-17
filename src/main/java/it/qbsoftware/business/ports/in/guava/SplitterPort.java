package it.qbsoftware.business.ports.in.guava;

import java.util.List;

public interface SplitterPort {
    public SplitterPort on(char separator);

    public List<String> splitToList(CharSequence sequence);
}
