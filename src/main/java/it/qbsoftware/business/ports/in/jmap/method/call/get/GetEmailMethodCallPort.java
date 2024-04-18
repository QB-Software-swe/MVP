package it.qbsoftware.business.ports.in.jmap.method.call.get;

public interface GetEmailMethodCallPort extends GetMethodCallPort {
    public String[] getBodyProperties();

    public Boolean getFetchTextBodyValues();

    public Boolean getFetchHtmlBodyValues();

    public Boolean getFetchAllBodyValues();

    public Long getMaxBodyValueBytes();
}