package br.com.evtm;

import java.util.List;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class TesteRecomendadorSQL {

	private static final String SERVER_NAME = "localhost";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE = "LAB_RECOMENDADOR";

	private static String[] books = { "Meet Big Brother", "Explore the Universe", "Memoir as metafiction",
			"A child-soldier's story", "Wicked good fun", "The 60s kids classic", "A short-form master",
			"Go down the rabbit hole", "Unseated a president", "An Irish-American Memoir" };

	private static final int NEIGHBOR_HOOD_SIZE = 5;

	/**
	 * Get Recommender instance using database
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Recommender getRecommender() throws Exception {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(SERVER_NAME);
		dataSource.setUser(USER_NAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setDatabaseName(DATABASE);
		

		DataModel model = new MySQLJDBCDataModel(dataSource, 
				"AVALIACAO_SUB_TIPO", "id_usuario", "id_categoria", "avaliacao", null);

//		Get Pearson correlation instance from given model
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

//		Computes a neighborhood consisting of the nearest n users to a given user.
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBOR_HOOD_SIZE, similarity, model);

//		Get Recommender
		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

		return recommender;
	}

	/**
	 * Get noOfRecommendations for given customer
	 * 
	 * @param recommender
	 * @param custId
	 * @param noOfRecommendations
	 * @return
	 * @throws Exception
	 */
	public static List<RecommendedItem> getRecommendations(Recommender recommender, int custId, int noOfRecommendations)
			throws Exception {
		return recommender.recommend(custId, noOfRecommendations);
	}

	public static void displayRecommendations(int custId, List<RecommendedItem> recommendations) {
		System.out.println("Recommendations for customer " + custId + " are:");
		System.out.println("*************************************************");

		for (RecommendedItem recommendation : recommendations) {
			int bookId = (int) recommendation.getItemID();
			System.out.println(bookId + " " + books[bookId - 1]);
		}

		System.out.println("*************************************************");
	}

	public static void main(String args[]) throws Exception {

		Recommender recommender = getRecommender();
		List<RecommendedItem> recommendations;

		recommendations = getRecommendations(recommender, 1, 5);
		displayRecommendations(1, recommendations);

		recommendations = getRecommendations(recommender, 2, 5);
		displayRecommendations(2, recommendations);
	}
}
