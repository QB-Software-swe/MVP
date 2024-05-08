package it.qbsoftware.business.domain.methodcall.filter;

public record EmailFilterBodyPartSettings(
        String[] bodyProperties,
        Boolean fetchTextBodyValues,
        Boolean fetchHtmlBodyValues,
        Boolean fetchAllBodyValues,
        Long maxBodyValueBytes) {}
