import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search/search.service';
import { BasicSearch } from '../model/BasicSearch';
import { AdvancedSearch } from '../model/AdvancedSearch';
import { AdvancedSearchDataDTO } from '../model/AdvancedSearchDataDTO';
import { UrlHandlingStrategy } from '@angular/router';
import { ArticleService } from '../services/article/article.service';
import { FileService } from '../services/file/file.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  private fields = ['All fields','journalName','title','author', 'keywords', 'text', 'scienceArea'];
  private filters = ['Sciene area', 'Geo search', 'More like this search']
  private advacedOptions = ['AND', 'OR'];
  private advancedfields = ['All fields','journalName','title','author', 'coauthors' ,'keywords', 'text', 'scienceArea'];
  private result; 
  private query : BasicSearch = new BasicSearch();
  private advanced = false;
  private advancedEntity : Array<AdvancedSearch> = new Array<AdvancedSearch>();
  private articleSearch = true;
  private articleList;
  private articleId : number;
  private choosenFilter = "";
  private reviewersList;

  constructor(private searchService : SearchService, private articleService : ArticleService, private fileService : FileService) { }

  ngOnInit() {

  }

  search() {
    
    if(this.query.field != null && this.query.value != null && this.advancedEntity.length == 0) 
    {

      this.searchService.basicSearch(this.query).subscribe(res => {

        this.result  = res;
      
      }); 
    }else if(this.query.field != null && this.query.value != null && this.advancedEntity.length != 0)
    {
      console.log("Field " + this.query.field);
      console.log("Value: " + this.query.value);
      let queryForSearch = new AdvancedSearchDataDTO();
      queryForSearch.firstOption.field = this.query.field;
      queryForSearch.firstOption.value = this.query.value;
      queryForSearch.otherOptions = this.advancedEntity;

      this.searchService.advancedSearch(queryForSearch).subscribe(res => {
        this.result = res;
      });

    }else
    {
      alert("Fill all choosen fields for search");
    } 
  }

  advancedSearch() {
    
     this.advancedEntity.push(new AdvancedSearch());
    
  }

  closeField(aE : AdvancedSearch){

    this.advancedEntity = this.advancedEntity.filter(item => item != aE);

  }

  articleSearchView(){
    this.articleSearch = true;
  }

  reviewersSearchView(){

    this.articleService.getAllArticles().subscribe(res => {
      this.articleList = res;
    });

    this.searchService.getAllReviewers().subscribe(res => {

      this.reviewersList = res;
      console.log(this.reviewersList);
    });

    this.articleSearch = false;
  }

  reviewersSearch(){

    if(this.isBlank(this.articleId) || this.isBlank(this.choosenFilter))
    {
      alert("Choose all criteria");
    }else
    {
      
      if(this.choosenFilter == 'Sciene area')
      {
        this.searchService.scienceAreaSearch(this.articleId).subscribe(res => {

          this.reviewersList = res;
        });
      }else if(this.choosenFilter == 'Geo search')
      {
        this.searchService.geoSearch(this.articleId).subscribe(res => {

          this.reviewersList = res;
        });
      }else
      {
        this.searchService.moreLikeThisSearch(this.articleId).subscribe(res => {

          this.reviewersList = res;
        });
      }
    }

  }

  downloadPdf(pdfPath : string){

    var pom = [];
    pom = pdfPath.split("\\");

    var pdfName = pom[pom.length - 1];

    this.fileService.downloadFile(pdfName).subscribe(res => {
      
      saveAs(res, pdfName);
    });
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
