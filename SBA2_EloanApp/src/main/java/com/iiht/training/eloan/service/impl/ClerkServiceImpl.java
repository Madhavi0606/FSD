package com.iiht.training.eloan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository processingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
		
	@Override
	public List<LoanOutputDto> allAppliedLoans() {
		 List<LoanOutputDto> u1 = null;
		 u1 = loanRepository.findAll().stream().map(e -> loanParse(e)).collect(Collectors.toList());
		 for(int i =0;i<=u1.size();i++) {
				if(!((u1.get(i)).getRemark().equalsIgnoreCase("Applied"))) {
					u1.remove(i);
				}
				}
		 return u1;
	}
	@Transactional
	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto) {
		return processParse(processingInfoRepository.save(processParse(clerkId,loanAppId,processingDto)));
	}

private ProcessingInfo processParse(Long clerkId, Long loanAppId, ProcessingDto source) {
	ProcessingInfo target = new ProcessingInfo();
	
	target.setAcresOfLand(source.getAcresOfLand());
	target.setAddressOfProperty(source.getAddressOfProperty());
	target.setAppraisedBy(clerkId.toString());
	target.setLandValue(source.getLandValue());
	target.setLoanAppId(loanAppId);
	target.setLoanClerkId(clerkId);
	target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
	target.setValuationDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	
	Loan loanInf = loanRepository.findById(loanAppId).get();
	loanInf.setRemark("Processed");
	loanInf.setStatus(1);
	
	
	
	return target;
	}

private ProcessingDto processParse(ProcessingInfo source) {
	ProcessingDto target = new ProcessingDto();
	
	target.setAcresOfLand(source.getAcresOfLand());
	target.setAddressOfProperty(source.getAddressOfProperty());
	target.setLandValue(source.getLandValue());
	target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
	target.setValuationDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	return target;
}

public static LoanOutputDto loanParse(Loan loanInf) {
		
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanAmount(loanInf.getLoanAmount());
		loanDto.setLoanName(loanInf.getLoanName());
		loanDto.setBillingIndicator(loanInf.getBillingIndicator());
		loanDto.setBusinessStructure(loanInf.getBusinessStructure());
		loanDto.setTaxIndicator(loanInf.getTaxIndicator());
		
		
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(loanInf.getCustomerId());
		loanOutputDto.setLoanAppId(loanInf.getId());
		loanOutputDto.setLoanDto(loanDto);
		loanOutputDto.setStatus(loanInf.getStatus());
		loanOutputDto.setRemark(loanInf.getRemark());
		
		
		return loanOutputDto;
	}
	
	public static Loan loanParse(LoanDto loanDto) {
		Loan loanInf = new Loan();
		loanInf.setCustomerId(loanDto.getUserDto().getId());
		loanInf.setLoanName(loanDto.getLoanName());
		loanInf.setLoanAmount(loanDto.getLoanAmount());
		loanInf.setLoanApplicationDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		loanInf.setBusinessStructure(loanDto.getBusinessStructure());
		loanInf.setBillingIndicator(loanDto.getBillingIndicator());
		loanInf.setTaxIndicator(loanDto.getTaxIndicator());
		loanInf.setStatus(0);
		loanInf.setRemark("Applied");
		
		
		Users userObj = new Users();
		userObj.setId(loanDto.getUserDto().getId());
		
		loanInf.setUserObj(userObj);
		
		return loanInf;
		
	}
}
