package uz.unicorn.rentme.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.dto.transportModel.TransportModelCreateDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.TransportModelService;

import java.util.List;

@RestController
@RequestMapping("/transport-model")
public class TransportModelController extends AbstractController<TransportModelService> {

    private final RestTemplate restTemplate;

    public TransportModelController(TransportModelService service, RestTemplate restTemplate) {
        super(service);
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get-name/{name}")
    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(@PathVariable(value = "name") String type) {
        return service.getTransportTypeVal(type);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody TransportModelCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping(value = "/list-details")
    public ResponseEntity<DataDTO<List<TransportModelDTO>>> getAllDetails() {
        return service.getAll(null);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<DataDTO<List<String>>> getAll() {
        return service.getAllName();
    }

    @GetMapping(value = "/aziz")
    public String postWithRestTemplate() {
        String url = "http://localhost:8080/transport-model/create";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        try {
            personJsonObject.put("name", "Car");
            personJsonObject.put("category", "CAR");
            personJsonObject.put("imagePath", "car_image_path");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);
        return restTemplate.postForObject(url, request, String.class);
    }
}
