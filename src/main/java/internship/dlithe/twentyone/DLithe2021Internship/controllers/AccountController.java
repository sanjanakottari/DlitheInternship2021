package internship.dlithe.twentyone.DLithe2021Internship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import internship.dlithe.twentyone.DLithe2021Internship.model.Account;
import internship.dlithe.twentyone.DLithe2021Internship.model.Beneficiary;

@Controller
public class AccountController 
{

	Account loggedPerson;
	
	List<Beneficiary> every;
	
	@Autowired
	ServiceAccount service;
	
	@Autowired
	ServiceBeneficiary benserv;
	
	@RequestMapping("/")
	public String open()
	{
		return "index";
	}
	
	@RequestMapping(value="/log",method=RequestMethod.POST)
	public String logging(Model model,@RequestParam("user") Long user,@RequestParam("pass") String pass)
	{
		loggedPerson=service.fetchOne(user);
		if(loggedPerson.getPassword()!=null&&loggedPerson.getPassword().equals(pass))
		{
			model.addAttribute("username", loggedPerson.getAccHolder());
			return "home";
		}
		else
		{
			model.addAttribute("msg", "Invalid account/password");
			return "index";
		}
	}
	
	@RequestMapping("/sign")
	public String kyc(Model model)
	{
		Account acc=new Account();
		model.addAttribute("obj", acc);
		return "signup";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String adding(Model model,@ModelAttribute("holder") Account holder)
	{
		System.out.println(holder);
		String name=service.insert(holder);
		model.addAttribute("msg", name);
		return "signup";
	}
	
	@RequestMapping("/manage")
	public String managing(Model model)
	{
		//every=benserv.getAll();
		every=benserv.getEveryOneByAccount(loggedPerson.getAccNum());
		model.addAttribute("all", every);
		return "beneficiaries";
	}
	
	@RequestMapping("/addNew")
	public String adding(Model model)
	{
		Beneficiary ben=new Beneficiary();
		model.addAttribute("object", ben);
		return "create";
	}
	
	@RequestMapping(value="/keep",method=RequestMethod.POST)
	public String added(Model model,@ModelAttribute("benef") Beneficiary benef)
	{
		System.out.println(loggedPerson.getAccHolder()+" going have new beneficiary");
		benef.setAccount(loggedPerson);
		benserv.add(benef);
		model.addAttribute("info",benef.getName()+" has affected");
		//every=benserv.getAll();
		every=benserv.getEveryOneByAccount(loggedPerson.getAccNum());
		model.addAttribute("all", every);
		return "beneficiaries";
	}
	
	@RequestMapping("/edit/{id}")
	public String letEdit(Model model,@PathVariable("id") Long id)
	{
		Beneficiary ben=benserv.extractOne(id).orElse(new Beneficiary());
		model.addAttribute("object", ben);
		return "change";
	}
	
	@RequestMapping("delete/{id}")
	public String erase(Model model,@PathVariable("id") Long id)
	{
		Beneficiary ben=benserv.extractOne(id).orElse(new Beneficiary());
		String tmp=benserv.remove(ben);
		//every=benserv.getAll();
		every=benserv.getEveryOneByAccount(loggedPerson.getAccNum());
		model.addAttribute("info", tmp+" has deleted from your list");
		model.addAttribute("all", every);
		return "beneficiaries";
	}
}