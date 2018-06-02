package com.JMS.repository;

import com.JMS.model.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Elvira on 02.06.2018.
 */
@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
}
