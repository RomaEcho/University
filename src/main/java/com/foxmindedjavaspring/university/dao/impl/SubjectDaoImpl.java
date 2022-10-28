package com.foxmindedjavaspring.university.dao.impl;

import java.util.List;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.AbstractGenericDao;
import com.foxmindedjavaspring.university.model.Subject;

@Repository
public class SubjectDaoImpl extends AbstractGenericDao<Subject>
        implements SubjectDao {
    static final String FIND_BY_NAME = "SELECT s FROM Subject s WHERE s.name LIKE :name";
    private static final Logger LOG = LoggerFactory.getLogger(
                SubjectDaoImpl.class);
    
    public SubjectDaoImpl() {
      super(Subject.class);
    }

    @Override
    public List<Subject> findByName(String name) {
        LOG.debug("Trying to find the subjects with name containing: {} using the following SQL: {}", 
                    name, FIND_BY_NAME);
        return getEntityManager().createQuery(FIND_BY_NAME, Subject.class)
                                 .setParameter("name", name)
                                 .getResultList();
   }
}
