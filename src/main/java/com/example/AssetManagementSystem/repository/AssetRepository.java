package com.example.AssetManagementSystem.repository;

import com.example.AssetManagementSystem.entity.Asset;
import com.example.AssetManagementSystem.entity.AssignmentStatus;
import com.example.AssetManagementSystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByName(String name);

    List<Asset> findByAssignmentStatus(AssignmentStatus assignmentStatus);

    List<Asset> findByCategory(Category category);
}
