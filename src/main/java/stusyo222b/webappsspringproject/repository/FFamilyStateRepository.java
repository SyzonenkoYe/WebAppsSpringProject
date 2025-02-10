package stusyo222b.webappsspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stusyo222b.webappsspringproject.entities.FFamilyState;
import org.springframework.stereotype.Repository;
import stusyo222b.webappsspringproject.enums.OfficeWorkerFamilyState;

@Repository
public interface FFamilyStateRepository extends JpaRepository<FFamilyState, Long> {
    FFamilyState findFFamilyStateByWorker_Id(Long idOfficeWorker);
    FFamilyState findFamilyStateById(Long idFamilyState);

}
