package com.vradiuse.dbManager.repo;

import com.vradiuse.dbManager.database.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Integer> {
}
