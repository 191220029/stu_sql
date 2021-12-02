package com.student.stu.Repository;

import com.student.stu.Entity.stu;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//继承JpaRepository来实现数据库的操作，Integer是该表的主键
public interface SRepository extends JpaRepository<stu,Integer> {
    //id 其它是count,第n个，返回的是第n个
    @Query(nativeQuery = true, value = "SELECT * FROM student.stu2 limit :id ,1")
    stu findCountI(@Param("id") int id);
    List<stu> findByName(@Param("name") String name);

    List<stu> findByGradeAndSclass(@Param("grade") String grade, @Param("sclass") String sclass);



    @Query(nativeQuery = true, value = "select count(id) from stu2")
    Integer getRCount();


}
