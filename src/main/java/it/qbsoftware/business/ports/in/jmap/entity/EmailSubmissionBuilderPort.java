package it.qbsoftware.business.ports.in.jmap.entity;

public interface EmailSubmissionBuilderPort {
    public EmailSubmissionBuilderPort id(String id);

    public EmailSubmissionPort build();

    public EmailSubmissionBuilderPort reset();
}
