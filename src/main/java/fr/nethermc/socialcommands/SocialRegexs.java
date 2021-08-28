package fr.nethermc.socialcommands;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialRegexs {

    public String discord;
    public String twitter;
    public String instagram;
    public String website;
    public List<String> platforms;

    public SocialRegexs() {
        this.platforms = Arrays.asList(
            "discord",
            "twitter",
            "instagram",
            "website"
        );
        this.discord = "((https?\\:\\/\\/)?discord\\.(gg|io)\\/)?([a-zA-Z0-9]+)";
        this.twitter = "((((https?\\:\\/\\/)?twitter\\.com\\/)?)|\\@)?([a-zA-Z0-9_]+)";
        this.instagram = "((((https?\\:\\/\\/)?(www\\.)?instagr(am\\.com|\\.am)\\/)?)|\\@)?([a-zA-Z0-9_]+)";
        this.website = "(https?\\:(\\/\\/)?)?(([a-z\\.]+)\\.)?(([a-z]+)\\.([a-z]+))(\\/([a-zA-Z0-9_\\-\\.]+)?)?";
    }

    public boolean regexMatch(String pattern, String string) {
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(string);

        return matcher.find();
    }

}