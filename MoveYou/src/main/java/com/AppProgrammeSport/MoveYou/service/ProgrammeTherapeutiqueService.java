package com.AppProgrammeSport.MoveYou.service;

import java.util.List;

import com.AppProgrammeSport.MoveYou.entity.ProgrammeTherapeutique;

public interface ProgrammeTherapeutiqueService {
	public ProgrammeTherapeutique saveProgrammeTherapeutique(ProgrammeTherapeutique prog);
	
	public List<ProgrammeTherapeutique> saveMultipleProgrammeTherapeutique(List<ProgrammeTherapeutique> progs);

	public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueList();

	public ProgrammeTherapeutique fetchProgrammeTherapeutiqueById(Long therapeutiqueId);

	public void deleteProgrammeTherapeutiqueById(Long therapeutiqueId);

	public ProgrammeTherapeutique updateProgrammeTherapeutique(ProgrammeTherapeutique prog);
	
	public ProgrammeTherapeutique updateProgrammeTherapeutiqueName(ProgrammeTherapeutique prog);

	public ProgrammeTherapeutique fetchProgrammeTherapeutiqueByNom(String nom);
	
	public List<ProgrammeTherapeutique> fetchProgrammeTherapeutiqueByutilisateurId(Long userId);
	
	public Integer ajoutActiviteToProgrammeTherapeutique(Long progId, Long activityId);
	
    public boolean isActiviteRelatedToProgramme(Long progId, Long activiteId);

	public Double fetchProgrammeTherapeutiqueNote(Long progId, Long userId);
}
