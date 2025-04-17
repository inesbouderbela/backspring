package tn.ucar.enicar.info.projetspring.controllers;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.ucar.enicar.info.projetspring.sevices.FileStorageService;
@RestController
@RequestMapping("/api/uploads")
public class FileController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{folder}/{filename:.+}")
    public ResponseEntity<UrlResource> getFile(@PathVariable String folder, @PathVariable String filename) {
        try {
            UrlResource resource = fileStorageService.loadFile(folder + "/" + filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
