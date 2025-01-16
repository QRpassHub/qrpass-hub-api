package com.porseacaso.qrpasshubapi.controller;

import com.porseacaso.qrpasshubapi.model.entity.Promoter;
import com.porseacaso.qrpasshubapi.service.PromoterService;
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
    public ResponseEntity<List<Promoter>> getAllPromoters() {
        List<Promoter> promoters = promoterService.getAll();
        return new ResponseEntity<List<Promoter>>(promoters, HttpStatus.OK);
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Promoter>> getAllPromotersPaginate(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Promoter> promoters = promoterService.paginate(pageable);
        return new ResponseEntity<Page<Promoter>>(promoters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promoter> getPromoterById(@PathVariable Integer id) {
        Promoter promoter = promoterService.getById(id);
        return new ResponseEntity<Promoter>(promoter, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Promoter> createPromoter(@RequestBody Promoter promoter){
        Promoter promoterCreated = promoterService.create(promoter);
        return new ResponseEntity<Promoter>(promoterCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promoter> updatePromoter(@PathVariable Integer id, @RequestBody Promoter promoter){
        Promoter promoterUpdated = promoterService.update(id, promoter);
        return new ResponseEntity<Promoter>(promoterUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Promoter> deletePromoter(@PathVariable Integer id){
        promoterService.delete(id);
        return new ResponseEntity<Promoter>(HttpStatus.NO_CONTENT);
    }

}
