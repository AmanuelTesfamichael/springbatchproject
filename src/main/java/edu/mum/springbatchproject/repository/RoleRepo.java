package edu.mum.springbatchproject.repository;


import edu.mum.springbatchproject.domain.Role;
import edu.mum.springbatchproject.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role,Integer> {
    boolean existsRoleByType(RoleType type);
}
