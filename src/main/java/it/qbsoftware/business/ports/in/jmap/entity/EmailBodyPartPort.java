package it.qbsoftware.business.ports.in.jmap.entity;

public interface EmailBodyPartPort {

    public String getPartId();

    public String getCharset();

    public String getType();

    public String getName();

    public Long getSize();
}
