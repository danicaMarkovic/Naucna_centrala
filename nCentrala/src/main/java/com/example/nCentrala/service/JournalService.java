package com.example.nCentrala.service;

import com.example.nCentrala.model.Journal;

public interface JournalService {

	Journal saveJournal(Journal journal);
	
	Journal getLastAddedJournal();
	
	Journal getJournalByIssn(String issn);
	
	void deleteJournal(Journal journal);
}
