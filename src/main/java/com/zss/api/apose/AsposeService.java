package com.zss.api.apose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsposeService {

    @Autowired
    private AsposeWordToPdf asposeWordToPdf;

    public void transferPdf() {
        asposeWordToPdf.transfer();
    }

    public DownloadFile getDownloadFile() {
        return asposeWordToPdf.transferToPdfStream();
    }
}
