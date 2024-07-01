package cloneproject.Instagram.global.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class StringExtractUtil {

	public List<String> extractMentionsWithExceptList(String input, List<String> exceptUsernames) {
		final Set<String> mentions = new HashSet<>();
		final String regex = "@[0-9a-zA-Z가-힣ㄱ-ㅎ]+";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			mentions.add(matcher.group().substring(1));
		}
		for (String username : exceptUsernames) {
			mentions.remove(username);
		}

		return new ArrayList<>(mentions);
	}

	public List<String> extractMentions(String input) {
		return extractMentionsWithExceptList(input, List.of());
	}

	public List<String> extractHashtags(String input) {
		final Set<String> hashtags = new HashSet<>();
		final String regex = "#[0-9a-zA-Z가-힣ㄱ-ㅎ_]+";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			hashtags.add(matcher.group().substring(1));
		}

		return new ArrayList<>(hashtags);
	}

}
