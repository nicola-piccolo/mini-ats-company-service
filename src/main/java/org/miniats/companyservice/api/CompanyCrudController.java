package org.miniats.companyservice.api;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.miniats.common.util.UidGenerator;
import org.miniats.common.util.ValidationErrorUtility;
import org.miniats.companyservice.model.Company;
import org.miniats.companyservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/company", produces="application/json")
@CrossOrigin(origins="*")
public class CompanyCrudController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping("/")
	public Iterable<Company> getCompanies(){
		return this.companyRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public Company getCompanyByUid(@PathVariable("uid") String companyUid) {
		Optional<Company> companyToCheck = this.companyRepository.findByUid(companyUid);
		if(companyToCheck.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found for uid = " + companyUid);
		}
		return companyToCheck.get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Company createCompany(@Valid @RequestBody Company company) {
		String companyUid = UidGenerator.generateUid();
		company.setUid(companyUid);
		return this.companyRepository.save(company);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException exception) {
	    return ValidationErrorUtility.convertIntoErrorMessagesFrom(exception);
	}
}
