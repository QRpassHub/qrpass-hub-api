package com.porseacaso.qrpasshubapi.controller;

import com.porseacaso.qrpasshubapi.dto.CludgoerDTO;
import com.porseacaso.qrpasshubapi.model.entity.Cludgoer;
import com.porseacaso.qrpasshubapi.service.CludgoerService;
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
@RequestMapping("/cloudgoers")
public class CludgoerController {
    private final CludgoerService cludgoerService;

    @GetMapping
    public ResponseEntity<List<CludgoerDTO>> getAllCludgoers() {
        List<CludgoerDTO> cludgoers = cludgoerService.getAll();
        return new ResponseEntity<>(cludgoers, HttpStatus.OK);
    }


    @GetMapping("/paginate")
    public ResponseEntity<Page<CludgoerDTO>> getAllCludgoersPaginate(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CludgoerDTO> cludgoers = cludgoerService.paginate(pageable);
        return new ResponseEntity<>(cludgoers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CludgoerDTO> getCludgoerById(@PathVariable Integer id) {
        CludgoerDTO cludgoer = cludgoerService.getById(id);
        return new ResponseEntity<>(cludgoer, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CludgoerDTO> createCludgoer(@Valid @RequestBody CludgoerDTO cludgoerDTO){
        CludgoerDTO newCludgoer = cludgoerService.create(cludgoerDTO);
        return new ResponseEntity<>(newCludgoer, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CludgoerDTO> updateCludgoer(@PathVariable Integer id, @Valid @RequestBody CludgoerDTO cludgoerDTO){
        CludgoerDTO cludgoerUpdated = cludgoerService.update(id, cludgoerDTO);
        return new ResponseEntity<>(cludgoerUpdated, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Cludgoer> deleteCludgoer(@PathVariable Integer id){
        cludgoerService.delete(id);
        return new ResponseEntity<Cludgoer>(HttpStatus.NO_CONTENT);
    }
}
