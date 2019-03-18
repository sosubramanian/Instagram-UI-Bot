package instagram.http;

import instagram.logger.LogService;
import instagram.model.ConfigData;
import org.springframework.web.client.RestTemplate;

import instagram.model.Profile;
import instagram.utils.ProfileUtils;

public class HttpCall {

	private static LogService logger = new LogService();

	public static String getResponse(String url) {
		RestTemplate restTemplate = new RestTemplate();
		String response;
		try {
			response = restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			logger.append("Error fetching data for URL: " + url).log();
			return "";
		}
		return response;
	}

	public static String getProfileAsText(String profileName) {
		return getResponse(ConfigData.BASE_URL + "/" + profileName);
	}

	public static Profile getProfile(String profileName) {
		Profile profile = new Profile();
		profile.setName(profileName);
		String profileText = getProfileAsText(profileName);
		try {
			String followers = profileText.split("meta content=\"")[1].split(" ")[0];
			profile.setNoOfFollowers(ProfileUtils.getNumberCount(followers));
			String following = profileText.split("Followers, ")[1].split(" ")[0];
			profile.setNoOfFollowing(ProfileUtils.getNumberCount(following));
			String posts = profileText.split("Following, ")[1].split(" ")[0];
			profile.setPosts(ProfileUtils.getNumberCount(posts));
		} catch (Exception e) {
			logger.append("Exception in getting profile details").log();
			return profile;
		}
//		System.out.println(profile);
		return profile;
	}

}
