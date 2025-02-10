package stusyo222b.webappsspringproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import stusyo222b.webappsspringproject.entities.FFamilyState;
import stusyo222b.webappsspringproject.repository.FFamilyStateRepository;

@Service
public class FFamilyStateServiceImpl implements FFamilyStateService {
    private FFamilyStateRepository ffamilyStateRepository;

    public FFamilyStateServiceImpl(FFamilyStateRepository ffamilyStateRepository) {
        this.ffamilyStateRepository = ffamilyStateRepository;
    }

    @Override
    public List<FFamilyState> getAllFFamilyStates() {
        return ffamilyStateRepository.findAll();
    }

    @Override
    public FFamilyState getFFamilyStateByIdOfficeWorker(Long idOfficeWorker) {
        return ffamilyStateRepository.findFFamilyStateByWorker_Id(idOfficeWorker);
    }

    @Override
    public FFamilyState getFFamilyStateById(Long idFFamilyState) {
        return ffamilyStateRepository.findById(idFFamilyState).orElse(null);
    }

    @Override
    public String getInfoPresenceFFamilyStateByIDOfficeWorker(Long idOfficeWorker) {
        FFamilyState fp = ffamilyStateRepository.findFFamilyStateByWorker_Id(idOfficeWorker);
        return fp == null?"":"There is information about the office worker in DB!";
    }

    @Override
    public FFamilyState insertFFamilyState(FFamilyState ffamilyState) {
        return ffamilyStateRepository.save(ffamilyState);
    }


    @Override
    public FFamilyState updateFFamilyState(Long idEd, FFamilyState ffamilyState) {
        ffamilyState.setId(idEd);
        return ffamilyStateRepository.save(ffamilyState);
    }

    @Override
    public void deleteFFamilyStateById(Long id) {
        ffamilyStateRepository.deleteById(id);
    }


}
