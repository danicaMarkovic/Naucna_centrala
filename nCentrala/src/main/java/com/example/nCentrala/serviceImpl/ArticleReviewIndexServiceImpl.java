package com.example.nCentrala.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Review;
import com.example.nCentrala.modelElastic.ArticleReviewIndex;
import com.example.nCentrala.repository.ArticleReviewIndexRepository;
import com.example.nCentrala.service.ArticleReviewIndexService;

@Service
public class ArticleReviewIndexServiceImpl implements ArticleReviewIndexService {
	
	private final Path fileLocation = Paths.get("files")
		    .toAbsolutePath().normalize();
	
	@Autowired
	private ArticleReviewIndexRepository indexRepository;

	@Override
	public Iterable<ArticleReviewIndex> findAllReviewes() {
		// TODO Auto-generated method stub
		return indexRepository.findAll();
	}

	@Override
	public boolean addToIndex(Review review) {
		// TODO Auto-generated method stub
		ArticleReviewIndex index = new ArticleReviewIndex();
		
		try {
			Resource resource = this.loadFile(review.getArticle().getPdfPath());
			PDFParser parser = new PDFParser(new RandomAccessFile(resource.getFile(), "r"));
			parser.parse();
			PDDocument pdf = parser.getPDDocument();
			index.setContent(this.getTextFromPdfDocument(pdf));
			
			index.setId(review.getId());
			index.setJournalId(review.getArticle().getJournal().getId());
			index.setReviewerId(review.getUser().getId());
			index.setArticleTitle(review.getArticle().getTitle());
			if(review.getArticle().getAccepted().name().equals("ACCEPTED"))
			{
				index.setAccepted(true);
			}else
			{
				index.setAccepted(false);
			}
			
			pdf.close();
			
			ArticleReviewIndex ret = indexRepository.index(index);
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
