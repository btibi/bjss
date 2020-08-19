package com.bjss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjss.domain.Cv;

public interface CvRepository extends JpaRepository<Cv, Long> {
}
