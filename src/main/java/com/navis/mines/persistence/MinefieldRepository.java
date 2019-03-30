package com.navis.mines.persistence;

import com.navis.mines.model.Mine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinefieldRepository
    extends JpaRepository<Mine, Long>,
            MinefieldRepositoryCustom
{}
