package com.soil.mositure.IOT.repository;

import com.soil.mositure.IOT.model.MoistureDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoistureDataRepository extends JpaRepository<MoistureDataModel,Integer> {
}
