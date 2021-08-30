package fr.nethermc.socialcommands.regexs;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexs {

    public static final String DISCORD_REGEX = "(discord\\.(gg|io)\\/)?([a-zA-Z0-9]+)";
    public static final String TWITTER_REGEX = "(\\@)?([a-zA-Z0-9_]+)";
    public static final String INSTAGRAM_REGEX = "(\\@)?([a-zA-Z0-9_]+)";
    public static final String WEBSITE_REGEX = "(https?\\:(\\/\\/)?)?(([a-z\\.]+)\\.)?(([a-z]+)\\.([a-z]+))(\\/([a-zA-Z0-9_\\-\\.]+)?)?";
    public static final List<String> PLATFORMS = Arrays.asList("discord", "twitter", "instagram", "website");

    public Regexs() {}

    public boolean regexMatch(String pattern, String string) {
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(string);

        return matcher.find();
    }

}