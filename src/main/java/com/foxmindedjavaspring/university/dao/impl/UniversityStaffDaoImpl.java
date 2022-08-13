package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.UniversityStaffDao;
import com.foxmindedjavaspring.university.model.UniversityStaff;

@Component
public class UniversityStaffDaoImpl implements UniversityStaffDao {
    private static final String ADD_UNIVERSITY_STAFF = "INSERT INTO university_staff VALUES(?, (SELECT id FROM persons WHERE first_name = ? AND last_name = ? AND address = ?), ?)";
    private static final String REMOVE_UNIVERSITY_STAFF = "DELETE FROM university_staff WHERE staff_id = ?";
    private static final String UPDATE_TITLE = "UPDATE university_staff SET title = ? WHERE staff_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityStaffDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(UniversityStaff universityStaff) {
        return jdbcTemplate.update(ADD_UNIVERSITY_STAFF,
                universityStaff.getStaffId(), universityStaff.getFirstName(),
                universityStaff.getLastName(), universityStaff.getAddress(),
                universityStaff.getTitle()) == 1;
    }

    @Override
    public boolean delete(UniversityStaff universityStaff) {
        return jdbcTemplate.update(REMOVE_UNIVERSITY_STAFF,
                universityStaff.getStaffId()) == 1;
    }

    @Override
    public boolean updateTitle(UniversityStaff universityStaff, String title) {
        return jdbcTemplate.update(UPDATE_TITLE, title,
                universityStaff.getStaffId()) == 1;
    }
}
