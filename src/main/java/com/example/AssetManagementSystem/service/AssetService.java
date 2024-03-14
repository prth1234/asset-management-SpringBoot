package com.example.AssetManagementSystem.service;


import com.example.AssetManagementSystem.entity.Asset;
import com.example.AssetManagementSystem.entity.AssignmentStatus;
import com.example.AssetManagementSystem.exception.AssetManagementException;
import com.example.AssetManagementSystem.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Asset> assetPage = assetRepository.findAll(pageable);
        return assetPage.getContent();
    }

    public Asset getAssetById(Long assetId) {
        Optional<Asset> assetOptional = assetRepository.findById(assetId);
        return assetOptional.orElseThrow(() -> new AssetManagementException("Asset not found with ID: " + assetId));
    }

    public Asset getAssetByName(String assetName) {
        Optional<Asset> assetOptional = assetRepository.findByName(assetName);
        return assetOptional.orElseThrow(() -> new AssetManagementException("Asset not found with Name: " + assetName));
    }
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long assetId, Asset updatedAsset) {
        Optional<Asset> existingAsset = assetRepository.findById(assetId);
        if (existingAsset.isEmpty()) {
            throw new AssetManagementException("Asset not found with ID: " + assetId);
        }

        Asset asset = existingAsset.get();
        asset.setName(updatedAsset.getName());
        asset.setPurchaseDate(updatedAsset.getPurchaseDate());
        asset.setConditionNotes(updatedAsset.getConditionNotes());
        asset.setCategory(updatedAsset.getCategory());
        asset.setAssignmentStatus(updatedAsset.getAssignmentStatus());
        return assetRepository.save(asset);
    }

    public void assignAsset(Long assetId, AssignmentStatus assignmentStatus) {
        Optional<Asset> existingAsset = assetRepository.findById(assetId);

        if (existingAsset.isPresent()) {
            Asset asset = existingAsset.get();
            if (asset.getAssignmentStatus() == AssignmentStatus.AVAILABLE || asset.getAssignmentStatus() == AssignmentStatus.RECOVERED) {
                asset.setAssignmentStatus(assignmentStatus);
                assetRepository.save(asset);
            }
            else {
                throw new AssetManagementException("Failed to assign asset with ID: " + assetId);
            }
        }
        else {
//            System.out.println("Exist hi ni krta bhai");
            throw new AssetManagementException("Asset not found with ID: " + assetId);
        }
    }

    public void recoverAsset(Long assetId) {
        Optional<Asset> existingAsset = assetRepository.findById(assetId);
        if (existingAsset.isPresent()) {
            Asset asset = existingAsset.get();
            if (asset.getAssignmentStatus() == AssignmentStatus.AVAILABLE) {
                throw new AssetManagementException("Asset with ID: " + assetId + " is already set to AVAILABLE");
            }
            else if (asset.getAssignmentStatus() == AssignmentStatus.RECOVERED) {
                throw new AssetManagementException("Asset with ID: " + assetId + " is already set to RECOVERED");
            }
            else {
                asset.setAssignmentStatus(AssignmentStatus.RECOVERED);
                assetRepository.save(asset);
            }

        }
        else {
//            System.out.println("Exist hi ni krta bhai");
            throw new AssetManagementException("Asset not found with ID: " + assetId);
        }
    }

    public void deleteAsset(Long assetId) {
        assetRepository.deleteById(assetId);
    }
}
