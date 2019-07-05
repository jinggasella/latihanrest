package com.eksad.latihanrest.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP) // mensetting bahwa yg tersimpen tanggal aja atau tanggal dan waktu
	private Date date;  //type data date tetap harus pake @Temporal juga
	
	private String remark;
	
// data transaction detail banyak, jd perlu simpah di collection set
	@EqualsAndHashCode.Exclude //biar ga bolak balik	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", fetch = FetchType.LAZY)
	private Set<TransactionDetail> details;

	
}
