package com.eugenefe.mvfeed.quandl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

//@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "QDATA_MASTER")
public class QDataset2 {
	
	 @Id
	 @Column(name = "Q_ID", unique = true, nullable = false, length = 20)
    private String id;
	 @Column(name = "Q_NAME", unique = true, nullable = false, length = 50)
	 private String name;
    @Column(name = "Q_CODE", unique = true, nullable = false, length = 10)
    private String code;
    @Column(name = "Q_SOURCE_CODE", unique = true, nullable = false, length =20)
    private String source_code;
    @Column(name = "Q_TYPE", unique = true, nullable = false, length =20)
    private String type ;
    
    @Column(name = "URL", unique = true, nullable = false, length =100)
    private String display_url;
    
    @Column(name = "URL_QUERY", unique = true, nullable = false, length =100)
    private String urlize_name;
    @Column(name = "DESCRIPTION", unique = true, nullable = false, length =250)
    private String description;
    
    @Transient
    private String updated_at;
    
    @Column(name = "FREQUENCY", unique = true, nullable = false, length =20)
	private String frequency;
    @Column(name = "FROM_DATE", unique = true, nullable = false, length =20)
    private String from_date;
    @Column(name = "TO_DATE", unique = true, nullable = false, length =20)
    private String to_date;
    
    @Transient
    private List<String> column_names = new ArrayList<String>();
    @Transient
    private List<List<String>> data = new ArrayList<List<String>>();
    
//    private List<Map<String, String>> 
//    private List<QData> data = new ArrayList<QData>();
    private boolean _private;
//    private String errors;
    @JsonIgnore
//    public List<QError> errors;
    public List<String> errors;
//    private String rawData;
//    private List<QEntry> dataset = new ArrayList<QEntry>();
    public String error;
    public QDataset2() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource_code() {
		return source_code;
	}

	public void setSource_code(String source_code) {
		this.source_code = source_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlize_name() {
		return urlize_name;
	}

	public void setUrlize_name(String urlize_name) {
		this.urlize_name = urlize_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

//	public List<String> getColumn_names() {
//		return column_names;
//	}
//
//	public void setColumn_names(List<String> column_names) {
//		this.column_names = column_names;
//	}

//	public String getErrors() {
//		return errors;
//	}
//
//	public void setErrors(String errors) {
//		this.errors = errors;
//	}

	
	public List<List<String>> getData() {
		return data;
	}

//	@JsonIgnore
//	public List<QError> getErrors() {
//		return errors;
//	}
//
//	public void setErrors(List<QError> errors) {
//		this.errors = errors;
//	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

//	public List<QData> getData() {
//		return data;
//	}
//
//	public void setData(List<QData> data) {
//		this.data = data;
//	}

	
	public List<String> getColumn_names() {
		return column_names;
	}

	public void setColumn_names(List<String> column_names) {
		this.column_names = column_names;
	}

	public String getDisplay_url() {
		return display_url;
	}
	public void setDisplay_url(String display_url) {
		this.display_url = display_url;
	}
	
	public boolean isPrivate() {
		return _private;
	}

	public void setPrivate(boolean _private) {
		this._private = _private;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
//	public QDataset getQDataset(String hql){
//		Session s = HibernateUtil.currentSession();
//		String hql = " from QFutures a ";
//		Query qr = s.createQuery(hql);
//
//		return qr.list();
//	}
//	
//	public void setQDataset(List<QFutures> rst){
//		Session s = HibernateUtil.currentSession();
//		Transaction tx = s.beginTransaction();
//		for(QFutures aa : rst){
//			s.saveOrUpdate(aa);
//		}
//		tx.commit();
//	}
}
