package com.example.AssetManagementSystem.controller;


import com.example.AssetManagementSystem.entity.Asset;
import com.example.AssetManagementSystem.entity.AssignmentStatus;
import com.example.AssetManagementSystem.exception.AssetManagementException;
import com.example.AssetManagementSystem.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Asset> assets = assetService.getAllAssets(page, size);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/id/{assetId}")
    public Asset getAssetById(@PathVariable Long assetId) {
        return assetService.getAssetById(assetId);
    }

    @GetMapping("/name/{assetName}")
    public Asset getAssetByName(@PathVariable String assetName) {
        return assetService.getAssetByName(assetName);
    }

    @PostMapping
    public ResponseEntity<?> createAsset(@RequestBody Asset asset) {
        try {
            Asset createdAsset = assetService.createAsset(asset);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
        } catch (AssetManagementException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }



    @PutMapping("/{assetId}/assign")
    public ResponseEntity<Void> assignAsset(
            @PathVariable Long assetId,
            @RequestParam AssignmentStatus assignmentStatus
            ) {
        assetService.assignAsset(assetId, assignmentStatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assetId}/recover")
    public ResponseEntity<Void> recoverAsset(@PathVariable Long assetId) {
        assetService.recoverAsset(assetId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assetId}")
    public ResponseEntity<Asset> updateAsset(
            @PathVariable Long assetId,
            @RequestBody Asset updatedAsset
    ) {
        Asset asset = assetService.updateAsset(assetId, updatedAsset);
        return (asset != null) ? ResponseEntity.ok(asset) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long assetId) {
        assetService.deleteAsset(assetId);
        return ResponseEntity.noContent().build();
    }

}








