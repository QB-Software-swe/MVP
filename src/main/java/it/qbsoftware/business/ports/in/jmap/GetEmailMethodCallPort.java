package it.qbsoftware.business.ports.in.jmap;

public interface GetEmailMethodCallPort extends GetMethodCallPort {
    public String[] getBodyProperties();

    public Boolean getFetchTextBodyValues();

    public Boolean getFetchHTMLBodyValues();

    public Boolean getFetchAllBodyValues();

    public Long getMaxBodyValueBytes();
}