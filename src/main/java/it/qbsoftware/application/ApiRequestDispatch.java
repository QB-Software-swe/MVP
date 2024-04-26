package it.qbsoftware.application;

import com.google.gson.JsonSyntaxException;

public interface ApiRequestDispatch {

    String Dispatch(String jmapRequest) throws JsonSyntaxException;

}