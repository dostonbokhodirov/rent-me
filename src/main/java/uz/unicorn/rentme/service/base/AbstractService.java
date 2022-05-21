package uz.unicorn.rentme.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.unicorn.rentme.exceptions.BadRequestException;
import uz.unicorn.rentme.mapper.base.BaseMapper;
import uz.unicorn.rentme.repository.base.BaseRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AbstractService<M extends BaseMapper, R extends BaseRepository> implements BaseService {

    protected final M mapper;
    protected final R repository;

    @Autowired
    protected EntityManager entityManager;

    protected <T> T getResponse(String json) {
        assert json != null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Json Error");
        }
    }

}
