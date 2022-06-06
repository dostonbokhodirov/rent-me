package uz.unicorn.rentme.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.controller.base.GenericCrudController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.criteria.SearchCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.dto.auth.SessionDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping(value = "/advertisement")
@PropertySource("classpath:apidoc/advertisement.properties")
public class AdvertisementController extends AbstractController<AdvertisementService>
        implements GenericCrudController<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    public AdvertisementController(AdvertisementService service) {
        super(service);
    }

    @Override
    @Operation(summary = "${get.summary}", description = "${get.desc}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${get.resCode.200.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "${get.resCode.404.desc}",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "${get.resCode.403.desc}",
                    content = @Content) })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/list")
    @Operation(summary = "${getAll.summary}", description = "${getAll.desc}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${getAll.resCode.200.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "${getAll.resCode.404.desc}",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "${getAll.resCode.403.desc}",
                    content = @Content) })
    @PostMapping(value = "/list")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        return service.getAll(criteria);
    }

    @Override
    @Operation(summary = "${create.summary}", description = "${create.desc}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${create.resCode.200.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "${create.resCode.404.desc}",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "${create.resCode.403.desc}",
                    content = @Content) })
    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody AdvertisementCreateDTO dto) {

        return service.create(dto);
    }

    @Override
    @Operation(summary = "${update.summary}", description = "${update.desc}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${update.resCode.200.desc}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "${update.resCode.404.desc}",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "${update.resCode.403.desc}",
                    content = @Content) })
    @PostMapping(value = "/update")
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        return service.update(dto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping(value = "/save-advertisement/{id}")
    public ResponseEntity<DataDTO<Boolean>> save(@PathVariable Long id) {

        return service.save(id);
    }

    @GetMapping("/list-daily")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllDaily(AdvertisementCriteria criteria) {
        return service.getAllDaily(criteria);
    }

    @GetMapping(value = "/list-long-term")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLongTerm(AdvertisementCriteria criteria) {
        return service.getAllLongTerm(criteria);
    }


    @GetMapping(value = "/list_my")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMyList(AdvertisementCriteria criteria) {
        return service.getAllMyList(criteria);
    }

    @GetMapping(value = "/list-saved")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getMySave(AdvertisementCriteria criteria) {
        return service.getAllMySave(criteria);
    }

    @GetMapping(value = "/list-last")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLast(AdvertisementCriteria criteria) {
        return service.getAllLast(criteria);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllBySearch(@RequestBody SearchCriteria criteria) {
        return service.getAllBySearch(criteria);
    }

}
