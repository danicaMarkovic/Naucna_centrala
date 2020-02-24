package com.example.nCentrala.model.dto;

import java.util.List;

public class AdvancedSearchDataDTO {

	private BasicSearchQuery firstOption;
	
	private List<AdvancedSearchQuery> otherOptions;
	
	public AdvancedSearchDataDTO() {}

	public AdvancedSearchDataDTO(BasicSearchQuery firstOption, List<AdvancedSearchQuery> otherOptions) {
		super();
		this.firstOption = firstOption;
		this.otherOptions = otherOptions;
	}

	public BasicSearchQuery getFirstOption() {
		return firstOption;
	}

	public void setFirstOption(BasicSearchQuery firstOption) {
		this.firstOption = firstOption;
	}

	public List<AdvancedSearchQuery> getOtherOptions() {
		return otherOptions;
	}

	public void setOtherOptions(List<AdvancedSearchQuery> otherOptions) {
		this.otherOptions = otherOptions;
	}
	
}
