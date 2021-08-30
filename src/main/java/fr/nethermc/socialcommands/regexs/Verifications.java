package fr.nethermc.socialcommands.regexs;

public class Verifications {

    private final Regexs regexs = new Regexs();

    public boolean verifyPlatform(String platform, String string) {
        switch (platform) {
            case "discord":
                return this.verifyDiscord(string);
            case "twitter":
                return this.verifyTwitter(string);
            case "instagram":
                return this.verifyInstagram(string);
            case "website":
                return this.verifyWebsite(string);
            default:
                return false;
        }
    }

    public boolean verifyDiscord(String string) {
        return regexs.regexMatch(Regexs.DISCORD_REGEX, string);
    }

    public boolean verifyTwitter(String string) {
        return regexs.regexMatch(Regexs.TWITTER_REGEX, string);
    }

    public boolean verifyInstagram(String string) {
        return regexs.regexMatch(Regexs.INSTAGRAM_REGEX, string);
    }

    public boolean verifyWebsite(String string) {
        return regexs.regexMatch(Regexs.WEBSITE_REGEX, string);
    }

}
