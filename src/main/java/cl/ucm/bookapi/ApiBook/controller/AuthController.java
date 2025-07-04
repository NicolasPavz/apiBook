package cl.ucm.bookapi.ApiBook.controller;


import cl.ucm.bookapi.ApiBook.dto.in.LoginDto;
import cl.ucm.bookapi.ApiBook.dto.in.RegisterDto;
import cl.ucm.bookapi.ApiBook.entities.RolEntity;
import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;
import cl.ucm.bookapi.ApiBook.security.JwtUtil;
import cl.ucm.bookapi.ApiBook.service.AccountService;
import cl.ucm.bookapi.ApiBook.service.RolService;
import cl.ucm.bookapi.ApiBook.service.UserRolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// http://localhost:8087/api/auth
@RestController
@RequestMapping(path = "api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AccountService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRolService userRolService;
    @Autowired
    private RolService rolService;

    //REgistrar usuario
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto dto){

        // primero verificamos si existe una cuenta con ese email
        Optional<UserEntity> existingUser = service.findByEmail(dto.getEmail());

        if (existingUser.isPresent()){
            return ResponseEntity.badRequest().body("Ya existe un usuario con ese email");
        }
        if (dto.getRol().isBlank()){
            Optional<RolEntity> lectorRol = rolService.findByName("LECTOR");
            if (lectorRol.isEmpty()){
                return ResponseEntity.notFound().build();
            }else{
                dto.setRol("LECTOR");
            }
        }
        Optional<RegisterDto> optional = service.createUser(dto);

        if(optional.isPresent()){
            return ResponseEntity.ok("Usuario registrado correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto){
        List<UserRolEntity> userRol = userRolService.findByUserFk(dto.getEmail());

        if (userRol.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario sin roles");
        }

        Integer rolId = userRol.get(0).getRolFk();
        Optional<RolEntity> rolUser = rolService.findById(rolId);
        if (rolUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(login);
        log.info("Auth principal: {}", authentication.getPrincipal());
        log.info("Authorities: {}", authentication.getAuthorities().stream().toList().toString());
        String jwt = jwtUtil.create(dto.getEmail(),rolUser.get().getName());
        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);
        map.put("rol", rolUser.get().getName());
        return ResponseEntity.ok(map);
    }


}

