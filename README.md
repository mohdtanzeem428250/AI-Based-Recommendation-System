# AI-Based-Recommendation-System

COMPANY : CODTECH IT SOLUTIONS PVT. LTD.

NAME : MOHD TANZEEM

INTERN ID : CT08DA696

DOMAIN : JAVA PROGRAMMING

DURATION : 8 WEEKS

MENTOR : NEELA SANTHOSH KUMAR

---

# Task Description: AI-Based-Recommendation-System

---

# 🎬 Movie Recommendation Engine (Java)

This project is a Java-based implementation of a **basic movie recommendation engine**, designed using the principles of **user-based collaborative filtering**. It aims to simulate how recommendation systems suggest content (in this case, movies) to a user based on the preferences and behaviors of other users with similar interests.

The core idea behind the system is straightforward: **“Users who rate items similarly are likely to share similar tastes.”** By identifying these users and analyzing the items they’ve rated highly, the system can make meaningful recommendations to the target user. This approach is widely used in real-world platforms such as Netflix, Amazon, and YouTube.

## 👨‍💻 Code Overview
The code is structured with two main components inside the **RecommendationEngine class**:

### 1.User Class
This inner class is used to represent individual users. Each user has a name and a collection of movie ratings stored in a HashMap<String, Integer>, where the key is the movie title and the value is the rating given by the user. The class also includes a method addRating() to allow adding or updating a rating for a specific movie.

### 2.MovieRecommendationEngine Class
This static inner class contains the logic to calculate similarity between users and to generate movie recommendations. The method calculaterSimilarity() implements the **Euclidean distance-based similarity formula**, which compares the ratings of two users over commonly rated movies. The similarity score is calculated as:

​![Image](https://github.com/user-attachments/assets/43e15ee3-e482-456c-b4a9-4fc5bb09b144)
 
A higher similarity score implies that the two users have similar tastes.

The method **getRecommendation()** is responsible for generating a list of movie suggestions for the target user. It iterates over all other users, calculates similarity, and for each movie the target user hasn’t seen, it adds a recommendation score weighted by similarity.

## 🔄 Flow of Execution
In the main() method, three users (Alice, Bob, and Charlie) are created, and each provides ratings for a few movies. These users are added to a list. The engine then selects one user (Alice) as the target and computes recommendations based on the ratings and similarity with the other users.

The results are printed in descending order of recommendation score, allowing the most relevant suggestions to appear at the top.
