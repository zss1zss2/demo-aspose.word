package com.zss.api.apose;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class AsposeWordToPdf {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsposeWordToPdf.class);

    public boolean getLicense() {
        boolean result = false;
        try {
            InputStream license = getClass().getResourceAsStream("/license.xml");

            License asposeLicense = new License();
            asposeLicense.setLicense(license);
            result = true;
        } catch (Exception e) {
            LOGGER.error("get license exception:", e);
        }
        return result;
    }

    public void transfer() {
        if (!getLicense()) {
            return;
        }
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/123.doc");
            FileOutputStream fileOutputStream = new FileOutputStream(new File("f:\\123.pdf"));
            long old = System.currentTimeMillis();
            Document doc = new Document(resourceAsStream);

            doc.save(fileOutputStream, SaveFormat.PDF);

            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n");
        } catch (Exception e) {
            LOGGER.error("transfer pdf exception:", e);
        }
    }

    public DownloadFile transferToPdfStream() {
        DownloadFile downloadFile = DownloadFile.create();
        if (!getLicense()) {
            return downloadFile;
        }
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/123.doc");
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("f:\\123.pdf"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long old = System.currentTimeMillis();
            Document doc = new Document(resourceAsStream);

            doc.save(byteArrayOutputStream, SaveFormat.PDF);

            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n");

            InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            downloadFile.setInputStream(byteArrayInputStream);
            downloadFile.setName("123.pdf");
        } catch (Exception e) {
            LOGGER.error("transfer pdf stream exception:", e);
        }
        return downloadFile;
    }
}
