package it.qbsoftware.adapters.in.jmaplib.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.entity.EmailSubmission.EmailSubmissionBuilder;
import rs.ltt.jmap.gson.JmapAdapters;

public class EmailSubmissionBuilderAdapter implements EmailSubmissionBuilderPort {
    private EmailSubmissionBuilder emailSubmissionBuilder = EmailSubmission.builder();
    private String id = "0";

    private static Gson GSON;

    static {
        GSON = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        GSON = gsonBuilder.create();
    }

    @Override
    public EmailSubmissionBuilderPort id(final String id) {
        this.id = id;
        return this;
    }

    @Override
    public EmailSubmissionPort build() {
        final EmailSubmission emailSubmission = GSON.fromJson("{\"id\":\"" + id + "\"}", EmailSubmission.class);
        return new EmailSubmissionAdapter(emailSubmission);
    }

    @Override
    public EmailSubmissionBuilderPort reset() {
        emailSubmissionBuilder = EmailSubmission.builder(); return this;
    }

}