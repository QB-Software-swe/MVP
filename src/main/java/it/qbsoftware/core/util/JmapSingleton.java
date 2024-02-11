package it.qbsoftware.core.util;

import it.qbsoftware.core.Jmap;

public enum JmapSingleton {
  INSTANCE;

  Jmap jmap = new Jmap();

  public Jmap getJmap() {
    return jmap;
  }
}
