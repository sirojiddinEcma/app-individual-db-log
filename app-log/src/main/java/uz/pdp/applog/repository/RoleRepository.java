package uz.pdp.applog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.applog.entity.Role;
import uz.pdp.applog.entity.enums.RoleName;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByRoleName(RoleName roleName);
}
