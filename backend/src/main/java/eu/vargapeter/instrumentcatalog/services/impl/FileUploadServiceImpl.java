package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.services.FileUploadService;
import eu.vargapeter.instrumentcatalog.util.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void storeFile(MultipartFile file) throws IOException {

        try {
            Path filePath = Paths.get(Constans.IMPORT_FILE_URL + file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException();
        }

    }
}
