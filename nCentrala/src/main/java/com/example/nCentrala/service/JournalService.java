package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.Journal;

public interface JournalService {

	Journal saveJournal(Journal journal);
	
	Journal getLastAddedJournal();
	
	Journal getJournalByIssn(String issn);
	
	Journal getJournalByName(String name);
	
	void deleteJournal(Journal journal);
	
	List<Journal> activeJournals();
}
