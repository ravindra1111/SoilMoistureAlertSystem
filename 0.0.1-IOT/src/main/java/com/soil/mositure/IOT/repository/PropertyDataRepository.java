package com.soil.mositure.IOT.repository;

import com.soil.mositure.IOT.model.PropertyDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDataRepository extends JpaRepository<PropertyDataModel,Integer> {
}
