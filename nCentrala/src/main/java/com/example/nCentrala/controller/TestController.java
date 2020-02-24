package com.example.nCentrala.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;

@RestController
@RequestMapping("test")
public class TestController {

	@GetMapping(value = "/get/{id1}/{id2}")
	private String getLongAndLat(@PathVariable("id1") String city, @PathVariable("id2") String state) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String adresa = city.replace(" ", "+") + state.replace(" ", "+");
		
		System.out.println("Trazi se " + adresa);
		
		String fooResourceUrl = "https://nominatim.openstreetmap.org/search?q=%20\"+%27+"+ adresa +"+%27+\"%20format=json";
		ResponseEntity response
		  = restTemplate.getForEntity(fooResourceUrl, String.class);
		
		System.out.println("Response: ");
		System.out.println(response.toString());
		
		return  null;
	}
}
