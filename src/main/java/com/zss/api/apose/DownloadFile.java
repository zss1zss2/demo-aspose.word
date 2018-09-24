package com.zss.api.apose;

import java.io.InputStream;

public class DownloadFile {
    private String name;
    private InputStream inputStream;

    private DownloadFile() {

    }

    public static DownloadFile create() {
        return new DownloadFile();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
