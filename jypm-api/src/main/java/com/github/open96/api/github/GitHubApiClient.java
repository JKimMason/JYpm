package com.github.open96.api.github;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApiClient {

    //Hardcoded base url for api calls
    private static final String GITHUB_API_BASE_URL = "https://api.github.com/";
    //Initialize log4j logger for later use in this class
    private static Logger log = LogManager.getLogger(GitHubApiClient.class.getName());
    //Variable that stores reference to Retrofit object.
    private Retrofit retrofit;
    //Singleton instance that stores instance of this class
    private static GitHubApiClient singletonInstance;

    /**
     * Initializes Retrofit object and stores it in variable.
     */
    private GitHubApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Method that ensures object will be initialized only once.
     *
     * @return Singleton instance of GitHubApiClient.
     */
    public static GitHubApiClient getInstance() {
        log.debug("Instance of GitHubApiClient has been requested.");
        if (singletonInstance == null) {
            log.debug("Instance is null, initializing...");
            singletonInstance = new GitHubApiClient();
        }
        return singletonInstance;
    }

    /**
     * @return Retrofit object that is used to get data from GitHub API.
     */
    public Retrofit getClient() {
        return retrofit;
    }

}
