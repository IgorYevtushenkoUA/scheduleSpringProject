package repositories;

import com.example.faculty.database.entity.Atendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtendeeRepository extends JpaRepository<Atendee, UUID> {
}
