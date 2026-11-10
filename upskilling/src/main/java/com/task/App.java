package com.task;

import java.time.LocalDate;
import java.util.Scanner;



public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello ... Choose an option:" );
        System.out.println( "1. Add a new movie" );
        System.out.println( "2. View all movies" );
        System.out.println( "3. Search for a movie" );
        System.out.println( "4. Mark a movie as watched" );
        System.out.println( "5. Remove a movie" );
        System.out.println( "6. Calculate average rating" );
        System.out.println( "7. Exit" );
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        while (choice != 7) {
            JsonUtils<Movie> movieJsonUtils = new JsonUtils<>("movies3.json", Movie.class);
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter movie details (id, title, genre, rating, watched, addedDate yyyy-MM-dd):");
                    int id = scanner.nextInt();
                    String title = scanner.next();
                    String genre = scanner.next();
                    double rating = scanner.nextDouble();
                    boolean watched = scanner.nextBoolean();
                    String addedDatestr = scanner.next();
                    LocalDate addedDate = LocalDate.parse(addedDatestr);
                    Movie movie = new Movie(id, title, genre, rating, watched, addedDate);
                    movieJsonUtils.additem(movie);
                    break;
                }
                case 2 -> {
                    movieJsonUtils.displayallitems();
                    break;
                }
                case 3 -> {
                    System.out.println("Enter the title of the movie you want to search for:");
                    String titleToSearch = scanner.next();
                    Movie movieToSearch = movieJsonUtils.searchforitem(titleToSearch);
                    if (movieToSearch != null) {
                        System.out.println("Movie found:");
                        System.out.println(movieToSearch);
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;
                }
                case 4 -> {
                    System.out.println("Enter the title of the movie you want to mark as watched:");
                    String titleToMarkWatched = scanner.next();
                    movieJsonUtils.markitemascompleted(titleToMarkWatched);
                    break;
                }
                case 5 -> {
                    System.out.println("Enter the title of the movie you want to remove:");
                    String titleToRemove = scanner.next();
                    Movie movieToRemove = movieJsonUtils.searchforitem(titleToRemove);
                    if (movieToRemove != null) {
                        movieJsonUtils.removeitem(movieToRemove);
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;
                }
                case 6 -> {
                    double averageRating = movieJsonUtils.calculateaverage();
                    System.out.println("Average rating of all movies: " + averageRating);
                    break;
                }

                case 7 -> {
                    System.out.println("Exiting the application.");
                    break;
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
                if (choice != 7) {
                    System.out.print("\nEnter your choice (1-7): ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
            }

        }
        scanner.close();
    }
}
