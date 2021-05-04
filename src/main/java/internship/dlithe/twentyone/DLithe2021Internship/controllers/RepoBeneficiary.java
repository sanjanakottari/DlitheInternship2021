
package internship.dlithe.twentyone.DLithe2021Internship.controllers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import internship.dlithe.twentyone.DLithe2021Internship.model.Beneficiary;

@Repository
public interface RepoBeneficiary extends JpaRepository<Beneficiary, Long>
{
	//@Query("from Beneficiary where account=:accnum")
	public List<Beneficiary> findAllByAccountAccNum(Long accnum);
}