package eu.vargapeter.instrumentcatalog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    public void storeFile(MultipartFile file) throws IOException;
}
