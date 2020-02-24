package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.Query;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.model.dto.AdvancedSearchDataDTO;
import com.example.nCentrala.model.dto.AdvancedSearchQuery;
import com.example.nCentrala.model.dto.BasicQueryResult;
import com.example.nCentrala.model.dto.BasicSearchQuery;
import com.example.nCentrala.model.dto.ReviewerSearchDataDTO;
import com.example.nCentrala.model.dto.SearchResultDTO;
import com.example.nCentrala.modelElastic.AcceptedArticleIndex;
import com.example.nCentrala.modelElastic.ArticleReviewIndex;
import com.example.nCentrala.modelElastic.RejectedArticleIndex;
import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.service.ArticleIndexService;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.CoauthorService;
import com.example.nCentrala.service.ReviewerIndexService;
import com.google.gson.Gson;

@RestController
@RequestMapping("search")
@CrossOrigin(origins = "http://localhost:1337")
public class SearchController {
	
	@Autowired
    private Client nodeClient;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CoauthorService coauthorService;
	
	@Autowired
	private ArticleIndexService acceptedIndexService;
	
	@Autowired
	private ReviewerIndexService reviewerIndexService;
	
	private HighlightBuilder highlightBuilder = new HighlightBuilder()
            .field("text", 50)
            .field("title",50)
            .field("coauthors", 50)
            .field("author", 50)
            .field("keywords", 50)
            .field("journalName", 50)
            .field("scienceArea", 50);
	
	private HighlightBuilder highlightBuilderAdvanced = new HighlightBuilder()
            .field("text", 50)
            .field("title",50)
            .field("coauthors", 50)
            .field("author", 50)
            .field("keywords", 50)
            .field("journalName", 50)
            .field("scienceArea", 50);

