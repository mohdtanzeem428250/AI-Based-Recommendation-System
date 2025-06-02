package recommendation.engine; // Declares the package name

import java.util.*; // Imports utility classes like List, Map, Set, HashMap, etc.

public class RecommendationEngine // Main class
{
    // Inner class to represent a user
    static class User
    {
        String name; // Name of the user
        Map<String, Integer> ratings; // A map of movie names and the user's ratings

        User(String name) // Constructor to initialize the user with name
        {
            this.name = name;
            this.ratings = new HashMap<>(); // Initialize ratings map
        }

        void addRating(String movie, int rating) // Method to add movie and rating
        {
            ratings.put(movie, rating); // Add or update rating for the movie
        }

        @Override
        public String toString() // Custom toString method to print user details
        {
            return "User={Name=" + name + ", Ratings=" + ratings + "}";
        }
    }

    // Inner class containing logic for recommendation engine
    static class MovieRecommendationEngine
    {
        // Method to calculate similarity between two users using Euclidean distance
        public static double calculaterSimilarity(Map<String, Integer> user1Ratings, Map<String, Integer> user2Ratings)
        {
            Set<String> commonMovie = new HashSet<>(user1Ratings.keySet()); // Get user1's rated movies
            commonMovie.retainAll(user2Ratings.keySet()); // Keep only movies rated by both users

            if (commonMovie.isEmpty()) // No common movies, similarity is zero
            {
                return 0;
            }

            double sum = 0; // To store squared differences
            for (String movie : commonMovie)
            {
                // Add square of rating difference for each common movie
                sum += Math.pow(user1Ratings.get(movie) - user2Ratings.get(movie), 2);
            }

            return 1 / (1 + Math.sqrt(sum)); // Return similarity score (closer means more similar)
        }

        // Method to generate movie recommendations for the target user
        public static Map<String, Double> getRecommendation(User targetUser, List<User> allUsers)
        {
            Map<String, Double> recommendation = new HashMap<>(); // Store recommended movies with scores

            for (User otherUser : allUsers) // Loop through all users
            {
                if (otherUser.equals(targetUser)) continue; // Skip if it's the same user

                double similarity = calculaterSimilarity(targetUser.ratings, otherUser.ratings); // Calculate similarity

                // Loop through movies rated by other users
                for (Map.Entry<String, Integer> entry : otherUser.ratings.entrySet())
                {
                    if (targetUser.ratings.containsKey(entry.getKey())) 
                        continue; // Skip movies already rated by the target user

                    // Update recommendation score based on similarity
                    recommendation.put(
                        entry.getKey(),
                        recommendation.getOrDefault(entry.getKey(), 0.0) + similarity
                    );
                }
            }

            return recommendation; // Return the final recommendation map
        }
    }

    // Main method to run the recommendation engine
    public static void main(String[] args)
    {
        // Create first user and add ratings
        User user1 = new User("Alice");
        user1.addRating("The Matrix", 5);
        user1.addRating("Avatar", 3);
        user1.addRating("Titanic", 4);

        // Create second user and add ratings
        User user2 = new User("Bob");
        user2.addRating("The Matrix", 4);
        user2.addRating("Avatar", 5);
        user2.addRating("Titanic", 2);

        // Create third user and add ratings
        User user3 = new User("Charlie");
        user3.addRating("The Matrix", 3);
        user3.addRating("Avatar", 4);
        user3.addRating("Titanic", 5);

        // Add all users to a list
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        // Set the target user for whom recommendations will be generated
        User targetUser = user1;

        // Get recommendations for the target user
        Map<String, Double> recommendation = MovieRecommendationEngine.getRecommendation(targetUser, users);

        // Print the recommended movies sorted by score in descending order
        System.out.println("Recommendation For " + targetUser.name + ":");
        recommendation.entrySet().stream()
            .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Sort by score descending
            .forEach(entry -> System.out.println(entry.getKey() + " With Score : " + entry.getValue())); // Print each movie
    }
}
