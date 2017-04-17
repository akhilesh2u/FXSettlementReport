/**
* RankingDataModel is POJO which stores the entity transaction details with rank
*
* @author  Akhilesh Perla
* @version 1.0
* @since   2017-04-17 
*/

public class RankingDataModel {
    
	private Double totalamount;
	private Integer rank;
	private String entity;

	public RankingDataModel(Integer rank,String entity,Double totalamount) {
		super();
		this.totalamount = totalamount;
		this.rank = rank;
		this.entity=entity;
	}
    public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

}