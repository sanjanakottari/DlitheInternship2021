package internship.dlithe.twentyone.DLithe2021Internship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.dlithe.twentyone.DLithe2021Internship.model.Account;

@Service
public class ServiceAccount 
{
	@Autowired
	RepoAccount repo;
	
	public String insert(Account account)
	{
		repo.save(account);
		return account.getAccHolder();
	}
	
	public Account fetchOne(Long data)
	{
		return repo.findById(data).orElse(new Account());
	}
}