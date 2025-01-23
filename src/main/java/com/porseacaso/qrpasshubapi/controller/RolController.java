package com.porseacaso.qrpasshubapi.controller;

import com.porseacaso.qrpasshubapi.dto.RolDTO;
import com.porseacaso.qrpasshubapi.model.entity.Rol;
import com.porseacaso.qrpasshubapi.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDTO>> getAllRoles(){
        List<RolDTO> roles = rolService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<RolDTO>> getAllRolesPaginate(
            @PageableDefault(size = 10) Pageable pageable){

        Page<RolDTO> roles = rolService.paginate(pageable);
        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRolById(@PathVariable Integer id){
        RolDTO rol = rolService.getById(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RolDTO> createRol(@Valid @RequestBody RolDTO rolDTO){
        RolDTO newRol = rolService.create(rolDTO);
        return new ResponseEntity<>(newRol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> updateRol(@PathVariable Integer id, @Valid @RequestBody RolDTO rolDTO){
        RolDTO rolUpdated = rolService.update(id, rolDTO);
        return new ResponseEntity<>(rolUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRol(@PathVariable Integer id){
        rolService.delete(id);
        return new ResponseEntity<>("Rol deleted", HttpStatus.OK);
    }
}
