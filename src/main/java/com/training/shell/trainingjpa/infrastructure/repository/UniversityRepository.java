package com.training.shell.trainingjpa.infrastructure.repository;

import com.training.shell.trainingjpa.infrastructure.model.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {

}
