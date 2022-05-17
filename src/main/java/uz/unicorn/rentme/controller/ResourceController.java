package uz.unicorn.rentme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.service.FileUploadService;

import java.util.List;

@RestController
@RequestMapping("/file")
public class ResourceController extends AbstractController<FileUploadService> {

    public ResourceController(FileUploadService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object upload(@RequestParam("file") List<MultipartFile> multipartFile) {
        return service.upload(multipartFile);
    }

}