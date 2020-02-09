package com.example.nCentrala.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.repository.JournalRepository;
import com.example.nCentrala.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {
	
	@Autowired
	private JournalRepository journalRepository;

	@Override
	public Journal saveJournal(Journal journal) {
		// TODO Auto-generated method stub
		return journalRepository.save(journal);
	}

	@Override
	public Journal getLastAddedJournal() {
		// TODO Auto-generated method stub
		return journalRepository.findTopByOrderByIdDesc();
	}

	@Override
	public Journal getJournalByIssn(String issn) {
		// TODO Auto-generated method stub
		return journalRepository.findByIssn(issn);
	}

	@Override
	public void deleteJournal(Journal journal) {
		// TODO Auto-generated method stub
		journalRepository.delete(journal);
	}

	@Override
	public List<Journal> activeJournals() {
		// TODO Auto-generated method stub
		return journalRepository.findAllByIsActivated(true);
	}

	@Override
	public Journal getJournalByName(String name) {
		// TODO Auto-generated method stub
		return journalRepository.findByName(name);
	}

}
