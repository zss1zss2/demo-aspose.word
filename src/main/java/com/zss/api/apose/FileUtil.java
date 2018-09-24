package com.zss.api.apose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);


    private FileUtil() {
    }

    static InputStreamResource body(InputStream inputStream) {
        return new InputStreamResource(inputStream);
    }

    static HttpHeaders httpHeaders(DownloadFile file) throws IOException {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final String filename = file.getName();
        httpHeaders.setContentType(MediaType.valueOf("application/pdf"));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=UTF-8''" + encode(filename));
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getInputStream().available()));
        return httpHeaders;
    }

    private static String encode(String name) {
        try {
            return URLEncoder.encode(name, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
