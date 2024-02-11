package it.qbsoftware.persistence;

import java.util.ArrayList;

import rs.ltt.jmap.common.entity.Email;

public interface EmailDao {
    ArrayList<Email> getEmailsInMailboxs(String mailboxsId);
    void saveEmail(Email email);
}
