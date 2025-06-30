package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.dto.in.RegisterDto;
import cl.ucm.bookapi.ApiBook.entities.RolEntity;
import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;
import cl.ucm.bookapi.ApiBook.repository.RolRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRolRepository userRolRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolService rolService;

    @Autowired
    private UserRolService userRolService;

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return repository.findById(email);
    }

    @Override
    public Optional<UserEntity> updateState(String email, boolean state) {
        Optional<UserEntity> optional = repository.findById(email);
        if(optional.isPresent()){
            UserEntity user = optional.get();
            user.setState(state);
            return Optional.of(repository.save(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RegisterDto> createUser(RegisterDto dto){
        Optional<UserEntity> optionalUserEntity = repository.findById(dto.getEmail());
        if(optionalUserEntity.isPresent()){
            return Optional.empty();
        }


        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setState(true);
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(user);

        // verifico si existe el rol LECTOR

        RolEntity lectorRol = rolService.findByName("LECTOR").orElseThrow(
                () -> new RuntimeException("Rol LECTOR no encontrado")
        );

        // Creo un nuevo user rol e ingreso el id de usuario e id del rol
        UserRolEntity userRol = new UserRolEntity();
        userRol.setUserFk(user.getEmail());
        userRol.setRolFk(lectorRol.getId());
        userRolService.save(userRol);

        dto.setPassword(user.getPassword());
        return Optional.of(dto);
    }
}
