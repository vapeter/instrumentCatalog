package eu.vargapeter.instrumentcatalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/admin")
public class FileDownloadController {

    @GetMapping("/download")
    public void downloadFile(String fileName, HttpServletResponse res) throws Exception {
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        res.getOutputStream().write(contentOf(fileName));
    }

    private byte[] contentOf(String fileName) throws Exception {
        return Files.readAllBytes( Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
    }
}
