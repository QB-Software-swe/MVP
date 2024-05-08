package it.qbsoftware.application;

import com.google.gson.JsonSyntaxException;

public interface ApiRequestDispatch {

    String dispatch(String jmapRequest) throws JsonSyntaxException;
}
