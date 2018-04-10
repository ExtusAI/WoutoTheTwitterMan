package xyz.sethy.woutobot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import xyz.sethy.woutobot.dto.TextError;
import xyz.sethy.woutobot.dto.TextResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Seth on 30/08/2017.
 */
public class WoutoBot {
    private final Gson gson;
    private final Twitter twitter;
    private static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

    public WoutoBot() {
        this.gson = new GsonBuilder().create();

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setDebugEnabled(true)
                .setOAuthConsumerKey("9v81j9otXQOAfxAQyCpntPxtN")
                .setOAuthConsumerSecret("OPDpn9Fq1Hx3hFGd5LpNkpfuhtrtz56k6ypXz2ewELos2HGFZW")
                .setOAuthAccessToken("902707288686944258-ZdQ4tTojYzPADZRa8i1xbsMxDz1gCNX")
                .setOAuthAccessTokenSecret("RcDnu5QtomuMhTgSkTGITVL79BsBAyoGHpwJemf7dqlo6");

        this.twitter = new TwitterFactory(builder.build()).getInstance();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Woutoify(), 0, 5, TimeUnit.MINUTES);
    }

    private String makeTweet(final ResponseList<Status> statuses) {
        StringBuilder tweet = new StringBuilder();
        int max = 100;
        final int[] i = {0};

        System.out.println("status");
        statuses.forEach(s -> {
            if(max < i[0]) {
                return;
            }
            i[0]++;

            if(s.getUser().getName().equalsIgnoreCase("woutobot"))
                return;

            String[] words = s.getText().split(" ");
            Integer element = new Random().nextInt(words.length);
            String word = words[element];
            Pattern p = Pattern.compile(URL_REGEX);
            Matcher m = p.matcher(word);
            if((tweet.toString().length() + word.length() < 140 && !m.find())) {
                tweet.append(word).append(" ");
            }
        });

        System.out.println(tweet.toString());
        return tweet.toString();
    }

//    private String makeTweet() throws TwitterException {
//        System.out.println("woutify start");
//        ResponseList<Status> statuses = twitter.getHomeTimeline(new Paging(1, 40));
//        System.out.println("str builder");
//        StringBuilder tweet = new StringBuilder();
//        int max = 100;
//        final int[] i = {0};
//
//        System.out.println("status");
//        statuses.forEach(s -> {
//            if(max < i[0]) {
//                return;
//            }
//            i[0]++;
//
//            if(s.getUser().getName().equalsIgnoreCase("woutobot"))
//                return;
//
//            String[] words = s.getText().split(" ");
//            Integer element = new Random().nextInt(words.length);
//            String word = words[element];
//            Pattern p = Pattern.compile(URL_REGEX);
//            Matcher m = p.matcher(word);
//            if((tweet.toString().length() + word.length() < 140 && !m.find())) {
//                tweet.append(word).append(" ");
//            }
//        });
//
//        System.out.println(tweet.toString());
//        return tweet.toString();
//    }

    private boolean isSentance(final String text) throws MalformedURLException {
        String urlStr = "https://api.textgears.com/check.php?text=" + text.replaceAll(" ", "+") + "&key=jLnuHTytuuT4zcDZ";
        URL url = new URL(urlStr);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        StringBuilder json = new StringBuilder();
        String line;
        try {
            while((line = reader.readLine()) != null)
                json.append(line);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(json.toString());

        TextResult result = this.gson.fromJson(json.toString(), TextResult.class);

        return result.getErrors().size() < 0;
    }

    public class Woutoify implements Runnable {

        public void run() {
            try {
                ResponseList<Status> statuses = twitter.getHomeTimeline(new Paging(1, 40));


                String tweet = makeTweet(statuses);

                while(!isSentance(tweet)) {
                    tweet = makeTweet(statuses);
                }

                System.out.println("i would tweet " + tweet);

//                twitter.updateStatus(tweet);

            } catch (TwitterException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
