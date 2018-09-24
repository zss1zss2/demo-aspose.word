package com.zss.api.apose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.zss.api.apose.FileUtil.body;
import static com.zss.api.apose.FileUtil.httpHeaders;

@RestController
public class AsposeController {

    @Autowired
    private AsposeService asposeService;

    @GetMapping("/transfer")
    public void transferPdf() {
        asposeService.transferPdf();
    }

    @GetMapping("/file")
    public ResponseEntity<InputStreamResource> downloadPublicFile() throws Exception {
        DownloadFile fullFile = asposeService.getDownloadFile();
        return ResponseEntity.ok()
                .headers(httpHeaders(fullFile))
                .cacheControl(CacheControl.maxAge(Long.MAX_VALUE, TimeUnit.HOURS).cachePublic())
                .body(body(fullFile.getInputStream()));
    }
}
