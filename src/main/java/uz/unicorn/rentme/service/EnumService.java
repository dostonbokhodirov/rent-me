package uz.unicorn.rentme.service;

import org.springframework.stereotype.Service;
import uz.unicorn.rentme.enums.transport.TransportType;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.BaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EnumService implements BaseService {
    public static  ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(String str) {
        List<String> types = new ArrayList<>();
        List<TransportType> transportTypes = Arrays.stream(TransportType.values()).filter(
                transportType -> transportType.toString().toLowerCase().startsWith(str.toLowerCase())
        ).toList();

        transportTypes.forEach(transportType -> types.add(transportType.toString()));
        return new ResponseEntity<>(new DataDTO<>(types));
    }

}
