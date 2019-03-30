package com.navis.mines.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
@Slf4j
public class MinefieldRepositoryImpl
    implements MinefieldRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void truncate()
  {
    log.debug("Clearing the mine field");
    entityManager.createNativeQuery("truncate table navis.minefield")
                 .executeUpdate();
  }
}
