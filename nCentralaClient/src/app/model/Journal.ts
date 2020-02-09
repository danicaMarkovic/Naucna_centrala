import { ScienceArea } from './ScienceArea';
import { PaymentMethod } from './PaymentMethod';
import { Subscription } from './Subscription';

export class Journal {
    id : number;
	
	name : String;
	
	issn : String;
	
	isOpenAccess : boolean;
	
	isActivated : boolean;
	
	scienceAreas  : Array<ScienceArea>;
	
	paymentMethods : Array<PaymentMethod>;
	
	subscriptions : Array<Subscription>;
	
	//private Editor mainEditor;
	
//	private List<Editor> editors = new ArrayList<Editor>();
}