	@PostMapping(value="/basicSearch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BasicQueryResult>> basicSearch(@RequestBody BasicSearchQuery query) {
		
		List<BasicQueryResult> ret = new ArrayList<BasicQueryResult>();
		
		if(query.isPhrase())
		{
			ret = this.phraseQuerySearch(query);
			
		}else //obican upit
		{
			QueryStringQueryBuilder queryBuilder = null;
			
			if(query.getField().equals("author"))
			{
				queryBuilder = QueryBuilders.queryStringQuery(query.getValue());
				queryBuilder.field(query.getField());
				queryBuilder.field("coauthors");
				queryBuilder.analyzer("serbian");
			
			}else if(query.getField().equals("All fields"))
			{
				queryBuilder = QueryBuilders.queryStringQuery(query.getValue());
				queryBuilder.field("text");
				queryBuilder.field("title");
				queryBuilder.field("coauthors");
				queryBuilder.field("author");
				queryBuilder.field("keywords");
				queryBuilder.field("journalName");
				queryBuilder.field("scienceArea");
				queryBuilder.analyzer("serbian");
			}
			else
			{
				queryBuilder = QueryBuilders.queryStringQuery(query.getValue());
				queryBuilder.field(query.getField());
				queryBuilder.analyzer("serbian");
			}
			
			
			highlightBuilder.highlightQuery(QueryBuilders.queryStringQuery(query.getValue()));
			
			SearchRequestBuilder request = nodeClient.prepareSearch("articleindex")
	                .setQuery(queryBuilder)
	                .setSearchType(SearchType.DEFAULT)
	                .highlighter(highlightBuilder)
	                .setSize(100);
			
			SearchResponse response = request.get();
	        
	        ret = this.getDataFromResponse(response);
		}
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/advancedSearch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BasicQueryResult>> advancedSearch(@RequestBody AdvancedSearchDataDTO advancedData)
	{
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		
		QueryBuilder qfO = null; 
		
		if(advancedData.getFirstOption().getField().equals("author"))
		{
			qfO	= QueryBuilders.multiMatchQuery(advancedData.getFirstOption().getValue(), "author", "coauthors");
			
		}else if(advancedData.getFirstOption().getField().equals("All fields"))
		{
			qfO	= QueryBuilders.multiMatchQuery(advancedData.getFirstOption().getValue(), "author", "coauthors", "title", "text", "keywords", "scienceArea", "apstract",
					"journalName");
		}else //obicno
		{
			qfO = QueryBuilders.matchQuery(advancedData.getFirstOption().getField(), advancedData.getFirstOption().getValue());
		}
		
		qb.must(qfO);
		
		for(AdvancedSearchQuery aQuery : advancedData.getOtherOptions())
		{
			
			if(aQuery.getOperator().equals("AND"))
			{
								
				if(aQuery.isPhrase())
				{
					qb.must(QueryBuilders.matchPhraseQuery(aQuery.getField(), aQuery.getValue()));
					
				}else
				{
					qb.must(QueryBuilders.matchQuery(aQuery.getField(), aQuery.getValue()));
					
				}
				
			}else //OR
			{
				if(aQuery.isPhrase())
				{
					qb.should(QueryBuilders.matchPhraseQuery(aQuery.getField(), aQuery.getValue()));
					
				}else
				{
					qb.should(QueryBuilders.matchQuery(aQuery.getField(), aQuery.getValue()));
					
				}
			}
		}
		
		System.out.println("Upit");
		System.out.println(qb.toString());
		
		highlightBuilderAdvanced.highlightQuery(QueryBuilders.matchQuery(advancedData.getFirstOption().getField(), advancedData.getFirstOption().getValue()));
		
		SearchRequestBuilder request = nodeClient.prepareSearch("articleindex")
                .setQuery(qb)
                .setSearchType(SearchType.DEFAULT)
                .highlighter(highlightBuilder);
        SearchResponse response = request.get();
        
        List<BasicQueryResult> ret = this.getDataFromResponse(response);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value="geoSearch/{id}")
	public ResponseEntity<List<ReviewerSearchDataDTO>> geoSearchFilter(@PathVariable("id") Long articleId){
		
		Article article = articleService.getById(articleId);
		
		List<Coauthor> coauthors = coauthorService.getAllByArticle(articleId);
		
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		
		List<ReviewerSearchDataDTO> retList = new ArrayList<ReviewerSearchDataDTO>();
		
		//autor
		GeoDistanceQueryBuilder gdqb1 = new GeoDistanceQueryBuilder("revLocation");
		gdqb1.distance(100.00, DistanceUnit.KILOMETERS);
		gdqb1.point(article.getAuthor().getLat(), article.getAuthor().getLongit());
		bqb.mustNot(gdqb1);
		
		for(Coauthor c : coauthors) //koautori
		{
			GeoDistanceQueryBuilder gdqb = new GeoDistanceQueryBuilder("revLocation");
			gdqb.distance(100.00, DistanceUnit.KILOMETERS);
			gdqb.point(c.getLatt(), c.getLongit());
			bqb.mustNot(gdqb);
		}
		
		SearchRequestBuilder request = nodeClient.prepareSearch("reviewerindex")
                .setQuery(bqb)
                .setSearchType(SearchType.DEFAULT);
        
		SearchResponse response = request.get();
        
        for(SearchHit hit : response.getHits().getHits()) {
        	Gson gson = new Gson();
        	ReviewerIndex index = gson.fromJson(hit.getSourceAsString(), ReviewerIndex.class);
        	
        	String description = "Lokacija: " + index.getCity() + ", " + index.getState();
        	
        	ReviewerSearchDataDTO reviewer = new ReviewerSearchDataDTO(index.getId(), index.getName(), index.getSurname(), index.getCity(), index.getState(), index.getScienceAreas(), description);
        	retList.add(reviewer);
        }
		
        return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	@GetMapping(value="areaFilter/{id}")
	public ResponseEntity<List<ReviewerSearchDataDTO>> scienceAreaFilter(@PathVariable("id") Long articleId){
		
		Article article = articleService.getById(articleId);
		//System.out.println("Trazi se naucna oblast: " + article.getScienceArea().getName());
		
		List<ReviewerSearchDataDTO> retList = new ArrayList<ReviewerSearchDataDTO>();
		
		MatchPhraseQueryBuilder query = QueryBuilders.matchPhraseQuery("scienceAreas", article.getScienceArea().getName());
		query.analyzer("serbian");
		
		SearchRequestBuilder request = nodeClient.prepareSearch("reviewerindex")
                .setQuery(query)
                .setSearchType(SearchType.DEFAULT);
        
		SearchResponse response = request.get();
		
        for(SearchHit hit : response.getHits().getHits()) {
        	Gson gson = new Gson();
        	
        	ReviewerIndex index = gson.fromJson(hit.getSourceAsString(), ReviewerIndex.class);
        	
        	String description = "Zainteresovan za: " + index.getScienceAreas();
        	
        	ReviewerSearchDataDTO reviewer = new ReviewerSearchDataDTO(index.getId(), index.getName(), index.getSurname(), index.getCity(), index.getState(), index.getScienceAreas(), "");
        	retList.add(reviewer);
        }
        
        return new ResponseEntity<>(retList, HttpStatus.OK);
	}
	
	@GetMapping(value="moreLikeThis/{id}")
	public ResponseEntity<List<ReviewerSearchDataDTO>> moreLikeThisFilter(@PathVariable("id")Long articleId){
		
		Article article = articleService.getById(articleId);
		
		Optional<AcceptedArticleIndex> articleIndex = acceptedIndexService.getById(articleId);
		
		
		if(!articleIndex.isPresent())
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else
		{
			
			String fields[] = new String[1];
			String content[] = new String[1];
			
			content[0] = articleIndex.get().getText();
			fields[0] = "content";
			
			MoreLikeThisQueryBuilder mltquery = new MoreLikeThisQueryBuilder(fields, content ,null);
			mltquery.maxQueryTerms(70);
			mltquery.minTermFreq(1);
			mltquery.minimumShouldMatch("80%");
			mltquery.minDocFreq(1);
			mltquery.analyzer("serbian");
			
			BoolQueryBuilder bqb = QueryBuilders.boolQuery();
			bqb.must(mltquery);
			
			
			
			List<ReviewerSearchDataDTO> reviewers = new ArrayList<ReviewerSearchDataDTO>();
			
			SearchRequestBuilder request = nodeClient.prepareSearch("articlereviewindex")
	                .setQuery(bqb)
	                .setSearchType(SearchType.DEFAULT);
	        
			SearchResponse response = request.get();
	        

        	 for(SearchHit hit : response.getHits().getHits()) {
	        	Gson gson = new Gson();
	        	
	        	ArticleReviewIndex revIndex = gson.fromJson(hit.getSourceAsString(), ArticleReviewIndex.class);
	        	String status = "";
	        	if(revIndex.isAccepted())
	        	{
	        		status = "prihvacen";
	        	}else
	        	{
	        		status = "odbijen";
	        	}
	        	String description = "Recenzirao " + revIndex.getArticleTitle() + ", rad je " + status;
	        	
	        	ReviewerIndex reviewerIndex =  reviewerIndexService.getById(revIndex.getReviewerId()).get();
	        	
	        	ReviewerSearchDataDTO revDTO = new ReviewerSearchDataDTO(reviewerIndex.getId(), reviewerIndex.getName(), reviewerIndex.getSurname(), reviewerIndex.getCity(), reviewerIndex.getState(), reviewerIndex.getScienceAreas(), description);
	        	
	        	if(!reviewers.stream().map(ReviewerSearchDataDTO::getId).filter(revDTO.getId()::equals).findFirst().isPresent())
	        	{
	        		reviewers.add(revDTO);
	        	}
	        }
			
			return new ResponseEntity<>(reviewers, HttpStatus.OK);
		}
		
	}
	

	public List<BasicQueryResult> phraseQuerySearch(@RequestBody BasicSearchQuery query) {
		
		MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("text", query.getValue());
		queryBuilder.analyzer("serbian");
		
		highlightBuilder.highlightQuery(QueryBuilders.matchPhraseQuery("text", query.getValue()));
		
		SearchRequestBuilder request = nodeClient.prepareSearch("articleindex")
                .setQuery(queryBuilder)
                .setSearchType(SearchType.DEFAULT)
                .highlighter(highlightBuilder);
		
        SearchResponse response = request.get();
        
       List<BasicQueryResult> ret = this.getDataFromResponse(response);
        
        return ret;
	}
	
	private List<BasicQueryResult> getDataFromResponse(SearchResponse response){
		
		//System.out.println("Response: ");
        //System.out.println(response);
		
		List<BasicQueryResult> list = new ArrayList<BasicQueryResult>();
		
		for(SearchHit hit : response.getHits().getHits()) {
		
			Gson gson = new Gson();
			
			AcceptedArticleIndex article = gson.fromJson(hit.getSourceAsString(), AcceptedArticleIndex.class);
			BasicQueryResult result = new BasicQueryResult();
			result.setTitle(article.getTitle());
			result.setKeywords(article.getKeywords());
			result.setAuthors(article.getAuthor() + "," + article.getCoauthors());
			result.setOpenAccess(article.isOpenAccess());
			result.setPdfPath(article.getPdfPath());
			
			String allHighlights = "...";

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()){
            	
            	//System.out.println("Field za highlight: " + entry.toString());
            	
            	String value = Arrays.toString(entry.getValue().fragments());
             
            	allHighlights+=value.substring(1, value.length()-1);
                allHighlights+="...";

            }
            
            allHighlights = allHighlights.replace("<em>", "<b><i>");
            allHighlights = allHighlights.replace("</em>", "</i></b>");
         
            result.setHighlight(allHighlights);
            
            list.add(result);
		}
		
		return list;
	}
	
}
