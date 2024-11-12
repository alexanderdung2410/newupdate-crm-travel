package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.MealsPackage;

@Repository
public interface MealsPackageRepository extends JpaRepository<MealsPackage, Long> {

}
