package stusyo222b.webappsspringproject.service;

import org.springframework.stereotype.Service;
import stusyo222b.webappsspringproject.entities.OfficeWorker;
import stusyo222b.webappsspringproject.repository.OfficeWorkerRepository;

import java.util.List;

@Service
public class OfficeWorkerServiceImpl implements OfficeWorkerService {

    private final OfficeWorkerRepository officeWorkerRepository;

    public OfficeWorkerServiceImpl(OfficeWorkerRepository officeWorkerRepository) {
        this.officeWorkerRepository = officeWorkerRepository;
    }

    // Implementation of methods in OfficeWorkerService

    @Override
    public List<OfficeWorker> getAllOfficeWorkers() {
        return officeWorkerRepository.findAll();
    }

    @Override
    public OfficeWorker getOfficeWorkerById(Long id) {
        return officeWorkerRepository.findById(id).orElse(null);
    }

    @Override
    public OfficeWorker getOfficeWorkerByKeySet(String officeWorkerSurname) {
        return officeWorkerRepository.getOfficeWorkerBySurname(officeWorkerSurname);
    }

    @Override
    public OfficeWorker getOfficeWorkerByWorkerCod(String workerCod) {
        return officeWorkerRepository.findByWorkerCod(workerCod);
    }


    @Override
    public OfficeWorker saveOfficeWorker(OfficeWorker officeworker) {
        return officeWorkerRepository.save(officeworker);
    }

    @Override
    public OfficeWorker updateOfficeWorker(Long id, OfficeWorker officeworker) {
        OfficeWorker officeWorkerToUpdateInDB = officeWorkerRepository.findById(id).orElse(null);
        if (officeWorkerToUpdateInDB != null) {
            officeworker.setId(id);
            return officeWorkerRepository.save(officeworker);
        }
        return null;

    }

    @Override
    public void deleteOfficeWorkerById(Long id) {
        officeWorkerRepository.deleteById(id);
    }

}



