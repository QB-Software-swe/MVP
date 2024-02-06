package it.qbsoftware.core;

public enum SingletonJmap {
  INSTANCE;

  Jmap jmap = new Jmap();

  public Jmap getJmap() {
    return jmap;
  }
}
