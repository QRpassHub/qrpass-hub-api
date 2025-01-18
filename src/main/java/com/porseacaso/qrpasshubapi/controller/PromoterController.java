package com.porseacaso.qrpasshubapi.controller;

import com.porseacaso.qrpasshubapi.dto.PromoterDTO;
import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import com.porseacaso.qrpasshubapi.service.PromoterService;
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
@RequestMapping("/promoters")
public class PromoterController {
    private final PromoterService promoterService;

    @GetMapping
    public ResponseEntity<List<PromoterDTO>> getAllPromoters() {
        List<PromoterDTO> promoters = promoterService.getAll();
        return new ResponseEntity<>(promoters, HttpStatus.OK);
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<PromoterDTO>> getAllPromotersPaginate(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PromoterDTO> promoters = promoterService.paginate(pageable);
        return new ResponseEntity<>(promoters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromoterDTO> getPromoterById(@PathVariable Integer id) {
        PromoterDTO promoter = promoterService.getById(id);
        return new ResponseEntity<>(promoter, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PromoterDTO> createPromoter(@Valid @RequestBody PromoterDTO promoterDTO){
        PromoterDTO newPromoter = promoterService.create(promoterDTO);
        return new ResponseEntity<>(newPromoter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromoterDTO> updatePromoter(@PathVariable Integer id, @Valid @RequestBody PromoterDTO promoterDTO){
        PromoterDTO promoterUpdated = promoterService.update(id, promoterDTO);
        return new ResponseEntity<>(promoterUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Promoter> deletePromoter(@PathVariable Integer id){
        promoterService.delete(id);
        return new ResponseEntity<Promoter>(HttpStatus.NO_CONTENT);
    }

}
