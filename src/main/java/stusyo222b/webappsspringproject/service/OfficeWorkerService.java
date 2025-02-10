package stusyo222b.webappsspringproject.service;

import stusyo222b.webappsspringproject.entities.OfficeWorker;

import java.util.List;

public interface OfficeWorkerService {

    List<OfficeWorker> getAllOfficeWorkers();

    OfficeWorker getOfficeWorkerByKeySet(String officeWorkerName);

    OfficeWorker getOfficeWorkerById(Long id);

    OfficeWorker getOfficeWorkerByWorkerCod(String workerCod);

    OfficeWorker saveOfficeWorker(OfficeWorker officeworker);

    OfficeWorker updateOfficeWorker(Long id, OfficeWorker officeworker);

    void deleteOfficeWorkerById(Long id);

}