
package internship.dlithe.twentyone.DLithe2021Internship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.dlithe.twentyone.DLithe2021Internship.model.Account;
import internship.dlithe.twentyone.DLithe2021Internship.model.Beneficiary;

@RestController
@RequestMapping("/rest")
public class RestAccount 
{
	@Autowired
	ServiceAccount service;
	
	@Autowired
	ServiceBeneficiary benserv;
	
	@GetMapping("/")
	public List<Beneficiary> fetch()
	{
		return benserv.getAll();
	}
	
	@GetMapping(value = "/xml",produces = "application/xml")
	public List<Beneficiary> fetchAsXml()
	{
		return benserv.getAll();
	}
	
	@PostMapping("/newacc")
	public String createNewAccount(@RequestBody Account account)
	{
		return service.insert(account)+" has added";
	}
	
	@PostMapping(value="/newaccxml",consumes = "application/xml")
	public String createNewAccountXML(@RequestBody Account account)
	{
		return service.insert(account)+" has added";
	}
	
	@GetMapping("/{holder}")
	public List<Beneficiary> fetchByHolder(@PathVariable("holder") Long num)
	{
		return benserv.getEveryOneByAccount(num);
	}
	
	@PutMapping("/benup")
	public String updateAsJson(@RequestBody Beneficiary beneficiary)
	{
		return benserv.add(beneficiary).getName()+" has updated";
	}
	
	@PostMapping("/benadd")
	public String addingNewBenf(@RequestBody Beneficiary beneficiary)
	{
		return benserv.add(beneficiary).getName()+" has Added successfully";
	}
	
	@DeleteMapping("/remove")
	public String deletingBenf(@RequestBody Beneficiary beneficiary)
	{
		return benserv.remove(beneficiary)+" has removed from favorite list";
	}
}