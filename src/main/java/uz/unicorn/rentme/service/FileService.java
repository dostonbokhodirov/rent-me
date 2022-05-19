package uz.unicorn.rentme.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.BaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService implements BaseService {

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("picturesaver-61bc7.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }

        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public ResponseEntity<DataDTO<List<String>>> upload(List<MultipartFile> files) {
        ArrayList<String> PATH = new ArrayList<>(files.size());
        files.forEach(item -> {
            try {
                PATH.add(upload(item));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ResponseEntity<>(new DataDTO<>(PATH, (long) PATH.size()));
    }

    public String upload(MultipartFile picture) throws IOException {
        String fileName = picture.getOriginalFilename();                        // to get original file name
        assert fileName != null;
        fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.
        File file = convertToFile(picture, fileName);
        return this.uploadFile(file, fileName);
    }
}
