package com.iiht.training.eloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sanction_info")
public class SanctionInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sanction_id")
	private Long id;
	
	
	@Column(name="loan_appl_id")
	private Long loanAppId;
	
	@Column(name="manager_id")
	private Long managerId;
	
	@Column(name="loan_amt_sanctiond")
	private Double loanAmountSanctioned;
	
	@Column(name="loan_term")
	private Double termOfLoan;
	
	@Column(name="paymnt_start_dt")
	private String paymentStartDate;
	
	@Column(name="loan_closure_dt")
	private String loanClosureDate;
	
	@Column(name="monthly_paymnt")
	private Double monthlyPayment;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanAppId() {
		return loanAppId;
	}
	public void setLoanAppId(Long loanAppId) {
		this.loanAppId = loanAppId;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public Double getLoanAmountSanctioned() {
		return loanAmountSanctioned;
	}
	public void setLoanAmountSanctioned(Double loanAmountSanctioned) {
		this.loanAmountSanctioned = loanAmountSanctioned;
	}
	public Double getTermOfLoan() {
		return termOfLoan;
	}
	public void setTermOfLoan(Double termOfLoan) {
		this.termOfLoan = termOfLoan;
	}
	public String getPaymentStartDate() {
		return paymentStartDate;
	}
	public void setPaymentStartDate(String paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}
	public String getLoanClosureDate() {
		return loanClosureDate;
	}
	public void setLoanClosureDate(String loanClosureDate) {
		this.loanClosureDate = loanClosureDate;
	}
	public Double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

}
