package wisepanda.common;

/*
 * @Author rohit_prajapati
 * @Email  rohit_prajapati@zohomail.in
 *
 */
public class ApiConstants {
    public static final String REST_URL_ROOT                    = "/api/v1";

    // Contact Rest Api Endpoints
    public static final String REST_URL_CONTACT_ROOT            = REST_URL_ROOT + "/contact";
    public static final String REST_URL_CONTACT                 = "/";
    public static final String REST_URL_COUNTRY_CODE            = "/country-code";
    public static final String REST_URL_PHONE_NUMBER            = "/phone-number";
    public static final String REST_URL_EMAIL                   = "/email";
    public static final String REST_URL_ADDRESS                 = "/address";
    public static final String REST_URL_CONTACT_DETAILS         = "/contact-details/{id}";

    // Question Rest Api Endpoints
    public static final String REST_URL_QUESTION_ROOT           = REST_URL_ROOT + "/question";
    public static final String REST_URL_QUESTION                = "/";
    public static final String REST_URL_TOPIC_TAG               = "/topic-tag";

    // Organisation Rest Api Endpoints
    public static final String REST_URL_ORGANISATION_ROOT       = REST_URL_ROOT + "/organisation";
    public static final String REST_URL_ORGANISATION            = "/";
    public static final String REST_URL_QUESTION_TAG            = "/tags";


    // App Rest api endpoints
    public static final String REST_URL_APP_ROOT                = REST_URL_ROOT + "/app";
    public static final String REST_URL_REFRESH_CACHE           = "/refresh-cache";
    public static final String REST_URL_QUESTIONS               = "/questions";

}
