import { BasicSearch } from './BasicSearch';
import { AdvancedSearch } from './AdvancedSearch';

export class AdvancedSearchDataDTO{

    firstOption : BasicSearch = new BasicSearch();
    otherOptions : Array<AdvancedSearch>;

}