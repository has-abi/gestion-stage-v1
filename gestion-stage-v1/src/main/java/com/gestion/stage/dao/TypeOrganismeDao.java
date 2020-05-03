package com.gestion.stage.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeOrganisme;

@Repository
public interface TypeOrganismeDao extends JpaRepository<TypeOrganisme, Long>{


}
