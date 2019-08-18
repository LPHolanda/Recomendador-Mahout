package br.com.evtm;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class RecomendaEventos {
	public static void main(String[] args) throws IOException, TasteException {
		DataModel eventos = new Recomendador().getModeloDeEventos();
		Recommender recommender = new RecomendadorBuilder().buildRecommender(eventos);
		
		List<RecommendedItem> recommendations = recommender.recommend(5, 3);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}
}
