package tn.ucar.enicar.info.projetspring.sevices;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(rootLocation);
    }

    public String storeFile(MultipartFile file, String subfolder) throws IOException {
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path targetFolder = rootLocation.resolve(subfolder);
        Files.createDirectories(targetFolder);
        Path targetPath = targetFolder.resolve(filename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return subfolder + "/" + filename; // "events/uuid-filename.jpg"
    }

    public UrlResource loadFile(String path) throws IOException {
        Path file = rootLocation.resolve(path).normalize();
        return new UrlResource(file.toUri());
    }
}

