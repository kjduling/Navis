package com.navis.mines.persistence;

import org.springframework.stereotype.Repository;

@Repository
public interface MinefieldRepositoryCustom
{
  void truncate();
}
