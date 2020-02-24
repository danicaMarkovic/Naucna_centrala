package com.example.nCentrala.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.dto.ArticleIndexDTO;
import com.example.nCentrala.modelElastic.AcceptedArticleIndex;
import com.example.nCentrala.modelElastic.RejectedArticleIndex;
import com.example.nCentrala.repository.ArticleIndexRepository;
import com.example.nCentrala.service.ArticleIndexService;

@Service
public class ArticleIndexServiceImpl implements ArticleIndexService {
	
	@Autowired
	private ArticleIndexRepository articleRep;
	
	private final Path fileLocation = Paths.get("files")
    .toAbsolutePath().normalize();

	@Override
	public Iterable<AcceptedArticleIndex> findAll() {
		// TODO Auto-generated method stub
		return articleRep.findAll();
	}

	@Override
	public boolean addArticle(AcceptedArticleIndex article) {
		// TODO Auto-generated method stub
		AcceptedArticleIndex ret = articleRep.index(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public boolean updateArticle(AcceptedArticleIndex article) {
		// TODO Auto-generated method stub
		AcceptedArticleIndex ret  = articleRep.save(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public String getTextFromPdfDocument(PDDocument document) {
		// TODO Auto-generated method stub
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(document);
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri preuzimanju teksta iz pdf-a");
		}
		return null;
	}

	@Override
	public boolean addDataToIndex(Article article, String coauthors, String author) {
		// TODO Auto-generated method stub
		AcceptedArticleIndex articleIndex = new AcceptedArticleIndex();
		
		try {
			
			Resource resource = this.loadFile(article.getPdfPath());
			
			articleIndex.setId(article.getId());
			articleIndex.setApstract(article.getApstract());
			articleIndex.setJournalName(article.getJournal().getName());
			articleIndex.setJournalId(article.getJournal().getId());
			articleIndex.setKeywords(article.getKeyWords());
			articleIndex.setOpenAccess(article.getJournal().isOpenAccess());
			articleIndex.setScienceArea(article.getScienceArea().getName());
			articleIndex.setTitle(article.getTitle());
			articleIndex.setAccepted(true);
			articleIndex.setCoauthors(coauthors);
			articleIndex.setAuthor(author);
			articleIndex.setPdfPath(article.getPdfPath());
			
			PDFParser parser = new PDFParser(new RandomAccessFile(resource.getFile(), "r"));
			parser.parse();
			
			PDDocument pdf = parser.getPDDocument();
		//	PDDocumentInformation pdfInfo = pdf.getDocumentInformation();
			
			articleIndex.setText(this.getTextFromPdfDocument(pdf));
			
			pdf.close();
			
			AcceptedArticleIndex ret = articleRep.index(articleIndex);
			if(ret != null)
			{
				
				return true;
			}else
			{
				return false;
			}
			
		}catch(IOException e)
		{
			System.out.println("Greskaaa");
		}
		
		return false;
	}
	
	@Override
	public Optional<AcceptedArticleIndex> getById(Long id) {
		// TODO Auto-generated method stub
		return articleRep.findById(id);
	}
	
	public Resource loadFile(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }
            
        } catch (MalformedURLException ex) {
           
        	System.out.println("Nema fajlaaaa");
        	
        }
        
        return null;
    }
	

}
