package stusyo222b.webappsspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stusyo222b.webappsspringproject.entities.OfficeWorker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeWorkerRepository extends JpaRepository<OfficeWorker, Long> {
    OfficeWorker getOfficeWorkerBySurname(String officeWorkerSurname);
    boolean existsByWorkerCod(String workerCod);
    OfficeWorker findByWorkerCod(String workerCod);
}
