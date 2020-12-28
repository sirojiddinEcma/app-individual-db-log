package uz.pdp.applog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.applog.entity.Payment;

import java.util.UUID;

@RepositoryRestResource(path = "payment")
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
