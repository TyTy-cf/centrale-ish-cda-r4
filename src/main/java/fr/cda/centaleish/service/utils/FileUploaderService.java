package fr.cda.centaleish.service.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

public class FileUploaderService {

    private final Path path;
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public FileUploaderService(String path) {
        this.path = Path.of(path);
        try {
            if (!Files.exists(this.path)) {
                Files.createDirectories(this.path);
            }
        } catch (IOException e) {
            System.out.println("Oupsi");
        }
    }

    public String save(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            return null;
        }
        try {
            String uniqId = UUID.randomUUID().toString();
            String fileName = uniqId + "-" + StringUtils.cleanPath(normalizeFileName(file.getOriginalFilename()));
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException e) {
            return null;
        }
    }

    private String normalizeFileName(String file) {
        String noWhitespace = WHITESPACE.matcher(file).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
