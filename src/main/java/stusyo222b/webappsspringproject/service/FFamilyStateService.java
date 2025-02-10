package stusyo222b.webappsspringproject.service;

import stusyo222b.webappsspringproject.entities.FFamilyState;

import java.util.List;

public interface FFamilyStateService {

        List<FFamilyState> getAllFFamilyStates();

        FFamilyState getFFamilyStateByIdOfficeWorker(Long idOfficeWorker);

        FFamilyState getFFamilyStateById(Long idFFamilyState);

        String getInfoPresenceFFamilyStateByIDOfficeWorker(Long idOfficeWorker);

        FFamilyState insertFFamilyState(FFamilyState ffamilyState);

        FFamilyState updateFFamilyState(Long idEd, FFamilyState ffamilyState) ;

        void deleteFFamilyStateById(Long id);

    }
