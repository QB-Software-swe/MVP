package it.qbsoftware.core;

public enum SingletonJmap {
    INSTANCE;
    
    JmapMail jmap = new JmapMail();

    public JmapMail getJmap() {
        return jmap;
    }
}
