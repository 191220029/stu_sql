package com.student.stu.Repository;

import com.student.stu.Entity.stu;
import com.student.stu.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface URepository extends JpaRepository<user,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM student.user ")
    //user findCountI(@Param("id") int id);
    user findByName(@Param("username") String username);

}
