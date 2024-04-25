package it.qbsoftware.adapters.in.guava;

import java.util.List;

import com.google.common.base.Splitter;

import it.qbsoftware.business.ports.in.guava.SplitterPort;

public class SplitterAdapter implements SplitterPort {
    private Splitter splitter = null;

    @Override
    public SplitterPort on(char separator) {
        this.splitter = Splitter.on(separator);
        return this;
    }

    @Override
    public List<String> splitToList(CharSequence sequence) {
        return splitter.splitToList(sequence);
    }

}
