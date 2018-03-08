package edu.knoldus.operation;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TwitterOperation {

    private twitter4j.Twitter twitter = new TwitterFactory().getInstance();
    private Query query;

    /**
     * @param hashTag as Input.
     */
    public TwitterOperation(String hashTag) {
        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream("/home/knoldus/IdeaProjects/"
                    + "java8assignment02/src/main/resources/config.properties");
            properties.load(input);
            twitter.setOAuthConsumer(properties.getProperty("consumerKey"), properties
                    .getProperty("consumerSecret"));
            twitter.setOAuthAccessToken(new AccessToken(properties.getProperty("accessToken"),
                    properties.getProperty("accessTokenSecret")));
            query = new Query(hashTag);
            query.setSince("2018-01-20");
            query.setUntil("2018-01-30");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return tweets.
     */
    public final CompletableFuture<List<String>> getTweets() {

        return CompletableFuture.supplyAsync(() -> {
            List<String> tweets = new LinkedList<>();
            try {
                List<Status> responseList = twitter.search(query).getTweets();
                tweets = responseList.parallelStream()
                        .map(tweetsData -> tweetsData.getText())
                        .collect(Collectors.toList());
            } catch (TwitterException exception) {
                exception.getMessage();
            }
            return tweets;
        }).thenApply(tweets -> {
            return tweets;
        });
    }

    /**
     * @return number of tweets for a hashTag.
     */
    public final CompletableFuture<Integer> getNumberOfTweets() {
        return CompletableFuture.supplyAsync(() -> {
            Integer numberOfTweets = 0;
            try {
                numberOfTweets = twitter.search(query).getTweets().size();
            } catch (TwitterException exception) {
                exception.getMessage();
            }
            return numberOfTweets;
        }).thenApply(numberOfTweets -> {
            return numberOfTweets;
        });
    }


    /**
     * @return average tweets per day.
     */
    public final CompletableFuture<Double> getAverageTweetsPerDay() {

        return CompletableFuture.supplyAsync(() -> {
            Double averageTweetsPerDay = 0.0;
            try {
                averageTweetsPerDay = twitter.search(query).getTweets().size() / 10.0;
            } catch (TwitterException exception) {
                exception.getMessage();
            }
            return averageTweetsPerDay;
        }).thenApply(tweets -> {
            return tweets;
        });
    }


    /**
     * @return average likes.
     */
    public final CompletableFuture<Double> getAverageLikes() {
        return CompletableFuture.supplyAsync(() -> {
            Double averageLike = 0.0;
            try {

                List<Status> twitterStatus = twitter.search(query).getTweets();
                Double twitterSize = twitterStatus.size() + 0.0;
                averageLike = twitterStatus.parallelStream()
                        .map(tweets -> tweets.getFavoriteCount())
                        .reduce((tweetOne, tweetTwo) -> tweetOne + tweetTwo).get() / twitterSize;
            } catch (TwitterException te) {
                te.getMessage();
            }
            return averageLike;
        }).thenApply(averageLikes -> {
            return averageLikes;
        });
    }

    /**
     * @return average reTweets.
     */
    public final CompletableFuture<Double> getAverageReTweets() {
        return CompletableFuture.supplyAsync(() -> {
            Double totalReTweet = 0.0;
            try {

                List<Status> responseList = twitter.search(query).getTweets();
                Double twitterSize = responseList.size() + 0.0;
                totalReTweet = responseList.parallelStream()
                        .map(tweets -> tweets.getRetweetCount())
                        .reduce((tweetOne, tweetTwo) -> tweetOne + tweetTwo).get() / twitterSize;
            } catch (TwitterException exception) {
                exception.getMessage();
            }
            return totalReTweet;
        }).thenApply(aveReTweets -> {
            return aveReTweets;
        });
    }
}
