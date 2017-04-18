package com.jpmc.core.test;
/**
* RankEntities is a utility which will rank the entities data based on Transaction Type
*
* @author  Akhilesh Perla
* @version 1.0
* @since   2017-04-17 
*/

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankEntities {

	/* Rank the entities based on the Transaction type */
	public List<RankingDataModel> rankEntitiesByTransaction(List<FXTransactionDataModel> listData,char transactionType)
	{

        double[] score = {Double.MIN_VALUE};
        int[] no = {0};
        int[] rank = {0};
		
		return	listData.stream().filter(x -> x.getTranscationType() == transactionType)
	             .collect(Collectors.groupingBy(FXTransactionDataModel::getEntity,
	                     Collectors.summingDouble(FXTransactionDataModel::getTotalTransaction))).entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	             .map(p -> {
	                 ++no[0];
	                 if (score[0] != p.getValue()) rank[0] = no[0];
	                 else if (score[0] == p.getValue()) --no[0];
	                 return new RankingDataModel(rank[0], p.getKey(),score[0] = p.getValue());
	             })
	             .collect(Collectors.toList());
	}

}
