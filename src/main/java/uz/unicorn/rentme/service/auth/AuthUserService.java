package uz.unicorn.rentme.service.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.unicorn.rentme.criteria.AuthUserCriteria;
import uz.unicorn.rentme.dto.auth.AuthUserCreateDTO;
import uz.unicorn.rentme.dto.auth.AuthUserDTO;
import uz.unicorn.rentme.dto.auth.AuthUserUpdateDTO;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.enums.auth.AuthRole;
import uz.unicorn.rentme.enums.auth.Status;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AuthUserMapper;
import uz.unicorn.rentme.repository.AuthUserRepository;
import uz.unicorn.rentme.repository.OtpRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;

@Service
public class AuthUserService extends AbstractService<AuthUserMapper, AuthUserRepository>
        implements GenericCrudService<AuthUserDTO, AuthUserCreateDTO, AuthUserUpdateDTO, AuthUserCriteria> {

    private final OtpRepository otpRepository;

    public AuthUserService(@Qualifier("authUserMapperImpl") AuthUserMapper mapper, AuthUserRepository repository, OtpRepository otpRepository) {
        super(mapper, repository);
        this.otpRepository = otpRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<DataDTO<Long>> create(AuthUserCreateDTO dto) {
        AuthUser authUser = mapper.fromCreateDTO(dto);
        authUser.setRole(AuthRole.USER);
        authUser.setStatus(Status.INACTIVE);
        otpRepository.deleteIfExistsByPhoneNumber(authUser.getPhoneNumber());
        AuthUser save = repository.save(authUser);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(AuthUserUpdateDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<AuthUserDTO>> get(Long id) {
        AuthUser authUser = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        AuthUserDTO authUserDTO = mapper.toDTO(authUser);
        return new ResponseEntity<>(new DataDTO<>(authUserDTO));
    }

    @Override
    public ResponseEntity<DataDTO<List<AuthUserDTO>>> getAll(AuthUserCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<AuthUser> authUserList = repository.findAll(pageable).getContent();
        List<AuthUserDTO> authUserDTOList = mapper.toDTO(authUserList);
        return new ResponseEntity<>(new DataDTO<>(authUserDTOList, (long) authUserList.size()));
    }
}
