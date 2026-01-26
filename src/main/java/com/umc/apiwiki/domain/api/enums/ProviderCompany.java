package com.umc.apiwiki.domain.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderCompany {

    // [국내 기업]
    KAKAO("카카오"),
    NAVER("네이버"),

    // [글로벌 빅테크]
    GOOGLE("Google"),
    MICROSOFT("Microsoft"),
    AMAZON("Amazon"), // AWS 포함
    META("Meta"),     // Facebook, Instagram
    IBM("IBM"),
    APPLE("Apple"),

    // [SaaS & API 전문 기업]
    SPOTIFY("Spotify"),
    ATLASSIAN("Atlassian"), // Jira, Trello
    OPEN_WEATHER("OpenWeather"),
    TELEGRAM("Telegram"),
    MIXPANEL("Mixpanel"),
    STRIPE("Stripe"),
    LINKEDIN("LinkedIn"),
    DISCORD("Discord"),
    ASANA("Asana"),
    WOLFRAM("Wolfram"),
    NOTION("Notion"),
    HUBSPOT("HubSpot"),
    PEXELS("Pexels"),
    SLACK("Slack"),
    OPEN_STREET_MAP("OpenStreetMap Foundation"),
    PAYPAL("PayPal"),
    REDDIT("Reddit"),
    DROPBOX("Dropbox"),
    DEEPL("DeepL"),
    TWILIO("Twilio"),
    NEWS_API("NewsAPI"),
    OPEN_AI("OpenAI"),
    MAILCHIMP("Mailchimp"),
    SHOPIFY("Shopify"),
    SQUARE("Square"),
    AMPLITUDE("Amplitude"),
    ZOOM("Zoom"),
    AUTOMATTIC("Automattic"), // WooCommerce
    UNSPLASH("Unsplash"),
    SENDGRID("SendGrid"),
    GIPHY("Giphy"),
    GITHUB("GitHub"),
    TWITTER("X(Twitter)"),
    SALESFORCE("Salesforce"),

    // [기타]
    OPEN_DATA("공공데이터포털"),
    ETC("기타");

    private final String description;
}