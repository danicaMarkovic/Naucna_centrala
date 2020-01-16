package com.example.nCentrala.serviceImpl;

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
		System.out.println("velicina " + journalRepository.findByIssn(issn).getName());
		return journalRepository.findByIssn(issn);
	}

	@Override
	public void deleteJournal(Journal journal) {
		// TODO Auto-generated method stub
		journalRepository.delete(journal);
	}

}
