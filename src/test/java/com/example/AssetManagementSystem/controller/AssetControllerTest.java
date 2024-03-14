package com.example.AssetManagementSystem.controller;


import com.example.AssetManagementSystem.entity.Asset;
import com.example.AssetManagementSystem.entity.AssignmentStatus;
import com.example.AssetManagementSystem.entity.Category;
import com.example.AssetManagementSystem.exception.AssetManagementException;
import com.example.AssetManagementSystem.service.AssetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    @Before
    public void setUp() {

        Category category = new Category();
        category.setId(1L);
        category.setName("Category 1");

        Asset asset1 = new Asset();
        asset1.setId(1L);
        asset1.setName("Asset 1");
        asset1.setCategory(category);
        asset1.setPurchaseDate("2023-01-01");
        asset1.setConditionNotes("Good condition");
        asset1.setAssignmentStatus(AssignmentStatus.ASSIGNED);

        Asset asset2 = new Asset();
        asset2.setId(2L);
        asset2.setName("Asset 2");
        asset2.setCategory(category);
        asset2.setPurchaseDate("2023-01-02");
        asset2.setConditionNotes("Fair condition");
        asset2.setAssignmentStatus(AssignmentStatus.AVAILABLE);


        when(assetService.getAllAssets(0, 10)).thenReturn(Collections.singletonList(asset1));
        when(assetService.getAssetById(1L)).thenReturn(asset1);
    }

    @Test
    public void testGetAllAssets() {

        List<Asset> mockAssets = Collections.singletonList(new Asset());
        when(assetService.getAllAssets(0, 10)).thenReturn(mockAssets);


        ResponseEntity<List<Asset>> response = assetController.getAllAssets(0, 10);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAssets, response.getBody());
    }

    @Test
    public void testGetAssetById() {
        Asset response = assetController.getAssetById(1L);

//        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response);
        assertEquals(1L, response.getId().longValue());
        assertEquals("Asset 1", response.getName());
    }


    @Test
    public void testCreateAsset() throws AssetManagementException {
        Asset assetToCreate = new Asset();
        assetToCreate.setName("New Asset");
        assetToCreate.setCategory(new Category());
        assetToCreate.setPurchaseDate("2024-01-01");
        assetToCreate.setConditionNotes("New condition");
        assetToCreate.setAssignmentStatus(AssignmentStatus.AVAILABLE);

        when(assetService.createAsset(assetToCreate)).thenReturn(assetToCreate);

        ResponseEntity<?> response = assetController.createAsset(assetToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateAsset() {
        Asset updatedAsset = new Asset();
        updatedAsset.setId(1L);
        updatedAsset.setName("Updated Asset");

        when(assetService.updateAsset(1L, updatedAsset)).thenReturn(updatedAsset);

        ResponseEntity<Asset> response = assetController.updateAsset(1L, updatedAsset);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Asset", response.getBody().getName());
    }

    @Test
    public void testDeleteAsset() {
        ResponseEntity<Void> response = assetController.deleteAsset(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(assetService).deleteAsset(1L);
    }
}
