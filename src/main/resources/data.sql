-- 목차
-- 1. 관리자 유저 생성
-- ２. API 데이터 삽입
-- ３. 위키 데이터 삽입
-- 4. 카테고리 데이터 삽입
-- 5. API-카테고리 매핑


-- 데이터 삽입
-- 1. 관리자 유저 생성 (H2 예약어 충돌 방지를 위해 `users` 사용)
INSERT INTO `users` (
    email, name, nickname, password_hash, provider, created_at, updated_at, deleted_at
) VALUES (
             'admin@apiwiki.com',
             '관리자',
             'Admin',
             '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
             'LOCAL',
             NOW(),
             NOW(),
             NULL
         );


-- ２. API 데이터 삽입
-- JPA 서버용 API INSERT 문
-- long_description과 official_url은 기본값으로 채움

INSERT INTO apis (
    name, summary, long_description, official_url,
    avg_rating, view_counts, pricing_info,
    pricing_type, auth_type, provider_company,
    logo_url, created_at, updated_at
) VALUES
-- 1. Spotify API
(
    'Spotify API',
    '음악 스트리밍 및 플레이리스트 관리 API',
    'Spotify API는 음악 스트리밍 서비스의 공식 API로, 방대한 음악 카탈로그와 사용자 데이터에 접근하여 음악 관련 애플리케이션을 개발할 수 있습니다. 트랙, 앨범, 아티스트 검색과 플레이리스트 관리, 재생 제어 등의 기능을 제공합니다.',
    'https://developer.spotify.com/documentation/web-api',
    4.5, 0, '무료 티어 제공, 상업용은 유료',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://upload.wikimedia.org/wikipedia/commons/1/19/Spotify_logo_without_text.svg',
    NOW(), NOW()
),

-- 2. Jira API
(
    'Jira API',
    '이슈 추적 및 애자일 관리',
    'Atlassian의 프로젝트 관리 및 이슈 추적 플랫폼 Jira의 RESTful API입니다. 개발 팀의 워크플로우를 자동화하고 외부 시스템과 통합할 수 있으며, 이슈 관리, 프로젝트 관리, JQL 검색 등의 기능을 제공합니다.',
    'https://developer.atlassian.com/cloud/jira/platform/rest/v3/',
    4.3, 0, '무료 플랜 제공, 사용자당 과금',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://cdn.worldvectorlogo.com/logos/jira-1.svg',
    NOW(), NOW()
),

-- 3. Google Translate
(
    'Google Translate',
    '다국어 번역 서비스',
    'Google의 Neural Machine Translation을 기반으로 한 번역 API로 100개 이상의 언어를 지원합니다. 텍스트, 문서, 웹사이트를 자동으로 번역하며, 실시간 번역과 자동 언어 감지 기능을 제공합니다.',
    'https://cloud.google.com/translate/docs',
    4.7, 0, '월 50만 자 무료, 이후 유료',
    'PAID', 'API_KEY', 'GOOGLE',
    'https://upload.wikimedia.org/wikipedia/commons/d/d7/Google_Translate_logo.svg',
    NOW(), NOW()
),

-- 4. Weather API
(
    'Weather API',
    '실시간 날씨 및 기후 데이터',
    '전 세계 지역의 실시간 날씨 정보와 예보 데이터를 제공하는 API입니다. 현재 날씨, 시간별 예보, 주간 예보 등을 조회할 수 있으며, 기온, 습도, 풍속 등의 상세 정보를 제공합니다.',
    'https://openweathermap.org/api',
    4.2, 0, '무료 플랜 제공, 호출량에 따라 유료',
    'FREE', 'API_KEY', 'NAVER',
    'https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png',
    NOW(), NOW()
),

-- 5. Telegram Bot
(
    'Telegram Bot',
    '텔레그램 봇 생성 및 메시징 API',
    'Telegram 메신저를 위한 봇 개발 API입니다. 메시지 송수신, 인라인 키보드, 파일 전송, 그룹 관리 등의 기능을 제공하며, 자동 응답 시스템이나 알림 서비스를 구축할 수 있습니다.',
    'https://core.telegram.org/bots/api',
    4.6, 0, '완전 무료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://telegram.org/img/t_logo.png',
    NOW(), NOW()
),

-- 6. Mixpanel API
(
    'Mixpanel API',
    '사용자 행동 분석 플랫폼',
    '웹과 모바일 앱의 사용자 행동을 추적하고 분석하는 API입니다. 이벤트 트래킹, 사용자 프로필 관리, 퍼널 분석, 코호트 분석 등의 기능을 제공하여 데이터 기반 의사결정을 지원합니다.',
    'https://developer.mixpanel.com/reference/overview',
    4.4, 0, '무료 플랜 제공, 이벤트 수에 따라 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://mixpanel.com/wp-content/uploads/2021/07/mp-logo.svg',
    NOW(), NOW()
),

-- 7. Trello API
(
    'Trello API',
    '프로젝트 관리 보드 API',
    'Trello의 칸반 보드를 프로그래밍 방식으로 제어할 수 있는 API입니다. 보드, 리스트, 카드 생성 및 관리, 체크리스트, 라벨, 첨부파일 등의 기능을 자동화할 수 있습니다.',
    'https://developer.atlassian.com/cloud/trello/rest/',
    4.3, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://cdn.worldvectorlogo.com/logos/trello.svg',
    NOW(), NOW()
),

-- 8. Google Login
(
    'Google Login',
    '구글 계정으로 간편하게 로그인하는 OAuth 2.0 인증',
    'Google 계정을 통한 소셜 로그인을 구현할 수 있는 OAuth 2.0 기반 인증 API입니다. 사용자 인증, 프로필 정보 접근, 토큰 관리 등의 기능을 제공하여 안전하고 편리한 로그인 경험을 제공합니다.',
    'https://developers.google.com/identity/protocols/oauth2',
    4.8, 0, '무료',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg',
    NOW(), NOW()
),

-- 9. Stripe API
(
    'Stripe API',
    '온라인 결제 처리 API',
    '온라인 결제를 처리하는 글로벌 결제 플랫폼 API입니다. 카드 결제, 구독 관리, 인보이스 발행, 환불 처리, 결제 분석 등의 기능을 제공하며, 다양한 결제 수단을 지원합니다.',
    'https://stripe.com/docs/api',
    4.7, 0, '거래당 수수료 부과',
    'PAID', 'API_KEY', 'KAKAO',
    'https://images.ctfassets.net/fzn2n1nzq965/HTTOloNPhisV9P4hlMPNA/cacf1bb88b9fc492dfad34378d844280/Stripe_icon_-_square.svg',
    NOW(), NOW()
),

-- 10. LinkedIn API
(
    'LinkedIn API',
    '링크드인 프로필 및 네트워킹',
    '비즈니스 소셜 네트워크 LinkedIn의 API입니다. 사용자 프로필 조회, 포스트 공유, 회사 페이지 관리, 네트워크 연결 등의 기능을 제공하여 전문적인 네트워킹 기능을 구현할 수 있습니다.',
    'https://docs.microsoft.com/en-us/linkedin/',
    4.1, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png',
    NOW(), NOW()
),

-- 11. Google Cloud Vision
(
    'Google Cloud Vision',
    '이미지 인식 및 OCR API',
    'Google의 기계학습 모델을 활용한 이미지 분석 API입니다. 객체 감지, 얼굴 인식, 텍스트 추출(OCR), 로고 감지, 부적절한 콘텐츠 탐지 등의 기능을 제공합니다.',
    'https://cloud.google.com/vision/docs',
    4.6, 0, '월 1000건 무료, 이후 건당 과금',
    'PAID', 'API_KEY', 'GOOGLE',
    'https://www.gstatic.com/devrel-devsite/prod/v2210deb8920cd4a55bd580441aa58e7853afc04b39a9d9ac4198e1cd7fbe04ef/cloud/images/cloud-logo.svg',
    NOW(), NOW()
),

-- 12. Instagram API
(
    'Instagram API',
    '인스타그램 포스트 및 미디어 관리',
    'Instagram의 콘텐츠와 데이터에 접근할 수 있는 API입니다. 포스트 게시, 미디어 조회, 댓글 관리, 인사이트 분석 등의 기능을 제공하며, 비즈니스 계정 관리를 지원합니다.',
    'https://developers.facebook.com/docs/instagram-api',
    4.0, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://upload.wikimedia.org/wikipedia/commons/9/95/Instagram_logo_2022.svg',
    NOW(), NOW()
),

-- 13. Discord API
(
    'Discord API',
    '디스코드 봇 및 서버 관리 API',
    'Discord 플랫폼의 봇 개발과 서버 관리를 위한 API입니다. 메시지 송수신, 채널 관리, 역할 설정, 음성 연결 등의 기능을 제공하여 커뮤니티 자동화를 구현할 수 있습니다.',
    'https://discord.com/developers/docs/intro',
    4.5, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://assets-global.website-files.com/6257adef93867e50d84d30e2/636e0a6a49cf127bf92de1e2_icon_clyde_blurple_RGB.png',
    NOW(), NOW()
),

-- 14. Asana API
(
    'Asana API',
    '팀 작업 및 프로젝트 추적',
    'Asana 프로젝트 관리 플랫폼의 API입니다. 태스크 생성 및 할당, 프로젝트 관리, 팀 협업, 워크플로우 자동화 등의 기능을 제공하여 업무 효율성을 높일 수 있습니다.',
    'https://developers.asana.com/docs',
    4.3, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://luna1.co/eb0187.png',
    NOW(), NOW()
),

-- 15. Wolfram Alpha
(
    'Wolfram Alpha',
    '계산 지식 엔진 API',
    '복잡한 수학 계산, 과학 데이터 분석, 통계 처리 등을 제공하는 계산 지식 엔진 API입니다. 자연어 질의를 통해 정확한 계산 결과와 시각화된 데이터를 제공합니다.',
    'https://products.wolframalpha.com/api/',
    4.4, 0, '월 2000건 무료, 이후 유료',
    'PAID', 'API_KEY', 'KAKAO',
    'https://www.wolframalpha.com/_next/static/images/share_HJWi6D.png',
    NOW(), NOW()
),

-- 16. Notion API
(
    'Notion API',
    '노션 문서 및 데이터베이스 관리',
    'Notion 워크스페이스의 페이지와 데이터베이스를 프로그래밍 방식으로 제어할 수 있는 API입니다. 페이지 생성, 블록 편집, 데이터베이스 쿼리, 콘텐츠 동기화 등의 기능을 제공합니다.',
    'https://developers.notion.com/',
    4.5, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://upload.wikimedia.org/wikipedia/commons/4/45/Notion_app_logo.png',
    NOW(), NOW()
),

-- 17. HubSpot API
(
    'HubSpot API',
    'CRM 및 마케팅 자동화',
    'HubSpot의 CRM, 마케팅, 영업, 고객 서비스 기능에 접근할 수 있는 API입니다. 연락처 관리, 이메일 캠페인, 리드 추적, 분석 리포트 등의 기능을 자동화할 수 있습니다.',
    'https://developers.hubspot.com/docs/api/overview',
    4.3, 0, '무료 플랜 제공, 기능에 따라 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://www.hubspot.com/hubfs/HubSpot_Logos/HubSpot-Inversed-Favicon.png',
    NOW(), NOW()
),

-- 18. Pexels API
(
    'Pexels API',
    '무료 스톡 이미지 및 비디오',
    '고품질의 무료 스톡 이미지와 비디오를 제공하는 API입니다. 키워드 검색, 인기 콘텐츠 조회, 큐레이션된 컬렉션 접근 등의 기능을 제공하며, 상업적 용도로도 자유롭게 사용할 수 있습니다.',
    'https://www.pexels.com/api/',
    4.6, 0, '완전 무료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://images.pexels.com/lib/api/pexels.png',
    NOW(), NOW()
),

-- 19. Slack API
(
    'Slack API',
    '슬랙 메시징 및 워크스페이스 통합 API',
    'Slack 워크스페이스의 메시징과 협업 기능에 접근할 수 있는 API입니다. 메시지 전송, 채널 관리, 파일 공유, 봇 개발, 워크플로우 자동화 등의 기능을 제공합니다.',
    'https://api.slack.com/',
    4.7, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://a.slack-edge.com/80588/marketing/img/icons/icon_slack_hash_colored.png',
    NOW(), NOW()
),

-- 20. OpenStreetMap
(
    'OpenStreetMap',
    '오픈소스 기반 전 세계 지도 데이터 제공',
    '오픈소스 기반의 전 세계 지도 데이터를 제공하는 API입니다. 지도 타일, 지오코딩, 경로 탐색, POI 검색 등의 기능을 무료로 사용할 수 있으며, 커뮤니티 기반으로 지속적으로 업데이트됩니다.',
    'https://wiki.openstreetmap.org/wiki/API',
    4.4, 0, '완전 무료 (공정 사용 정책)',
    'FREE', 'API_KEY', 'KAKAO',
    'https://wiki.openstreetmap.org/w/images/7/79/Public-images-osm_logo.svg',
    NOW(), NOW()
),

-- 21. PayPal API
(
    'PayPal API',
    '글로벌 결제 및 송금 API',
    'PayPal을 통한 온라인 결제와 송금을 처리하는 API입니다. 결제 처리, 구독 관리, 인보이스 발행, 환불, 지불 추적 등의 기능을 제공하며, 전 세계적으로 사용 가능합니다.',
    'https://developer.paypal.com/docs/api/overview/',
    4.2, 0, '거래당 수수료 부과',
    'PAID', 'OAUTH2', 'KAKAO',
    'https://www.paypalobjects.com/webstatic/icon/pp258.png',
    NOW(), NOW()
),

-- 22. Reddit API
(
    'Reddit API',
    '레딧 포스트 및 커뮤니티 데이터',
    'Reddit 커뮤니티의 포스트, 댓글, 사용자 데이터에 접근할 수 있는 API입니다. 서브레딧 조회, 포스트 작성, 댓글 관리, 투표, 사용자 프로필 등의 기능을 제공합니다.',
    'https://www.reddit.com/dev/api/',
    4.1, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://www.redditstatic.com/desktop2x/img/favicon/android-icon-192x192.png',
    NOW(), NOW()
),

-- 23. Dropbox API
(
    'Dropbox API',
    '파일 동기화 및 공유 API',
    'Dropbox 클라우드 스토리지의 파일과 폴더를 관리할 수 있는 API입니다. 파일 업로드/다운로드, 공유 링크 생성, 폴더 구조 관리, 파일 검색 등의 기능을 제공합니다.',
    'https://www.dropbox.com/developers/documentation',
    4.3, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://cfl.dropboxstatic.com/static/images/logo_catalog/dropbox_webclip_152.png',
    NOW(), NOW()
),

-- 24. DeepL API
(
    'DeepL API',
    '고품질 AI 기반 번역',
    'AI 기반의 고품질 번역을 제공하는 API입니다. 자연스러운 문맥 이해와 정확한 번역으로 Google Translate보다 높은 품질을 자랑하며, 30개 이상의 언어를 지원합니다.',
    'https://www.deepl.com/docs-api',
    4.8, 0, '월 50만 자 무료, 이후 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://static.deepl.com/img/logo/DeepL_Logo_darkBlue_v2.svg',
    NOW(), NOW()
),

-- 25. Twilio SMS
(
    'Twilio SMS',
    'SMS 및 음성 통신 API',
    'SMS 문자, 음성 통화, 영상 통화 등의 통신 기능을 제공하는 API입니다. 전 세계적으로 메시지 전송과 전화 기능을 구현할 수 있으며, 2단계 인증, 알림 서비스 등에 활용됩니다.',
    'https://www.twilio.com/docs/sms',
    4.5, 0, '사용량 기반 과금',
    'PAID', 'API_KEY', 'KAKAO',
    'https://www.twilio.com/content/dam/twilio-com/global/en/blog/legacy/2017/Twilio-Mark-Red.png',
    NOW(), NOW()
),

-- 26. Azure Cognitive
(
    'Azure Cognitive',
    '인지 서비스 및 AI API',
    'Microsoft Azure의 AI 인지 서비스 API입니다. 컴퓨터 비전, 음성 인식, 자연어 처리, 감정 분석 등의 다양한 AI 기능을 제공하여 지능형 애플리케이션을 구축할 수 있습니다.',
    'https://azure.microsoft.com/en-us/services/cognitive-services/',
    4.4, 0, '무료 티어 제공, 사용량에 따라 유료',
    'PAID', 'API_KEY', 'KAKAO',
    'https://azure.microsoft.com/svghandler/cognitive-services/',
    NOW(), NOW()
),

-- 27. News API
(
    'News API',
    '전 세계 뉴스 헤드라인 및 기사',
    '전 세계 뉴스 소스의 헤드라인과 기사를 실시간으로 제공하는 API입니다. 키워드 검색, 카테고리별 조회, 소스 필터링 등의 기능으로 최신 뉴스를 애플리케이션에 통합할 수 있습니다.',
    'https://newsapi.org/docs',
    4.3, 0, '무료 플랜 제공, 요청 수 제한',
    'FREE', 'API_KEY', 'KAKAO',
    'https://newsapi.org/images/n-logo-border.png',
    NOW(), NOW()
),

-- 28. IBM Watson
(
    'IBM Watson',
    'AI 및 머신러닝 플랫폼',
    'IBM의 AI 플랫폼으로 자연어 처리, 음성 인식, 이미지 분석, 감정 분석 등의 기능을 제공합니다. 챗봇, 추천 시스템, 데이터 분석 등 다양한 AI 솔루션을 구축할 수 있습니다.',
    'https://cloud.ibm.com/apidocs/watson',
    4.2, 0, '무료 티어 제공, 사용량에 따라 유료',
    'PAID', 'API_KEY', 'KAKAO',
    'https://www.ibm.com/brand/experience-guides/developer/b1db1ae501d522a1a4b49613fe07c9f1/01_8-bar-positive.svg',
    NOW(), NOW()
),

-- 29. OpenAI GPT-4
(
    'OpenAI GPT-4',
    '최신 AI 언어 모델로 대화, 텍스트 생성, 분석 등 지원',
    'OpenAI의 최신 대규모 언어 모델 API입니다. 자연어 이해, 텍스트 생성, 번역, 요약, 코드 생성 등 다양한 언어 처리 작업을 수행할 수 있으며, 챗봇과 AI 어시스턴트 개발에 활용됩니다.',
    'https://platform.openai.com/docs/api-reference',
    4.9, 0, '토큰당 과금',
    'PAID', 'API_KEY', 'GOOGLE',
    'https://openai.com/favicon.ico',
    NOW(), NOW()
),

-- 30. Mailchimp
(
    'Mailchimp',
    '이메일 마케팅 자동화 API',
    '이메일 마케팅 캠페인을 관리하고 자동화하는 API입니다. 구독자 관리, 이메일 템플릿 생성, 캠페인 전송, 분석 리포트 등의 기능을 제공하여 효과적인 이메일 마케팅을 지원합니다.',
    'https://mailchimp.com/developer/',
    4.3, 0, '무료 플랜 제공, 구독자 수에 따라 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://eep.io/images/yzco4xsimv0y/5fJHr9W3tKKCW65hQgXG7d/a761f1a83f7854d45c1cf0c2918c00ad/MC_Logo.svg',
    NOW(), NOW()
),

-- 31. Google Analytics
(
    'Google Analytics',
    '웹사이트 분석 및 추적',
    '웹사이트와 앱의 트래픽과 사용자 행동을 추적하고 분석하는 API입니다. 페이지뷰, 이벤트, 전환율, 사용자 경로 등의 데이터를 수집하고 분석하여 마케팅 최적화에 활용할 수 있습니다.',
    'https://developers.google.com/analytics',
    4.6, 0, '무료',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://www.gstatic.com/analytics-suite/header/suite/v2/ic_analytics.svg',
    NOW(), NOW()
),

-- 32. Shopify API
(
    'Shopify API',
    '전자상거래 스토어 관리',
    'Shopify 온라인 스토어의 상품, 주문, 고객 데이터를 관리하는 API입니다. 상품 등록, 재고 관리, 주문 처리, 결제 연동 등의 기능을 자동화하여 전자상거래 운영을 효율화할 수 있습니다.',
    'https://shopify.dev/docs/api',
    4.4, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://cdn.shopify.com/shopifycloud/brochure/assets/brand-assets/shopify-logo-primary-logo-456baa801ee66a0a435671082365958316831c9960c480451dd0330bcdae304f.svg',
    NOW(), NOW()
),

-- 33. AWS Rekognition
(
    'AWS Rekognition',
    '이미지 및 비디오 분석',
    'Amazon의 이미지 및 비디오 분석 AI 서비스입니다. 얼굴 인식, 객체 감지, 장면 분석, 텍스트 추출, 유명인 인식 등의 기능을 제공하여 시각적 콘텐츠를 자동으로 분석할 수 있습니다.',
    'https://docs.aws.amazon.com/rekognition/',
    4.5, 0, '사용량 기반 과금',
    'PAID', 'API_KEY', 'GOOGLE',
    'https://d2908q01vomqb2.cloudfront.net/77de68daecd823babbb58edb1c8e14d7106e83bb/2021/06/23/Amazon-Rekognition-Logo.png',
    NOW(), NOW()
),

-- 34. Google Drive
(
    'Google Drive',
    '클라우드 파일 저장 및 공유',
    'Google Drive 클라우드 스토리지의 파일과 폴더를 관리하는 API입니다. 파일 업로드/다운로드, 공유 권한 설정, 검색, 버전 관리 등의 기능을 제공하여 클라우드 기반 파일 관리를 구현할 수 있습니다.',
    'https://developers.google.com/drive/api',
    4.6, 0, '무료 (15GB 제공)',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://ssl.gstatic.com/docs/doclist/images/drive_2022q3_32dp.png',
    NOW(), NOW()
),

-- 35. Square API
(
    'Square API',
    '결제 및 POS 시스템 API',
    'Square의 결제 처리와 POS 시스템 API입니다. 카드 결제, 모바일 결제, 인보이스, 재고 관리, 고객 관리 등의 기능을 제공하여 오프라인과 온라인 비즈니스를 통합 관리할 수 있습니다.',
    'https://developer.squareup.com/docs',
    4.4, 0, '거래당 수수료 부과',
    'PAID', 'OAUTH2', 'KAKAO',
    'https://images.ctfassets.net/2d5q1td6cyxq/5g0qkH0wLqQ0k8QgoqQomc/3bc1c2f85f85b8f6c1fa14f8e2e7aeaa/sq-logo.svg',
    NOW(), NOW()
),

-- 36. Amplitude API
(
    'Amplitude API',
    '제품 분석 및 인사이트',
    '제품 사용 데이터를 분석하고 인사이트를 제공하는 API입니다. 사용자 행동 추적, 코호트 분석, 퍼널 분석, A/B 테스트 결과 등을 통해 데이터 기반 제품 의사결정을 지원합니다.',
    'https://www.docs.developers.amplitude.com/',
    4.5, 0, '무료 플랜 제공, 이벤트 수에 따라 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://amplitude.com/favicon.ico',
    NOW(), NOW()
),

-- 37. Zoom API
(
    'Zoom API',
    '비디오 회의 및 웨비나 관리',
    'Zoom 비디오 회의 플랫폼의 API입니다. 회의 생성 및 관리, 참가자 제어, 녹화, 웨비나 설정, 통계 조회 등의 기능을 자동화하여 화상 회의 솔루션을 구축할 수 있습니다.',
    'https://marketplace.zoom.us/docs/api-reference/introduction',
    4.4, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://st1.zoom.us/static/5.15.11.4272/image/new/ZoomLogo.png',
    NOW(), NOW()
),

-- 38. WooCommerce
(
    'WooCommerce',
    '워드프레스 기반 쇼핑몰 API',
    'WordPress 플랫폼의 전자상거래 플러그인 WooCommerce API입니다. 상품 관리, 주문 처리, 고객 데이터, 결제 연동 등의 기능을 제공하여 온라인 쇼핑몰을 효율적으로 운영할 수 있습니다.',
    'https://woocommerce.github.io/woocommerce-rest-api-docs/',
    4.2, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://woocommerce.com/wp-content/themes/woo/images/logo-woocommerce.svg',
    NOW(), NOW()
),

-- 39. Unsplash API
(
    'Unsplash API',
    '고품질 무료 이미지 라이브러리',
    '고품질의 무료 이미지를 제공하는 API입니다. 200만 장 이상의 전문 사진을 검색하고 다운로드할 수 있으며, 상업적 용도로도 자유롭게 사용 가능한 이미지를 제공합니다.',
    'https://unsplash.com/developers',
    4.7, 0, '무료 (출처 표시 권장)',
    'FREE', 'API_KEY', 'KAKAO',
    'https://unsplash.com/favicon-32x32.png',
    NOW(), NOW()
),

-- 40. SendGrid
(
    'SendGrid',
    '이메일 전송 및 마케팅 API',
    '대량 이메일 전송과 이메일 마케팅을 위한 API입니다. 트랜잭션 이메일, 마케팅 캠페인, 이메일 검증, 전송 통계 등의 기능을 제공하여 안정적인 이메일 서비스를 구축할 수 있습니다.',
    'https://docs.sendgrid.com/api-reference',
    4.4, 0, '월 100건 무료, 이후 사용량 기반',
    'FREE', 'API_KEY', 'KAKAO',
    'https://sendgrid.com/wp-content/themes/sgdotcom/pages/resource/brand/2016/SendGrid-Logomark.png',
    NOW(), NOW()
),

-- 41. Naver Papago
(
    'Naver Papago',
    '네이버 파파고 번역 API로 다양한 언어 번역',
    '네이버의 AI 기반 번역 API로 한국어를 포함한 다양한 언어를 지원합니다. 텍스트 번역, 웹사이트 번역, 이미지 번역(OCR) 등의 기능을 제공하며, 한국어 번역에 특화되어 있습니다.',
    'https://developers.naver.com/docs/papago/',
    4.6, 0, '일 10,000자 무료, 이후 유료',
    'FREE', 'API_KEY', 'NAVER',
    'https://ssl.pstatic.net/static/nid/papago/img_papago_191204.png',
    NOW(), NOW()
),

-- 42. Youtube API
(
    'Youtube API',
    '강력한 동영상 플랫폼 API로 업로드, 검색, 재생 등 다양한 기능 제공',
    'YouTube 플랫폼의 동영상과 채널 데이터에 접근하는 API입니다. 동영상 업로드, 검색, 재생목록 관리, 댓글, 통계 조회 등의 기능을 제공하여 동영상 기반 애플리케이션을 개발할 수 있습니다.',
    'https://developers.google.com/youtube/v3',
    4.7, 0, '무료 (일일 할당량 제한)',
    'FREE', 'API_KEY', 'GOOGLE',
    'https://www.youtube.com/s/desktop/f506bd45/img/favicon_32.png',
    NOW(), NOW()
),

-- 43. Giphy API
(
    'Giphy API',
    'GIF 검색 및 임베드 API',
    '세계 최대의 GIF 라이브러리를 제공하는 API입니다. GIF 검색, 트렌딩 GIF, 스티커, 랜덤 GIF 등의 기능을 제공하여 애플리케이션에 재미있는 GIF 콘텐츠를 통합할 수 있습니다.',
    'https://developers.giphy.com/docs/api/',
    4.5, 0, '무료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://giphy.com/static/img/favicon.png',
    NOW(), NOW()
),

-- 44. AWS S3
(
    'AWS S3',
    '클라우드 스토리지 및 파일 관리',
    'Amazon Web Services의 객체 스토리지 서비스 API입니다. 파일 업로드/다운로드, 버킷 관리, 접근 권한 설정, 정적 웹사이트 호스팅 등의 기능을 제공하여 확장 가능한 클라우드 스토리지를 구축할 수 있습니다.',
    'https://docs.aws.amazon.com/s3/index.html',
    4.6, 0, '사용량 기반 과금',
    'PAID', 'API_KEY', 'GOOGLE',
    'https://a0.awsstatic.com/libra-css/images/logos/aws_logo_smile_1200x630.png',
    NOW(), NOW()
),

-- 45. GitHub API
(
    'GitHub API',
    'GitHub 저장소 및 프로젝트 관리 API',
    'GitHub 플랫폼의 저장소, 이슈, PR 등을 관리하는 API입니다. 코드 저장소 관리, 이슈 트래킹, PR 리뷰, Actions 워크플로우, 협업 도구 등의 기능을 자동화할 수 있습니다.',
    'https://docs.github.com/en/rest',
    4.7, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://github.githubassets.com/favicons/favicon.svg',
    NOW(), NOW()
),

-- 46. Firebase
(
    'Firebase',
    '백엔드 서비스 및 실시간 데이터베이스',
    'Google의 모바일 및 웹 앱 개발 플랫폼입니다. 실시간 데이터베이스, 인증, 클라우드 스토리지, 호스팅, 푸시 알림 등의 백엔드 서비스를 제공하여 빠른 앱 개발을 지원합니다.',
    'https://firebase.google.com/docs',
    4.7, 0, '무료 플랜 제공, 사용량에 따라 유료',
    'FREE', 'OAUTH2', 'GOOGLE',
    'https://www.gstatic.com/devrel-devsite/prod/v2210deb8920cd4a55bd580441aa58e7853afc04b39a9d9ac4198e1cd7fbe04ef/firebase/images/touchicon-180.png',
    NOW(), NOW()
),

-- 47. Twitter API
(
    'Twitter API',
    '트위터 트윗 및 소셜 데이터 API',
    'X(구 Twitter) 플랫폼의 트윗과 소셜 데이터에 접근하는 API입니다. 트윗 작성, 검색, 타임라인 조회, 팔로우 관리, DM 등의 기능을 제공하여 소셜 미디어 통합 서비스를 구축할 수 있습니다.',
    'https://developer.twitter.com/en/docs',
    4.0, 0, '무료 플랜 제한적, 유료 플랜 필요',
    'PAID', 'OAUTH2', 'KAKAO',
    'https://abs.twimg.com/favicons/twitter.2.ico',
    NOW(), NOW()
),

-- 48. Salesforce API
(
    'Salesforce API',
    '클라우드 기반 CRM 플랫폼',
    'Salesforce CRM 플랫폼의 API로 고객 관계 관리를 자동화합니다. 리드, 기회, 계정, 연락처 관리, 영업 프로세스 자동화, 리포트 생성 등의 기능을 제공합니다.',
    'https://developer.salesforce.com/docs/apis',
    4.5, 0, '플랜별 가격',
    'PAID', 'OAUTH2', 'KAKAO',
    'https://www.salesforce.com/content/dam/sfdc-docs/www/logos/logo-salesforce.svg',
    NOW(), NOW()
),

-- 49. Kakao Maps
(
    'Kakao Maps',
    '카카오맵 기반 한국 지역 지도 및 위치 서비스',
    '카카오의 지도 서비스 API로 한국 지역에 최적화되어 있습니다. 지도 표시, 주소 검색, 좌표 변환, 길찾기, 로드뷰 등의 기능을 제공하여 위치 기반 서비스를 구축할 수 있습니다.',
    'https://apis.map.kakao.com/',
    4.6, 0, '일 30만건 무료, 이후 유료',
    'FREE', 'API_KEY', 'KAKAO',
    'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png',
    NOW(), NOW()
),

-- 50. Facebook Graph
(
    'Facebook Graph',
    '페이스북 그래프 API로 소셜 데이터 접근',
    'Meta의 Facebook 플랫폼 API입니다. 사용자 프로필, 페이지, 그룹, 이벤트 관리, 포스트 게시, 광고 관리 등의 기능을 제공하여 소셜 미디어 통합 서비스를 개발할 수 있습니다.',
    'https://developers.facebook.com/docs/graph-api',
    4.2, 0, '무료',
    'FREE', 'OAUTH2', 'KAKAO',
    'https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico',
    NOW(), NOW()
);


-- ３. 위키 데이터 삽입
INSERT INTO `wiki` (api_id, user_id, content_md, created_at, updated_at) VALUES

-- 1. Spotify API
(
    1,
    1,
    '## 개요
Spotify API는 음악 스트리밍 서비스인 Spotify의 공식 API입니다. 개발자들이 Spotify의 방대한 음악 카탈로그와 사용자 데이터에 접근하여 음악 관련 애플리케이션을 개발할 수 있도록 지원합니다.

## 주요 기능

### 음악 검색 및 탐색
Spotify의 전체 음악 카탈로그에서 트랙, 앨범, 아티스트, 플레이리스트를 검색할 수 있습니다. 메타데이터, 오디오 분석, 추천 알고리즘에 접근 가능합니다.

### 플레이리스트 관리
사용자 플레이리스트를 생성, 수정, 삭제할 수 있으며, 공개 플레이리스트를 탐색하고 팔로우할 수 있습니다.

### 재생 제어
Spotify Connect를 통해 활성 기기에서 재생을 제어하고, 재생 상태를 확인하며, 재생 대기열을 관리할 수 있습니다.

### 사용자 라이브러리
사용자가 저장한 트랙, 앨범, 아티스트에 접근하여 개인화된 음악 경험을 제공할 수 있습니다.

## 인증 방식
OAuth 2.0 프로토콜을 사용하며, Authorization Code Flow, Implicit Grant Flow, Client Credentials Flow 등 다양한 인증 방식을 지원합니다.

## API 제한사항
Free 티어는 개인 프로젝트용으로 적합하며, 상업적 용도로는 Extended Quota 플랜이 필요합니다. Rate limit은 30초당 약 180 요청으로 설정되어 있습니다.

## 사용 사례
음악 추천 앱, 플레이리스트 생성 도구, 음악 분석 대시보드, 소셜 음악 공유 플랫폼 등 다양한 음악 관련 애플리케이션 개발에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 2. Jira API
(
    2,
    1,
    '## 개요
Jira API는 Atlassian의 프로젝트 관리 및 이슈 추적 플랫폼인 Jira의 RESTful API입니다. 개발 팀의 워크플로우를 자동화하고 외부 시스템과 통합할 수 있게 해줍니다.

## 주요 기능

### 이슈 관리
이슈를 생성, 수정, 삭제하고 상태를 업데이트할 수 있습니다. 커스텀 필드와 워크플로우를 프로그래밍 방식으로 제어할 수 있습니다.

### 프로젝트 관리
프로젝트 생성 및 설정 관리, 프로젝트 구성원 및 권한 관리, 프로젝트 메타데이터에 접근할 수 있습니다.

### 검색 및 필터링
JQL을 사용한 고급 이슈 검색이 가능하며, 저장된 필터를 관리하고 복잡한 쿼리를 실행할 수 있습니다.

### 워크플로우 자동화
상태 전환 자동화, 알림 설정, 승인 프로세스 구축 등 워크플로우를 커스터마이징할 수 있습니다.

## 인증 방식
OAuth 2.0 및 Basic Authentication을 지원합니다. Jira Cloud의 경우 OAuth 2.0이 권장되며, Jira Server/Data Center는 Basic Auth도 지원합니다.

## API 버전
REST API v3가 최신 버전이며, v2도 여전히 지원됩니다. Cloud와 Server/Data Center 버전 간 일부 차이가 있습니다.

## Rate Limiting
Jira Cloud는 API 호출에 대해 제한이 있으며, 구체적인 한도는 사용자의 Jira 플랜에 따라 다릅니다.

## 사용 사례
CI/CD 파이프라인 통합, 자동화된 이슈 생성 및 업데이트, 커스텀 대시보드 구축, 다른 개발 도구와의 연동 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 3. Google Translate (수정됨: 문법 오류 해결)
(
    3,
    1,
    '# Google Translate API

Google의 Neural Machine Translation을 기반으로 한 번역 API로, 100개 이상의 언어를 지원합니다.

## 개요

Google Translate API는 텍스트, 문서, 웹사이트를 자동으로 번역하는 강력한 번역 서비스입니다. 구글의 최신 신경망 기계 번역(NMT) 기술을 활용하여 높은 품질의 번역을 제공합니다.

## 주요 기능

**텍스트 번역**
- 100개 이상의 언어 지원
- 실시간 번역
- 자동 언어 감지
- 대량 텍스트 처리

**문서 번역**
- DOCX, PPTX, PDF 형식 지원
- 원본 서식 유지
- 페이지 단위 과금

**고급 기능**
- AutoML 커스텀 모델 학습
- Translation LLM (고급 AI 번역)
- Adaptive Translation (적응형 번역)
- Translation Hub (문서 번역 플랫폼)

## 지원 언어

한국어, 영어, 일본어, 중국어, 스페인어, 프랑스어, 독일어 등 100개 이상의 언어를 지원합니다.

## 번역 모델 종류

**Neural Machine Translation (NMT)**
구글의 표준 번역 모델로 대부분의 언어 쌍에서 높은 품질을 제공합니다.

**Custom Models (AutoML)**
특정 도메인이나 용어에 맞춘 커스텀 번역 모델을 학습할 수 있습니다.

**Translation LLM**
대규모 언어 모델 기반의 고급 번역 기능을 제공합니다.

## 인증 방식

**API Key**
- 간단한 인증 방식
- 요청 URL에 key 파라미터 추가
- 제한된 기능

**Service Account**
- OAuth 2.0 기반
- 전체 기능 접근 가능
- JSON 키 파일 사용

## 사용 예시

**기본 텍스트 번역**
```bash
curl -X POST "[https://translation.googleapis.com/language/translate/v2?key=YOUR_API_KEY](https://translation.googleapis.com/language/translate/v2?key=YOUR_API_KEY)" \
  -H "Content-Type: application/json" \
  -d ''{"q": "Hello world", "source": "en", "target": "ko"}''
```',
    NOW(),
    NOW()
),

-- 4. Weather API
(
    4,
    1,
    '## 개요
OpenWeather API는 전 세계의 실시간 날씨 데이터, 예보, 과거 날씨 정보를 제공하는 종합 기상 정보 API입니다. 200,000개 이상의 도시에 대한 날씨 데이터에 접근할 수 있습니다.

## 주요 기능

### 현재 날씨 데이터
실시간 온도, 습도, 기압, 풍속, 구름 정보 등을 제공합니다. 도시 이름, 좌표, 우편번호로 검색 가능합니다.

### 날씨 예보
5일/3시간 단위 예보, 16일 일일 예보를 제공하며, 시간별 상세 기상 정보를 확인할 수 있습니다.

### 과거 날씨 데이터
40년 이상의 과거 기상 데이터에 접근하여 기후 분석 및 통계 작업이 가능합니다.

### 기상 경보
악천후 경보 및 기상 주의보 정보를 실시간으로 받을 수 있습니다.

## 데이터 형식
JSON 및 XML 형식으로 응답을 제공하며, 단위는 미터법, 임페리얼, 켈빈 중 선택 가능합니다.

## 인증 방식
API 키 기반 인증을 사용합니다. 요청 시 URL 파라미터 또는 헤더에 API 키를 포함해야 합니다.

## 지원 언어
40개 이상의 언어로 날씨 설명을 제공합니다.

## 사용 사례
날씨 앱 개발, 농업 및 기후 분석, 물류 최적화, 여행 계획 애플리케이션, 스마트 홈 자동화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 5. Telegram Bot
(
    5,
    1,
    '## 개요
Telegram Bot API는 메시징 플랫폼 Telegram에서 봇을 생성하고 관리할 수 있는 HTTP 기반 인터페이스입니다. 자동화된 메시징, 사용자 상호작용, 콘텐츠 전달 등이 가능합니다.

## 주요 기능

### 메시지 송수신
텍스트, 이미지, 비디오, 오디오, 문서 등 다양한 형태의 메시지를 보내고 받을 수 있습니다. 인라인 키보드와 커스텀 키보드를 지원합니다.

### 그룹 및 채널 관리
그룹 채팅 관리, 채널 포스팅, 관리자 권한 제어 등이 가능합니다.

### 인라인 모드
사용자가 다른 채팅에서도 봇의 기능을 사용할 수 있게 하는 인라인 쿼리를 지원합니다.

### 결제 시스템
Telegram Payments를 통해 봇 내에서 직접 상품이나 서비스를 판매할 수 있습니다.

### 파일 처리
최대 2GB 크기의 파일을 업로드하고 다운로드할 수 있습니다.

## 봇 생성 방법
BotFather와 대화하여 새 봇을 생성하고 API 토큰을 받습니다. 이 토큰을 사용하여 Bot API에 접근합니다.

## Webhook vs Long Polling
업데이트를 받는 두 가지 방식을 지원합니다. Webhook은 실시간 알림을, Long Polling은 주기적인 체크를 제공합니다.

## 보안
HTTPS를 통한 안전한 통신, 엔드투엔드 암호화 지원 등 보안 기능이 강화되어 있습니다.

## 사용 사례
고객 서비스 봇, 뉴스 및 알림 전송, 커뮤니티 관리, 자동화된 작업 처리, 게임 및 엔터테인먼트 봇 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 6. Mixpanel API
(
    6,
    1,
    '## 개요
Mixpanel API는 제품 분석 플랫폼인 Mixpanel의 데이터에 프로그래밍 방식으로 접근할 수 있게 해주는 RESTful API입니다. 사용자 행동 추적, 분석, 인사이트 도출에 활용됩니다.

## 주요 기능

### 이벤트 트래킹
사용자 행동을 이벤트로 기록하고, 커스텀 속성을 추가하여 상세한 분석이 가능합니다.

### 사용자 프로필 관리
개별 사용자 프로필을 생성하고 업데이트하며, 사용자 속성을 관리할 수 있습니다.

### 데이터 쿼리
저장된 이벤트 데이터를 쿼리하고, 세그먼트별 분석, 깔때기 분석, 리텐션 분석 등을 수행할 수 있습니다.

### Funnel Analysis
사용자 전환 경로를 분석하고 각 단계에서의 이탈률을 파악할 수 있습니다.

### Cohort Analysis
특정 기간에 가입한 사용자 그룹의 행동 패턴을 시간에 따라 추적할 수 있습니다.

## API 종류

### Ingestion API
이벤트와 사용자 데이터를 Mixpanel로 전송하는 데 사용됩니다.

### Query API
저장된 데이터를 조회하고 분석 결과를 가져오는 데 사용됩니다.

## 인증 방식
Project Token 및 API Secret을 사용한 인증을 지원합니다. 민감한 작업에는 API Secret이 필요합니다.

## 데이터 프라이버시
GDPR 및 CCPA 준수를 위한 데이터 삭제 및 내보내기 기능을 제공합니다.

## 사용 사례
모바일 앱 및 웹 서비스의 사용자 행동 분석, A/B 테스트 결과 추적, 사용자 참여도 측정, 제품 개선을 위한 인사이트 도출 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 7. Trello API
(
    7,
    1,
    '## 개요
Trello API는 Atlassian의 시각적 프로젝트 관리 도구인 Trello의 RESTful API입니다. 보드, 리스트, 카드를 프로그래밍 방식으로 생성하고 관리할 수 있습니다.

## 주요 기능

### 보드 관리
보드를 생성, 수정, 삭제하고 보드 설정을 변경할 수 있습니다. 배경 이미지 설정, 권한 관리, 보드 멤버 초대가 가능합니다.

### 카드 작업
카드 생성 및 업데이트, 체크리스트 추가, 첨부파일 관리, 레이블 및 멤버 할당, 마감일 설정이 가능합니다.

### 리스트 관리
리스트를 생성하고 순서를 변경하며, 리스트 간 카드 이동을 자동화할 수 있습니다.

### 웹훅 기능
보드의 변경사항을 실시간으로 알림받을 수 있는 웹훅을 설정할 수 있습니다.

### 검색 기능
보드, 카드, 멤버를 검색하고 필터링할 수 있습니다.

## 인증 방식
OAuth 1.0a 및 API 키/토큰 조합을 사용합니다. 사용자 대신 작업을 수행하려면 OAuth 인증이 필요합니다.

## Power-Ups
커스텀 Power-Up을 개발하여 Trello의 기능을 확장할 수 있습니다.

## Rate Limiting
일반적으로 10초당 300개의 요청으로 제한되며, 토큰별로 100개의 요청으로 추가 제한됩니다.

## 사용 사례
프로젝트 관리 자동화, 다른 도구와의 통합, 커스텀 워크플로우 구축, 자동 카드 생성 및 이동, 리포팅 대시보드 제작 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 8. Google Login
(
    8,
    1,
    '## 개요
Google Login API는 OAuth 2.0 프로토콜을 기반으로 한 인증 시스템으로, 사용자가 Google 계정으로 간편하게 로그인할 수 있게 해줍니다. 안전하고 신뢰할 수 있는 인증 방식을 제공합니다.

## 주요 기능

### 간편 로그인
사용자가 별도의 회원가입 없이 Google 계정으로 로그인할 수 있습니다. 원클릭 로그인을 지원합니다.

### 사용자 정보 접근
사용자의 기본 프로필 정보에 접근할 수 있습니다. 이름, 이메일, 프로필 사진 등을 가져올 수 있습니다.

### 다중 플랫폼 지원
웹, Android, iOS 앱에서 일관된 로그인 경험을 제공합니다.

### 보안
OAuth 2.0 표준을 따르며, HTTPS를 통한 안전한 통신, 토큰 기반 인증, 짧은 토큰 만료 시간 등으로 보안을 강화합니다.

## 인증 플로우

### Authorization Code Flow
서버 사이드 애플리케이션에 적합하며, 가장 안전한 방식입니다.

### Implicit Flow
클라이언트 사이드 애플리케이션에 적합하며, 빠른 인증이 가능합니다.

## 권한 범위
사용자 정보의 접근 범위를 지정할 수 있습니다. 기본 프로필, 이메일, Google 서비스 접근 등 다양한 scope를 설정할 수 있습니다.

## Google Identity Services
최신 버전으로 개선된 보안과 사용자 경험을 제공합니다. Sign In With Google 버튼을 쉽게 통합할 수 있습니다.

## 토큰 관리
액세스 토큰과 리프레시 토큰을 제공하며, 토큰 갱신 및 폐기 기능을 지원합니다.

## 사용 사례
웹 및 모바일 앱 로그인, 사용자 인증 간소화, SSO 구현, 타사 서비스와의 계정 연동 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 9. Stripe API
(
    9,
    1,
    '## 개요
Stripe API는 온라인 결제 처리를 위한 포괄적인 플랫폼입니다. 신용카드 결제, 구독 관리, 송금 등 다양한 금융 서비스를 제공합니다.

## 주요 기능

### 결제 처리
신용카드 및 직불카드 결제를 안전하게 처리합니다. 주요 카드 브랜드를 모두 지원하며, 3D Secure 인증을 통한 추가 보안을 제공합니다.

### 구독 관리
정기 결제를 자동으로 관리하고, 다양한 구독 플랜을 생성할 수 있습니다. 무료 체험, 쿠폰, 할인 기능을 지원합니다.

### 송금 및 지불
플랫폼 비즈니스를 위한 Connect 기능으로 여러 계좌로 자동 송금이 가능합니다.

### 결제 방식
신용카드 외에도 Apple Pay, Google Pay, 은행 이체, 지역별 결제 수단을 지원합니다.

### 사기 방지
Stripe Radar를 통한 실시간 사기 탐지 및 차단 기능을 제공합니다.

## 인증 및 보안
API 키 기반 인증을 사용하며, 테스트용과 실제 운영용 키를 분리하여 제공합니다. PCI DSS Level 1 인증을 획득했습니다.

## Stripe Elements
사전 구축된 UI 컴포넌트로 안전한 결제 양식을 쉽게 통합할 수 있습니다.

## 웹훅
결제 이벤트를 실시간으로 알림받을 수 있습니다. 성공, 실패, 환불 등 다양한 이벤트를 처리할 수 있습니다.

## 지원 국가
135개 이상의 통화를 지원하며, 전 세계 대부분의 국가에서 사용 가능합니다.

## 사용 사례
전자상거래 결제, SaaS 구독 관리, 마켓플레이스 송금, 크라우드펀딩 플랫폼, 온디맨드 서비스 결제 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 10. LinkedIn API
(
    10,
    1,
    '## 개요
LinkedIn API는 전문 네트워킹 플랫폼인 LinkedIn의 데이터와 기능에 접근할 수 있게 해주는 RESTful API입니다. 프로필 정보, 연결 관계, 콘텐츠 공유 등이 가능합니다.

## 주요 기능

### 프로필 접근
사용자의 기본 프로필 정보에 접근할 수 있습니다. 이름, 직책, 회사, 학력, 기술 등의 정보를 가져올 수 있습니다.

### 콘텐츠 공유
LinkedIn 피드에 포스트를 공유하고, 이미지나 링크를 첨부할 수 있습니다. 회사 페이지에도 콘텐츠를 게시할 수 있습니다.

### 연결 관리
사용자의 연결 관계를 조회하고, 네트워크 정보에 접근할 수 있습니다.

### 인증
OAuth 2.0을 사용한 안전한 로그인 기능을 제공합니다. 사용자가 LinkedIn 계정으로 간편하게 로그인할 수 있습니다.

## API 제품

### Sign In with LinkedIn
간편 로그인 기능으로 사용자 인증을 간소화합니다.

### Share on LinkedIn
애플리케이션에서 직접 LinkedIn으로 콘텐츠를 공유할 수 있습니다.

### Marketing Developer Platform
광고 캠페인 관리, 리드 생성 양식, 분석 데이터 등 마케팅 기능을 제공합니다.

## 권한 범위
r_liteprofile, r_emailaddress, w_member_social 등 다양한 권한 범위를 설정할 수 있습니다.

## API 제한사항
LinkedIn은 API 사용을 엄격하게 제어합니다. 파트너 프로그램 승인이 필요한 기능이 많으며, Rate limiting이 적용됩니다.

## 사용 사례
구직 플랫폼 통합, 전문가 네트워킹 앱, 채용 관리 시스템, 비즈니스 인텔리전스 도구, 소셜 미디어 관리 툴 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 11. Google Cloud Vision
(
    11,
    1,
    '## 개요
Google Cloud Vision API는 구글의 머신러닝 기술을 활용한 이미지 분석 서비스입니다. 이미지에서 객체를 인식하고, 텍스트를 추출하며, 다양한 속성을 분석할 수 있습니다.

## 주요 기능

### 레이블 감지
이미지에 포함된 객체, 장소, 활동, 동물, 제품 등을 자동으로 식별합니다. 수천 개의 카테고리를 인식할 수 있습니다.

### OCR 텍스트 인식
이미지 내의 텍스트를 감지하고 추출합니다. 50개 이상의 언어를 지원하며, 손글씨도 인식 가능합니다.

### 얼굴 감지
이미지에서 얼굴을 찾고, 감정 상태와 얼굴 랜드마크를 분석합니다. 기쁨, 슬픔, 분노 등의 감정을 파악할 수 있습니다.

### 랜드마크 인식
유명한 자연 및 인공 구조물을 식별하고 위치 정보를 제공합니다.

### 로고 감지
이미지 내의 브랜드 로고를 인식하고 식별합니다.

### 부적절한 콘텐츠 탐지
성인용 콘텐츠, 폭력적인 콘텐츠 등을 자동으로 감지합니다.

### 웹 엔티티 및 페이지
이미지와 유사한 웹 콘텐츠를 찾고, 관련 웹 엔티티를 식별합니다.

## 인증 방식
Google Cloud 서비스 계정 또는 API 키를 사용한 인증이 필요합니다.

## 지원 형식
JPEG, PNG, GIF, BMP, WebP, RAW, ICO, PDF, TIFF 등 다양한 이미지 형식을 지원합니다.

## 배치 처리
여러 이미지를 한 번의 요청으로 처리할 수 있어 효율적입니다.

## 사용 사례
콘텐츠 검열, 자동 이미지 태깅, 문서 디지털화, 시각 검색, 접근성 향상, 제품 인식 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 12. Instagram API
(
    12,
    1,
    '## 개요
Instagram API는 Meta에서 제공하는 Instagram 플랫폼 통합 API입니다. Instagram Basic Display API와 Instagram Graph API 두 가지 버전이 있으며, 각각 다른 용도로 사용됩니다.

## 주요 API 종류

### Instagram Basic Display API
개인 Instagram 계정의 기본 프로필 정보와 미디어에 접근할 수 있습니다. 사용자 개인용 앱에 적합합니다.

### Instagram Graph API
비즈니스 및 크리에이터 계정을 위한 API로, 더 많은 기능을 제공합니다. 콘텐츠 게시, 댓글 관리, 인사이트 확인 등이 가능합니다.

## 주요 기능

### 미디어 관리
사진과 비디오를 게시하고, 스토리를 업로드하며, 게시물을 수정 및 삭제할 수 있습니다.

### 댓글 및 답글
게시물의 댓글을 조회하고, 답글을 작성하며, 부적절한 댓글을 숨길 수 있습니다.

### 인사이트 및 분석
게시물 도달률, 참여율, 팔로워 통계 등 다양한 분석 데이터를 확인할 수 있습니다.

### 해시태그 검색
특정 해시태그의 최근 미디어를 검색하고 조회할 수 있습니다.

### 멘션 관리
비즈니스 계정이 태그된 미디어를 찾고 관리할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, Facebook 로그인을 통해 인증합니다. 비즈니스 계정은 Facebook 페이지와 연결되어야 합니다.

## API 제한사항
Rate limiting이 적용되며, 시간당 요청 횟수가 제한됩니다. 앱 검수 과정이 필요할 수 있습니다.

## Content Publishing API
예약 게시, 여러 이미지 게시, 캐러셀 게시 등 고급 게시 기능을 제공합니다.

## 사용 사례
소셜 미디어 관리 도구, 인플루언서 분석 플랫폼, 자동 콘텐츠 게시, 고객 서비스 봇, 마케팅 분석 대시보드 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 13. Discord API
(
    13,
    1,
    '## 개요
Discord API는 실시간 커뮤니케이션 플랫폼인 Discord의 공식 API입니다. 봇 개발, 서버 관리 자동화, 사용자 상호작용 등이 가능하며, WebSocket 기반의 Gateway API를 제공합니다.

## 주요 기능

### 봇 개발
사용자 정의 봇을 생성하여 서버에 추가할 수 있습니다. 명령어 처리, 자동 응답, 관리 작업 등을 수행합니다.

### 메시지 관리
텍스트, 임베드, 파일, 이미지 등 다양한 형식의 메시지를 송수신할 수 있습니다. 메시지 편집 및 삭제도 가능합니다.

### 서버 관리
길드 생성 및 설정, 채널 관리, 역할 및 권한 설정, 멤버 관리 등이 가능합니다.

### 음성 및 비디오
음성 채널 참여, 오디오 스트리밍, 화면 공유 등의 기능을 구현할 수 있습니다.

### 슬래시 커맨드
사용자 친화적인 슬래시 커맨드를 생성하여 봇과의 상호작용을 개선할 수 있습니다.

### 인터랙션
버튼, 선택 메뉴, 모달 폼 등의 UI 컴포넌트를 사용하여 풍부한 사용자 경험을 제공합니다.

## Gateway와 REST API
Gateway API는 WebSocket을 통한 실시간 이벤트 수신을, REST API는 HTTP 요청을 통한 데이터 조작을 담당합니다.

## 인증 방식
Bot Token을 사용한 인증과 OAuth2를 통한 사용자 인증을 지원합니다.

## Rate Limiting
요청 빈도에 제한이 있으며, 각 엔드포인트마다 다른 제한이 적용됩니다.

## 웹훅
Discord 웹훅을 사용하여 간단하게 메시지를 전송할 수 있습니다. 봇 없이도 알림을 보낼 수 있습니다.

## 사용 사례
커뮤니티 관리 봇, 게임 통합, 알림 시스템, 음악 봇, 모더레이션 도구, 서버 통계 대시보드 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 14. Asana API
(
    14,
    1,
    '## 개요
Asana API는 팀 협업 및 프로젝트 관리 플랫폼인 Asana의 RESTful API입니다. 작업, 프로젝트, 팀을 프로그래밍 방식으로 관리하고 다른 도구와 통합할 수 있습니다.

## 주요 기능

### 작업 관리
작업을 생성, 수정, 삭제하고 세부 정보를 업데이트할 수 있습니다. 마감일, 담당자, 우선순위, 커스텀 필드를 설정할 수 있습니다.

### 프로젝트 관리
프로젝트를 생성하고 설정을 관리하며, 프로젝트 상태를 추적할 수 있습니다. 리스트, 보드, 타임라인 등 다양한 뷰를 지원합니다.

### 팀 및 워크스페이스
팀 멤버를 관리하고, 워크스페이스 설정을 변경하며, 권한을 제어할 수 있습니다.

### 첨부파일 관리
작업에 파일을 첨부하고, 첨부파일을 조회 및 다운로드할 수 있습니다.

### 댓글 및 스토리
작업에 댓글을 추가하고, 작업 히스토리를 조회할 수 있습니다. 팀원 간 소통을 원활하게 합니다.

### 커스텀 필드
조직의 워크플로우에 맞는 커스텀 필드를 생성하고 관리할 수 있습니다.

## 인증 방식
OAuth 2.0과 개인 액세스 토큰(PAT)을 지원합니다. OAuth는 사용자를 대신한 작업에, PAT는 개인 스크립트에 적합합니다.

## 웹훅
프로젝트나 작업의 변경사항을 실시간으로 알림받을 수 있습니다.

## Rate Limiting
분당 1500개의 요청으로 제한되며, 각 프로젝트와 작업에 대한 제한도 있습니다.

## Asana Apps
Asana 내에서 실행되는 커스텀 앱을 개발할 수 있습니다.

## 사용 사례
작업 자동화, 다른 도구와의 통합, 커스텀 대시보드 구축, 자동 보고서 생성, 워크플로우 최적화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 15. Wolfram Alpha
(
    15,
    1,
    '## 개요
Wolfram Alpha API는 계산 지식 엔진인 Wolfram Alpha의 강력한 연산 능력과 방대한 지식 베이스에 접근할 수 있게 해주는 API입니다. 자연어 쿼리를 처리하고 정확한 답변을 제공합니다.

## 주요 기능

### 자연어 처리
일상 언어로 작성된 질문을 이해하고 해석하여 적절한 답변을 생성합니다.

### 수학 계산
방정식 풀이, 미적분, 대수학, 통계, 행렬 연산 등 고급 수학 문제를 해결합니다.

### 과학 데이터
물리학, 화학, 생물학 등 다양한 과학 분야의 데이터와 공식에 접근할 수 있습니다.

### 날씨 및 지리
실시간 날씨 정보, 지리 데이터, 시간대 정보 등을 제공합니다.

### 금융 데이터
주식 시세, 환율, 경제 지표 등 금융 정보를 조회할 수 있습니다.

### 단위 변환
다양한 단위 간 변환을 자동으로 수행합니다.

## API 유형

### Full Results API
완전한 Wolfram Alpha 응답을 XML 형식으로 제공합니다. 이미지, 표, 그래프를 포함합니다.

### Short Answers API
간단한 텍스트 답변만 반환합니다. 빠른 응답이 필요한 경우 적합합니다.

### Simple API
단일 이미지로 답변을 제공합니다. 시각적 표현이 중요한 경우 유용합니다.

### Conversational API
대화형 인터페이스를 위한 API로, 후속 질문을 처리할 수 있습니다.

## 인증 방식
AppID를 사용한 간단한 키 기반 인증을 제공합니다.

## 응답 형식
XML, JSON 형식을 지원하며, 이미지 및 오디오 파일도 반환할 수 있습니다.

## 사용 사례
교육용 앱, 계산기 앱, 검색 엔진, 챗봇, 데이터 분석 도구, 과학 연구 지원 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 16. Notion API
(
    16,
    1,
    '## 개요
Notion API는 협업 워크스페이스 도구인 Notion의 공식 API입니다. 페이지, 데이터베이스, 블록을 프로그래밍 방식으로 생성하고 관리할 수 있습니다.

## 주요 기능

### 페이지 관리
페이지를 생성, 읽기, 업데이트, 삭제할 수 있습니다. 페이지 속성과 콘텐츠를 프로그래밍 방식으로 조작할 수 있습니다.

### 데이터베이스 작업
데이터베이스를 쿼리하고, 항목을 추가 및 수정하며, 필터링과 정렬을 수행할 수 있습니다.

### 블록 조작
텍스트, 이미지, 표, 리스트 등 다양한 블록 타입을 생성하고 수정할 수 있습니다.

### 검색 기능
워크스페이스 내의 페이지와 데이터베이스를 검색할 수 있습니다.

### 사용자 정보
워크스페이스 사용자 정보를 조회하고, 권한을 관리할 수 있습니다.

## 데이터베이스 속성
체크박스, 선택, 다중 선택, 날짜, 사람, 파일, 관계 등 다양한 속성 타입을 지원합니다.

## 인증 방식
OAuth 2.0과 내부 통합(Internal Integration)을 통한 인증을 지원합니다.

## 통합 설정
Notion 워크스페이스에서 통합을 생성하고, 특정 페이지에 대한 액세스 권한을 부여해야 합니다.

## Rate Limiting
초당 3개의 요청으로 제한되며, 버스트 트래픽에 대한 유연성이 있습니다.

## 버전 관리
API 버전은 날짜 기반으로 관리되며, Notion-Version 헤더를 통해 지정합니다.

## 사용 사례
작업 자동화, CRM 시스템 구축, 콘텐츠 관리, 프로젝트 추적, 지식 베이스 동기화, 커스텀 대시보드 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 17. HubSpot API
(
    17,
    1,
    '## 개요
HubSpot API는 인바운드 마케팅, 영업, 고객 서비스 플랫폼인 HubSpot의 데이터와 기능에 접근할 수 있게 해주는 RESTful API입니다. CRM 데이터 관리와 마케팅 자동화를 지원합니다.

## 주요 기능

### CRM 객체 관리
연락처, 회사, 거래, 티켓 등의 CRM 객체를 생성, 읽기, 업데이트, 삭제할 수 있습니다. 커스텀 속성도 지원합니다.

### 마케팅 자동화
이메일 캠페인, 워크플로우, 양식 제출 데이터에 접근하고 관리할 수 있습니다.

### 분석 및 리포팅
웹사이트 분석, 캠페인 성과, 영업 데이터 등을 조회할 수 있습니다.

### 콘텐츠 관리
블로그 포스트, 랜딩 페이지, 이메일 템플릿을 프로그래밍 방식으로 관리할 수 있습니다.

### 통합 기능
타사 애플리케이션과 HubSpot을 연결하여 데이터를 동기화할 수 있습니다.

## API 카테고리

### CRM API
연락처, 회사, 거래 등 핵심 CRM 데이터를 관리합니다.

### Marketing API
이메일, 양식, 워크플로우 등 마케팅 도구에 접근합니다.

### CMS API
웹사이트 콘텐츠를 생성하고 관리합니다.

### Conversations API
채팅 및 고객 소통 데이터를 처리합니다.

## 인증 방식
OAuth 2.0과 API 키 기반 인증을 지원합니다. 민감한 데이터 접근에는 OAuth가 권장됩니다.

## 웹훅
CRM 객체 변경, 양식 제출 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## Rate Limiting
구독 플랜에 따라 다르며, 일일 API 호출 제한이 적용됩니다.

## 사용 사례
리드 관리 자동화, 영업 파이프라인 통합, 고객 데이터 동기화, 마케팅 캠페인 추적, 커스텀 리포팅 대시보드 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 18. Pexels API
(
    18,
    1,
    '## 개요
Pexels API는 고품질의 무료 스톡 사진과 비디오를 제공하는 플랫폼의 API입니다. 수백만 개의 이미지와 비디오에 무료로 접근할 수 있으며, 상업적 용도로도 사용 가능합니다.

## 주요 기능

### 이미지 검색
키워드로 사진을 검색하고, 필터링 옵션을 사용하여 원하는 이미지를 찾을 수 있습니다. 방향, 크기, 색상으로 필터링이 가능합니다.

### 비디오 검색
HD 및 4K 비디오를 검색하고 다운로드할 수 있습니다. 다양한 카테고리와 태그로 분류되어 있습니다.

### 큐레이션 콜렉션
인기 사진과 비디오 컬렉션에 접근할 수 있습니다. 트렌딩 콘텐츠를 쉽게 찾을 수 있습니다.

### 특정 사진 가져오기
사진 ID를 사용하여 특정 이미지의 상세 정보를 조회할 수 있습니다.

### 다양한 해상도
여러 크기의 이미지를 제공하여 다양한 용도에 맞게 사용할 수 있습니다.

## 인증 방식
API 키를 사용한 간단한 인증 방식입니다. 요청 헤더에 키를 포함해야 합니다.

## 라이선스
모든 콘텐츠는 Pexels 라이선스 하에 제공되며, 상업적 및 비상업적 용도로 자유롭게 사용 가능합니다. 저작권자 표기가 필수는 아니지만 권장됩니다.

## Rate Limiting
시간당 200개의 요청으로 제한됩니다. 더 많은 요청이 필요한 경우 Pexels에 문의할 수 있습니다.

## 응답 형식
JSON 형식으로 이미지 메타데이터와 URL을 제공합니다.

## 지원 언어
다국어 검색을 지원하여 전 세계 사용자가 편리하게 사용할 수 있습니다.

## 사용 사례
웹사이트 및 블로그 이미지, 마케팅 자료, 소셜 미디어 콘텐츠, 앱 배경 이미지, 프레젠테이션 자료 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 19. Slack API
(
    19,
    1,
    '## 개요
Slack API는 팀 커뮤니케이션 플랫폼인 Slack의 기능을 외부 애플리케이션과 통합할 수 있게 해주는 API입니다. 메시징, 파일 공유, 워크플로우 자동화 등을 지원합니다.

## 주요 기능

### 메시지 전송
채널이나 개인에게 메시지를 보낼 수 있습니다. 서식 있는 텍스트, 버튼, 첨부파일 등을 포함할 수 있습니다.

### 슬래시 커맨드
사용자가 입력하는 명령어를 처리하는 커스텀 커맨드를 생성할 수 있습니다.

### 인터랙티브 컴포넌트
버튼, 선택 메뉴, 날짜 선택기 등의 UI 요소를 메시지에 추가하여 상호작용을 구현할 수 있습니다.

### 봇 개발
자동화된 봇을 생성하여 채널에서 다양한 작업을 수행할 수 있습니다.

### 이벤트 구독
워크스페이스에서 발생하는 이벤트를 실시간으로 수신할 수 있습니다.

### 파일 관리
파일을 업로드하고 공유하며, 파일 메타데이터에 접근할 수 있습니다.

## Web API
HTTP 요청을 통해 Slack 기능에 접근하는 RESTful API입니다. 메시지 게시, 채널 관리 등이 가능합니다.

## Events API
워크스페이스의 이벤트를 실시간으로 받아볼 수 있는 WebSocket 기반 API입니다.

## Block Kit
메시지를 구성하는 시각적 빌딩 블록으로, 풍부한 UI를 만들 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, Bot Token과 User Token을 제공합니다.

## Rate Limiting
메서드별로 다른 제한이 적용되며, Tier-based rate limiting을 사용합니다.

## 사용 사례
알림 시스템, 워크플로우 자동화, 챗봇, CI/CD 통합, 프로젝트 관리 통합, 고객 지원 도구 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 20. OpenStreetMap
(
    20,
    1,
    '## 개요
OpenStreetMap API는 오픈소스 협업 프로젝트인 OpenStreetMap의 지도 데이터에 접근할 수 있게 해주는 API입니다. 전 세계의 상세한 지도 정보를 무료로 사용할 수 있습니다.

## 주요 기능

### 지도 데이터 조회
도로, 건물, 자연 지형, 관심 지점 등의 지리 정보를 조회할 수 있습니다.

### 지오코딩
주소를 좌표로 변환하거나, 좌표를 주소로 변환할 수 있습니다. Nominatim 서비스를 통해 제공됩니다.

### 경로 찾기
출발지와 목적지 간의 최적 경로를 계산할 수 있습니다. OSRM 등의 라우팅 엔진을 사용합니다.

### 타일 서비스
지도 이미지 타일을 제공하여 웹이나 앱에서 지도를 표시할 수 있습니다.

### 데이터 편집
API를 통해 지도 데이터를 추가하거나 수정할 수 있습니다. 계정이 필요합니다.

## API 종류

### Overpass API
복잡한 쿼리로 특정 지역의 상세한 데이터를 추출할 수 있습니다.

### Nominatim API
지오코딩 및 역지오코딩 서비스를 제공합니다.

### OSM API
지도 데이터를 직접 읽고 쓸 수 있는 API입니다.

## 사용 정책
공정 사용 정책을 준수해야 하며, 대량 요청 시 자체 서버 구축이 권장됩니다.

## 데이터 라이선스
ODbL 라이선스 하에 제공되며, 사용 시 OpenStreetMap 크레딧 표기가 필요합니다.

## 인증
대부분의 읽기 작업은 인증이 필요 없지만, 데이터 편집에는 OAuth 인증이 필요합니다.

## 사용 사례
커스텀 지도 앱, 위치 기반 서비스, 내비게이션 시스템, 부동산 지도, 배달 및 물류 최적화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 21. PayPal API
(
    21,
    1,
    '## 개요
PayPal API는 글로벌 온라인 결제 플랫폼인 PayPal의 결제 서비스를 웹사이트와 앱에 통합할 수 있게 해주는 API입니다. 안전한 결제 처리와 송금 기능을 제공합니다.

## 주요 기능

### 결제 처리
신용카드, 직불카드, PayPal 잔액 등 다양한 결제 수단을 지원합니다. 일회성 결제와 정기 결제가 모두 가능합니다.

### 체크아웃
PayPal의 안전한 체크아웃 페이지로 리디렉션하거나, 자체 페이지에서 결제를 처리할 수 있습니다.

### 구독 관리
정기 결제 플랜을 생성하고, 구독을 관리하며, 자동 갱신을 설정할 수 있습니다.

### 송금 기능
다른 PayPal 계정으로 자금을 송금할 수 있습니다. 대량 송금 기능도 지원합니다.

### 환불 처리
전체 또는 부분 환불을 쉽게 처리할 수 있습니다.

### 분쟁 관리
고객 분쟁과 청구 거절을 API를 통해 관리할 수 있습니다.

## API 종류

### REST API
현대적인 RESTful 인터페이스로 대부분의 기능을 제공합니다.

### Checkout API
간편한 결제 버튼과 체크아웃 플로우를 구현합니다.

### Payouts API
여러 수취인에게 동시에 송금할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하여 Client ID와 Secret으로 액세스 토큰을 발급받습니다.

## 샌드박스 환경
실제 돈을 사용하지 않고 테스트할 수 있는 개발 환경을 제공합니다.

## 지원 통화
100개 이상의 통화를 지원하며, 자동 환전 기능이 있습니다.

## 웹훅
거래 이벤트를 실시간으로 알림받을 수 있습니다.

## 사용 사례
전자상거래 결제, 크라우드펀딩, 마켓플레이스 송금, SaaS 구독, 디지털 콘텐츠 판매 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 22. Reddit API
(
    22,
    1,
    '## 개요
Reddit API는 소셜 뉴스 및 커뮤니티 플랫폼인 Reddit의 데이터와 기능에 접근할 수 있게 해주는 RESTful API입니다. 게시물, 댓글, 사용자 데이터를 프로그래밍 방식으로 처리할 수 있습니다.

## 주요 기능

### 게시물 관리
서브레딧에 게시물을 작성하고, 수정하며, 삭제할 수 있습니다. 텍스트, 링크, 이미지, 비디오 포스트를 지원합니다.

### 댓글 시스템
댓글을 작성하고 답글을 달 수 있으며, 댓글 트리 구조를 탐색할 수 있습니다.

### 투표 기능
게시물과 댓글에 업보트 또는 다운보트를 할 수 있습니다.

### 검색 기능
키워드로 게시물, 서브레딧, 사용자를 검색할 수 있습니다. 고급 필터링 옵션을 제공합니다.

### 사용자 프로필
사용자 정보, 카르마, 작성한 게시물과 댓글을 조회할 수 있습니다.

### 서브레딧 관리
서브레딧 정보를 조회하고, 구독하거나 구독을 취소할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, Script, Web App, Installed App 등 다양한 앱 타입을 지원합니다.

## Rate Limiting
OAuth 앱은 분당 60개의 요청으로 제한됩니다. User-Agent 헤더를 정확히 설정해야 합니다.

## JSON 응답
모든 페이지는 URL 끝에 .json을 추가하여 JSON 형식으로 받을 수 있습니다.

## 실시간 데이터
스트리밍 엔드포인트를 통해 새로운 게시물과 댓글을 실시간으로 받을 수 있습니다.

## 사용 정책
Reddit의 API 사용 규칙을 준수해야 하며, 스팸이나 악용은 금지됩니다.

## 사용 사례
소셜 미디어 분석, 콘텐츠 큐레이션, 트렌드 모니터링, 커뮤니티 관리 봇, 데이터 마이닝 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 23. Dropbox API
(
    23,
    1,
    '## 개요
Dropbox API는 클라우드 스토리지 서비스인 Dropbox의 파일 시스템과 공유 기능에 접근할 수 있게 해주는 API입니다. 파일 업로드, 다운로드, 동기화, 공유 등을 프로그래밍 방식으로 처리할 수 있습니다.

## 주요 기능

### 파일 작업
파일을 업로드, 다운로드, 이동, 복사, 삭제할 수 있습니다. 대용량 파일을 위한 청크 업로드를 지원합니다.

### 폴더 관리
폴더를 생성하고 구조를 탐색할 수 있습니다. 재귀적으로 폴더 내용을 나열할 수 있습니다.

### 파일 공유
공유 링크를 생성하고 관리할 수 있습니다. 권한 설정과 만료 날짜를 지정할 수 있습니다.

### 파일 메타데이터
파일 정보, 수정 시간, 해시 값 등을 조회할 수 있습니다.

### 검색 기능
파일과 폴더를 이름으로 검색할 수 있습니다.

### 변경 추적
Dropbox 계정의 변경 사항을 추적할 수 있습니다. 파일 버전 히스토리에 접근할 수 있습니다.

## API 버전

### Dropbox API v2
최신 버전으로 JSON 기반 요청과 응답을 사용합니다.

### Content API
실제 파일 콘텐츠를 업로드하고 다운로드하는 엔드포인트입니다.

## 인증 방식
OAuth 2.0을 사용하며, 단기 및 장기 액세스 토큰을 지원합니다.

## Dropbox Paper API
Dropbox Paper 문서를 생성하고 관리할 수 있는 별도의 API도 제공됩니다.

## 웹훅
파일 변경 사항을 실시간으로 알림받을 수 있습니다.

## Rate Limiting
앱당 요청 제한이 있으며, 과도한 사용 시 제한될 수 있습니다.

## 사용 사례
파일 백업 솔루션, 문서 관리 시스템, 협업 도구, 미디어 갤러리, 자동 파일 동기화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 24. DeepL API
(
    24,
    1,
    '## 개요
DeepL API는 고품질 인공지능 기반 번역 서비스를 제공하는 API입니다. 신경망 번역 기술을 사용하여 자연스럽고 정확한 번역을 제공하며, 특히 유럽 언어에서 뛰어난 성능을 보입니다.

## 주요 기능

### 텍스트 번역
단어, 문장, 문단, 전체 문서를 번역할 수 있습니다. 문맥을 고려한 정확한 번역을 제공합니다.

### 문서 번역
DOCX, PPTX, PDF 등의 문서 파일을 번역하면서 서식을 유지합니다.

### 용어집 기능
특정 용어의 번역을 사용자 정의할 수 있습니다. 일관된 전문 용어 번역이 가능합니다.

### 형식성 설정
번역의 격식 수준을 조정할 수 있습니다. 더 격식 있는 또는 캐주얼한 번역을 선택할 수 있습니다.

### 언어 감지
입력 텍스트의 언어를 자동으로 감지합니다.

## 지원 언어
한국어, 영어, 일본어, 중국어, 독일어, 프랑스어, 스페인어, 이탈리아어, 네덜란드어, 폴란드어, 러시아어 등 31개 언어를 지원합니다.

## 번역 품질
Google Translate보다 자연스러운 번역으로 평가받고 있으며, 특히 유럽 언어 간 번역에서 우수한 성능을 보입니다.

## 인증 방식
API 키를 사용한 간단한 인증 방식입니다. HTTP 헤더에 인증 키를 포함합니다.

## API 엔드포인트
텍스트 번역, 문서 번역, 용어집 관리, 사용량 확인 등의 엔드포인트를 제공합니다.

## Rate Limiting
Free 플랜은 월 500,000자로 제한되며, Pro 플랜은 무제한입니다.

## 사용 사례
웹사이트 다국어 지원, 문서 번역, 채팅 앱 실시간 번역, 이메일 번역, 콘텐츠 현지화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 25. Twilio SMS
(
    25,
    1,
    '## 개요
Twilio SMS API는 프로그래밍 방식으로 SMS 및 MMS 메시지를 보내고 받을 수 있게 해주는 클라우드 통신 API입니다. 전 세계 180개 이상의 국가로 메시지를 전송할 수 있습니다.

## 주요 기능

### SMS 발송
텍스트 메시지를 개별 또는 대량으로 발송할 수 있습니다. 짧은 코드와 장문 번호를 지원합니다.

### MMS 발송
이미지, 비디오, 오디오가 포함된 멀티미디어 메시지를 보낼 수 있습니다.

### 수신 메시지 처리
수신한 SMS를 웹훅을 통해 실시간으로 받을 수 있습니다. 양방향 대화가 가능합니다.

### 메시지 상태 추적
발송, 전달, 실패 등 메시지 상태를 추적할 수 있습니다.

### 전화번호 관리
SMS 발송용 전화번호를 구매하고 관리할 수 있습니다.

### 단축 URL
긴 URL을 자동으로 단축하여 SMS 길이를 줄일 수 있습니다.

## 추가 채널

### WhatsApp API
Twilio를 통해 WhatsApp으로 메시지를 보낼 수 있습니다.

### 음성 통화
SMS 외에도 음성 통화 API를 제공합니다.

## 인증 방식
Account SID와 Auth Token을 사용한 Basic Authentication을 사용합니다.

## 웹훅
메시지 전송 상태와 수신 메시지를 실시간으로 알림받을 수 있습니다.

## 규정 준수
TCPA, GDPR 등 각국의 통신 규제를 준수합니다.

## SDK 지원
Python, Node.js, Java, PHP, Ruby, C# 등 다양한 언어의 SDK를 제공합니다.

## 사용 사례
인증 코드 전송, 알림 메시지, 마케팅 캠페인, 예약 확인, 배송 알림, 긴급 경보 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 26. Azure Cognitive
(
    26,
    1,
    '## 개요
Azure Cognitive Services는 Microsoft의 인공지능 서비스 모음으로, 비전, 음성, 언어, 의사결정 등의 AI 기능을 API로 제공합니다. 머신러닝 전문 지식 없이도 지능형 애플리케이션을 개발할 수 있습니다.

## 주요 서비스

### 비전 서비스
이미지와 비디오를 분석하여 객체, 얼굴, 텍스트를 인식합니다. OCR, 얼굴 감지, 맞춤형 이미지 분류 등을 지원합니다.

### 음성 서비스
음성을 텍스트로 변환하거나 텍스트를 자연스러운 음성으로 변환합니다. 실시간 음성 번역도 가능합니다.

### 언어 서비스
텍스트 분석, 감정 분석, 핵심 구문 추출, 번역 등의 자연어 처리 기능을 제공합니다.

### 의사결정 서비스
개인화 추천, 콘텐츠 조정, 이상 탐지 등의 기능을 제공합니다.

## 세부 API

### Computer Vision
이미지 분석, 썸네일 생성, OCR, 공간 분석 등을 수행합니다.

### Face API
얼굴 감지, 인식, 감정 분석, 비슷한 얼굴 찾기 등을 지원합니다.

### Text Analytics
감정 분석, 핵심 구문 추출, 언어 감지, 엔티티 인식 등을 제공합니다.

### Speech Service
음성 인식, 음성 합성, 음성 번역을 실시간으로 처리합니다.

### Translator
90개 이상의 언어를 지원하는 고품질 기계 번역을 제공합니다.

## 인증 방식
구독 키를 사용한 인증과 Azure Active Directory 인증을 지원합니다.

## 컨테이너 지원
Docker 컨테이너로 배포하여 온프레미스에서 실행할 수 있습니다.

## 사용 사례
챗봇, 콘텐츠 검열, 접근성 향상, 고객 서비스 자동화, 문서 처리, 비디오 분석 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 27. News API
(
    27,
    1,
    '## 개요
News API는 전 세계 80,000개 이상의 뉴스 소스에서 실시간 뉴스 기사와 헤드라인을 수집하는 API입니다. 주요 언론사, 블로그, 잡지 등의 최신 뉴스를 프로그래밍 방식으로 접근할 수 있습니다.

## 주요 기능

### 실시간 뉴스
최신 헤드라인과 기사를 실시간으로 받아볼 수 있습니다. 국가, 카테고리, 소스별로 필터링이 가능합니다.

### 뉴스 검색
키워드로 과거 기사를 검색할 수 있습니다. 최대 한 달 전까지의 기사를 조회할 수 있습니다.

### 소스 관리
사용 가능한 뉴스 소스 목록을 조회하고, 특정 소스의 기사만 가져올 수 있습니다.

### 카테고리 필터
비즈니스, 엔터테인먼트, 건강, 과학, 스포츠, 기술 등의 카테고리로 분류된 뉴스를 제공합니다.

### 언어 지원
14개 언어의 뉴스를 지원하며, 특정 국가의 뉴스만 가져올 수 있습니다.

## 엔드포인트

### Top Headlines
특정 국가나 카테고리의 주요 헤드라인을 가져옵니다.

### Everything
모든 소스에서 키워드로 기사를 검색합니다.

### Sources
사용 가능한 뉴스 소스를 조회합니다.

## 인증 방식
API 키를 URL 파라미터 또는 헤더에 포함하여 인증합니다.

## 응답 데이터
기사 제목, 설명, URL, 이미지, 게시 날짜, 출처 등의 정보를 JSON 형식으로 제공합니다.

## Rate Limiting
무료 플랜은 일일 100개의 요청으로 제한되며, 유료 플랜은 더 많은 요청을 허용합니다.

## 사용 사례
뉴스 애그리게이터 앱, 미디어 모니터링 도구, 트렌드 분석, 자동 뉴스 큐레이션, 리서치 도구 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 28. IBM Watson
(
    28,
    1,
    '## 개요
IBM Watson API는 IBM의 인공지능 플랫폼으로, 자연어 처리, 음성 인식, 이미지 분석 등 다양한 AI 서비스를 제공합니다. 엔터프라이즈급 AI 솔루션을 구축할 수 있습니다.

## 주요 서비스

### Watson Assistant
대화형 AI 챗봇을 구축할 수 있습니다. 자연어 이해, 대화 관리, 다중 채널 배포를 지원합니다.

### Watson Discovery
비구조화된 데이터에서 인사이트를 추출합니다. 문서 분석, 패턴 인식, 지식 그래프 생성이 가능합니다.

### Watson Natural Language Understanding
텍스트에서 감정, 엔티티, 키워드, 카테고리 등을 추출합니다. 고급 텍스트 분석 기능을 제공합니다.

### Watson Speech to Text
음성을 텍스트로 변환합니다. 실시간 스트리밍과 배치 처리를 지원합니다.

### Watson Text to Speech
텍스트를 자연스러운 음성으로 변환합니다. 다양한 언어와 목소리를 지원합니다.

### Watson Visual Recognition
이미지를 분석하고 분류합니다. 커스텀 모델 훈련이 가능합니다.

## 언어 지원
영어, 한국어, 일본어, 중국어, 스페인어, 프랑스어 등 다양한 언어를 지원합니다.

## 인증 방식
IBM Cloud IAM 토큰 또는 API 키를 사용한 인증을 지원합니다.

## 엔터프라이즈 기능
GDPR 준수, 데이터 암호화, 온프레미스 배포 옵션 등 기업용 기능을 제공합니다.

## Watson Studio
머신러닝 모델을 구축하고 훈련할 수 있는 통합 환경을 제공합니다.

## 사용 사례
고객 서비스 챗봇, 문서 분석, 음성 인식 애플리케이션, 감정 분석, 의료 진단 지원 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 29. OpenAI GPT-4
(
    29,
    1,
    '## 개요
OpenAI GPT-4 API는 최첨단 대규모 언어 모델인 GPT-4에 접근할 수 있게 해주는 API입니다. 자연어 이해, 텍스트 생성, 번역, 요약, 코드 작성 등 다양한 언어 작업을 수행할 수 있습니다.

## 주요 기능

### 텍스트 생성
프롬프트를 기반으로 창의적이고 일관성 있는 텍스트를 생성합니다. 다양한 스타일과 톤을 조절할 수 있습니다.

### 대화 및 챗봇
자연스러운 대화를 생성하여 고급 챗봇을 구축할 수 있습니다. 문맥을 유지하면서 대화를 이어갑니다.

### 코드 생성 및 디버깅
프로그래밍 언어로 코드를 작성하고, 버그를 찾아 수정하며, 코드를 설명할 수 있습니다.

### 번역 및 요약
다국어 번역과 긴 텍스트의 요약을 정확하게 수행합니다.

### 데이터 분석
텍스트 데이터를 분석하고 인사이트를 도출할 수 있습니다.

### 창의적 작업
스토리, 시, 광고 카피 등 창의적인 콘텐츠를 생성할 수 있습니다.

## 모델 버전

### GPT-4o
멀티모달 기능을 지원하는 최신 모델입니다.

### GPT-4 Turbo
더 긴 컨텍스트와 향상된 성능을 제공합니다.

### GPT-3.5 Turbo
비용 효율적이면서 빠른 응답이 필요한 경우 적합합니다.

## 인증 방식
API 키를 HTTP 헤더에 포함하여 인증합니다.

## 토큰 시스템
텍스트는 토큰으로 계산되며, 요청과 응답 모두 토큰 사용량에 포함됩니다.

## Function Calling
외부 함수를 호출하여 실시간 데이터를 가져오거나 작업을 수행할 수 있습니다.

## 안전 기능
콘텐츠 필터링과 안전 가이드라인을 통해 유해한 콘텐츠 생성을 방지합니다.

## 사용 사례
AI 어시스턴트, 콘텐츠 생성, 고객 지원 자동화, 교육 도구, 코딩 도우미, 데이터 분석 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 30. Mailchimp
(
    30,
    1,
    '## 개요
Mailchimp API는 이메일 마케팅 플랫폼인 Mailchimp의 기능을 외부 애플리케이션과 통합할 수 있게 해주는 RESTful API입니다. 이메일 캠페인, 리스트 관리, 자동화를 프로그래밍 방식으로 제어할 수 있습니다.

## 주요 기능

### 리스트 및 연락처 관리
메일링 리스트를 생성하고, 구독자를 추가, 수정, 삭제할 수 있습니다. 세그먼트와 태그를 관리할 수 있습니다.

### 캠페인 관리
이메일 캠페인을 생성, 발송, 스케줄링할 수 있습니다. 템플릿을 사용하거나 커스텀 HTML을 작성할 수 있습니다.

### 자동화 워크플로우
트리거 기반 이메일 자동화를 설정할 수 있습니다. 환영 이메일, 폐기 카트 리마인더 등을 자동화합니다.

### 리포팅 및 분석
캠페인 성과, 오픈율, 클릭률, 구독 취소율 등의 통계를 조회할 수 있습니다.

### 템플릿 관리
이메일 템플릿을 생성하고 관리할 수 있습니다.

### 전자상거래 통합
온라인 스토어와 연동하여 구매 데이터를 추적하고 타겟 마케팅을 수행할 수 있습니다.

## 인증 방식
OAuth 2.0과 API 키 기반 인증을 지원합니다.

## 배치 작업
여러 작업을 한 번의 요청으로 처리할 수 있는 배치 엔드포인트를 제공합니다.

## 웹훅
리스트 변경, 구독, 구독 취소 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## Rate Limiting
초당 10개의 요청으로 제한되며, 적절한 간격을 두고 요청해야 합니다.

## Mandrill
트랜잭셔널 이메일 전송을 위한 별도의 Mandrill API도 제공됩니다.

## 사용 사례
이메일 뉴스레터 자동화, 고객 온보딩, 이벤트 초대, 프로모션 캠페인, CRM 통합 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 31. Google Analytics
(
    31,
    1,
    '## 개요
Google Analytics API는 웹사이트와 앱의 트래픽 및 사용자 행동 데이터에 접근할 수 있게 해주는 API입니다. Google Analytics 4 속성의 데이터를 프로그래밍 방식으로 조회하고 분석할 수 있습니다.

## 주요 기능

### 데이터 조회
웹사이트 방문자 수, 페이지뷰, 세션, 전환율 등의 데이터를 조회할 수 있습니다.

### 맞춤 보고서
사용자 정의 보고서를 생성하여 특정 지표와 차원을 분석할 수 있습니다.

### 실시간 데이터
현재 웹사이트를 방문 중인 사용자 수와 활동을 실시간으로 확인할 수 있습니다.

### 세그먼트 분석
특정 사용자 그룹을 세그먼트로 나누어 행동을 분석할 수 있습니다.

### 전환 추적
목표 달성 및 전자상거래 거래 데이터를 추적할 수 있습니다.

### 이벤트 데이터
사용자 인터랙션 이벤트를 수집하고 분석할 수 있습니다.

## API 버전

### Data API v1 (GA4)
Google Analytics 4 속성의 데이터를 조회하는 최신 API입니다.

### Reporting API v4 (Universal Analytics)
기존 Universal Analytics 속성용 API입니다.

### Management API
Google Analytics 계정, 속성, 뷰를 관리할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, 서비스 계정 인증도 지원합니다.

## 쿼리 구조
지표와 차원을 조합하여 원하는 데이터를 조회합니다. 필터와 정렬 옵션을 사용할 수 있습니다.

## Rate Limiting
프로젝트당 일일 쿼리 한도가 있으며, 초당 요청 수도 제한됩니다.

## 사용 사례
커스텀 대시보드 구축, 자동화된 리포팅, 데이터 웨어하우스 통합, 고급 분석, A/B 테스트 결과 분석 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 32. Shopify API
(
    32,
    1,
    '## 개요
Shopify API는 전자상거래 플랫폼인 Shopify의 스토어 데이터와 기능에 접근할 수 있게 해주는 RESTful 및 GraphQL API입니다. 상품, 주문, 고객, 재고를 프로그래밍 방식으로 관리할 수 있습니다.

## 주요 기능

### 상품 관리
상품을 생성, 수정, 삭제하고 재고를 관리할 수 있습니다. 변형, 이미지, 컬렉션을 관리할 수 있습니다.

### 주문 처리
주문을 조회하고, 주문 상태를 업데이트하며, 배송 및 환불을 처리할 수 있습니다.

### 고객 관리
고객 정보를 생성하고 관리하며, 고객 그룹과 태그를 설정할 수 있습니다.

### 재고 관리
여러 위치의 재고를 추적하고 업데이트할 수 있습니다.

### 결제 처리
Shopify Payments 및 외부 결제 게이트웨이를 통한 결제를 처리합니다.

### 배송 설정
배송 영역, 요금, 방법을 설정하고 관리할 수 있습니다.

## API 타입

### REST Admin API
스토어의 모든 관리 기능에 접근할 수 있는 RESTful API입니다.

### GraphQL Admin API
더 효율적인 데이터 조회를 위한 GraphQL API입니다.

### Storefront API
고객 대면 쇼핑 경험을 구축하기 위한 API입니다.

## 인증 방식
OAuth 2.0과 API 액세스 토큰을 사용합니다. 커스텀 앱과 공개 앱을 지원합니다.

## 웹훅
주문 생성, 상품 업데이트, 고객 등록 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## Rate Limiting
API 버킷 시스템을 사용하여 요청 속도를 제어합니다.

## Shopify App Store
개발한 앱을 Shopify App Store에 출시하여 다른 상점 주인들에게 판매할 수 있습니다.

## 사용 사례
재고 관리 시스템, 주문 처리 자동화, 커스텀 체크아웃, 마켓플레이스 통합, 드롭쉬핑 솔루션 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 33. AWS Rekognition
(
    33,
    1,
    '## 개요
AWS Rekognition은 Amazon Web Services의 이미지 및 비디오 분석 서비스입니다. 딥러닝 기술을 사용하여 객체, 장면, 얼굴, 텍스트를 자동으로 인식하고 분석합니다.

## 주요 기능

### 객체 및 장면 탐지
이미지에서 수천 개의 객체와 장면을 식별합니다. 자동차, 동물, 건물 등을 인식할 수 있습니다.

### 얼굴 분석 및 인식
얼굴을 감지하고 나이, 성별, 감정 등을 분석합니다. 얼굴을 비교하고 데이터베이스에서 검색할 수 있습니다.

### 텍스트 인식
이미지와 비디오에서 텍스트를 감지하고 추출합니다.

### 유명인 인식
수천 명의 유명인을 자동으로 식별합니다.

### 부적절한 콘텐츠 탐지
성인용 콘텐츠, 폭력적인 콘텐츠 등을 자동으로 감지하여 필터링할 수 있습니다.

### 커스텀 레이블
고유한 비즈니스 요구사항에 맞는 커스텀 모델을 훈련할 수 있습니다.

### 비디오 분석
실시간 또는 저장된 비디오에서 객체, 사람, 활동을 추적합니다.

## PPE 감지
개인 보호 장비 착용 여부를 확인할 수 있습니다.

## 인증 방식
AWS IAM 자격 증명을 사용하여 인증합니다.

## SDK 지원
Java, Python, JavaScript, .NET, PHP 등 다양한 언어의 SDK를 제공합니다.

## S3 통합
Amazon S3에 저장된 이미지와 비디오를 직접 분석할 수 있습니다.

## 실시간 스트리밍
Kinesis Video Streams를 통해 실시간 비디오를 분석할 수 있습니다.

## 사용 사례
보안 시스템, 소셜 미디어 콘텐츠 검열, 고객 감정 분석, 매장 분석, 미디어 라이브러리 관리 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 34. Google Drive
(
    34,
    1,
    '## 개요
Google Drive API는 Google의 클라우드 스토리지 서비스인 Google Drive에 프로그래밍 방식으로 접근할 수 있게 해주는 RESTful API입니다. 파일 업로드, 다운로드, 공유, 검색 등이 가능합니다.

## 주요 기능

### 파일 관리
파일을 업로드, 다운로드, 복사, 이동, 삭제할 수 있습니다. 대용량 파일을 위한 재개 가능 업로드를 지원합니다.

### 폴더 구조
폴더를 생성하고 계층 구조를 관리할 수 있습니다. 파일과 폴더를 조직화할 수 있습니다.

### 파일 검색
강력한 검색 쿼리로 파일을 찾을 수 있습니다. 파일 이름, 유형, 소유자 등으로 검색이 가능합니다.

### 공유 및 권한
파일과 폴더를 공유하고, 읽기/쓰기/댓글 권한을 설정할 수 있습니다.

### 변경 추적
Drive의 변경 사항을 추적하고 동기화할 수 있습니다.

### 댓글 및 회신
파일에 댓글을 추가하고 관리할 수 있습니다.

## Google Workspace 통합
Docs, Sheets, Slides 등 Google Workspace 문서를 생성하고 편집할 수 있습니다.

## 파일 내보내기
Google 문서를 PDF, DOCX, XLSX 등 다양한 형식으로 내보낼 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, 서비스 계정 인증도 지원합니다.

## 변경 알림
푸시 알림을 통해 파일 변경 사항을 실시간으로 받을 수 있습니다.

## Rate Limiting
프로젝트당 일일 쿼리 한도와 사용자당 쿼리 한도가 있습니다.

## 사용 사례
파일 백업 도구, 문서 관리 시스템, 협업 플랫폼, 자동 보고서 생성, 미디어 저장소 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 35. Square API
(
    35,
    1,
    '## 개요
Square API는 결제 처리 및 비즈니스 관리 플랫폼인 Square의 기능을 통합할 수 있게 해주는 API입니다. 결제 처리, 재고 관리, 고객 관리 등 포괄적인 비즈니스 솔루션을 제공합니다.

## 주요 기능

### 결제 처리
카드 결제, 디지털 지갑, 현금 결제를 처리할 수 있습니다. 대면 및 비대면 결제를 모두 지원합니다.

### 재고 관리
상품 카탈로그를 생성하고, 재고를 추적하며, 변형과 옵션을 관리할 수 있습니다.

### 고객 관리
고객 프로필을 생성하고, 구매 이력을 추적하며, 로열티 프로그램을 운영할 수 있습니다.

### 주문 관리
주문을 생성하고 처리하며, 픽업 및 배송을 관리할 수 있습니다.

### 인보이스
전문적인 인보이스를 생성하고 발송할 수 있습니다. 자동 결제 리마인더를 설정할 수 있습니다.

### 리포팅
판매 데이터, 재무 리포트, 직원 성과 등을 분석할 수 있습니다.

## Payments API
안전한 온라인 결제를 처리하고, 카드 정보를 토큰화하여 저장합니다.

## Terminal API
Square 단말기와 통합하여 대면 결제를 처리할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, 개인 액세스 토큰도 지원합니다.

## 웹훅
결제 완료, 환불 처리, 재고 변경 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## 샌드박스 환경
실제 거래 없이 테스트할 수 있는 개발 환경을 제공합니다.

## SDK 지원
iOS, Android, JavaScript, PHP, Ruby, Python 등 다양한 SDK를 제공합니다.

## 사용 사례
전자상거래 플랫폼, POS 시스템, 예약 시스템, 마켓플레이스, 모바일 결제 앱 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 36. Amplitude API
(
    36,
    1,
    '## 개요
Amplitude API는 제품 분석 플랫폼인 Amplitude의 데이터에 접근하고 분석할 수 있게 해주는 API입니다. 사용자 행동을 추적하고, 코호트 분석, 리텐션 분석 등 고급 제품 인사이트를 제공합니다.

## 주요 기능

### 이벤트 트래킹
사용자 행동을 이벤트로 기록하고, 사용자 속성과 이벤트 속성을 추가할 수 있습니다.

### 사용자 분석
개별 사용자의 행동 패턴을 추적하고 분석할 수 있습니다.

### 코호트 분석
특정 기간에 가입한 사용자 그룹의 행동을 시간에 따라 분석합니다.

### Funnel Analysis
전환 깔때기를 생성하고 각 단계의 전환율을 분석할 수 있습니다.

### 리텐션 분석
사용자 재방문율과 이탈률을 측정합니다.

### 세그멘테이션
사용자를 다양한 기준으로 세그먼트화하여 행동을 비교 분석할 수 있습니다.

## API 종류

### HTTP API
이벤트 데이터를 Amplitude로 전송하는 API입니다.

### Dashboard REST API
저장된 분석 데이터를 조회하는 API입니다.

### Export API
원시 이벤트 데이터를 내보낼 수 있습니다.

## Identify API
사용자 속성을 설정하고 업데이트할 수 있습니다.

## 인증 방식
API 키를 사용한 인증을 지원합니다.

## Batch API
여러 이벤트를 한 번에 전송하여 효율성을 높일 수 있습니다.

## SDK 지원
JavaScript, iOS, Android, Python, Node.js 등 다양한 플랫폼의 SDK를 제공합니다.

## 데이터 프라이버시
GDPR 준수를 위한 사용자 데이터 삭제 및 내보내기 기능을 제공합니다.

## 사용 사례
제품 사용 분석, A/B 테스트 평가, 사용자 여정 최적화, 기능 출시 영향 분석, 사용자 이탈 예방 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 37. Zoom API
(
    37,
    1,
    '## 개요
Zoom API는 화상 회의 플랫폼인 Zoom의 기능을 외부 애플리케이션과 통합할 수 있게 해주는 RESTful API입니다. 회의 생성, 사용자 관리, 웨비나 운영 등을 프로그래밍 방식으로 처리할 수 있습니다.

## 주요 기능

### 회의 관리
회의를 생성, 수정, 삭제하고 스케줄링할 수 있습니다. 반복 회의와 즉석 회의를 지원합니다.

### 사용자 관리
Zoom 계정의 사용자를 생성하고 관리할 수 있습니다. 역할과 권한을 설정할 수 있습니다.

### 웨비나 운영
대규모 온라인 세미나를 생성하고 관리할 수 있습니다. 참석자 등록과 Q&A를 처리합니다.

### 녹화 관리
클라우드 녹화 파일에 접근하고 관리할 수 있습니다.

### 리포팅
회의 참석 데이터, 사용 통계, 품질 지표 등을 조회할 수 있습니다.

### 채팅 기능
Zoom 채팅 메시지를 보내고 받을 수 있습니다.

## 웹훅
회의 시작, 종료, 참가자 입장 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## 인증 방식
OAuth 2.0과 JWT 토큰을 지원합니다. Server-to-Server OAuth도 가능합니다.

## SDK 지원
Video SDK를 통해 커스텀 비디오 경험을 구축할 수 있습니다.

## Zoom Apps
Zoom 클라이언트 내에서 실행되는 앱을 개발할 수 있습니다.

## Rate Limiting
엔드포인트별로 다른 제한이 적용되며, 일일 및 초당 요청 제한이 있습니다.

## 사용 사례
회의 예약 시스템, 출석 관리, 자동 녹화 아카이빙, CRM 통합, 교육 플랫폼, 원격 의료 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 38. WooCommerce
(
    38,
    1,
    '## 개요
WooCommerce API는 WordPress 기반의 오픈소스 전자상거래 플러그인인 WooCommerce의 RESTful API입니다. 온라인 스토어의 상품, 주문, 고객 데이터를 프로그래밍 방식으로 관리할 수 있습니다.

## 주요 기능

### 상품 관리
상품을 생성, 수정, 삭제하고 카테고리와 태그를 관리할 수 있습니다. 단순 상품, 변형 상품, 그룹 상품 등을 지원합니다.

### 주문 처리
주문을 조회하고, 상태를 업데이트하며, 주문 노트를 추가할 수 있습니다.

### 고객 관리
고객 정보를 생성하고 수정할 수 있습니다. 고객 주문 이력을 조회할 수 있습니다.

### 쿠폰 관리
할인 쿠폰을 생성하고 관리할 수 있습니다.

### 배송 및 세금
배송 영역, 방법, 요금을 설정하고 세금 설정을 관리할 수 있습니다.

### 리포팅
판매 리포트, 상품 통계, 고객 분석 등의 데이터를 조회할 수 있습니다.

## API 버전
REST API v3가 최신 버전이며, 이전 버전인 Legacy API도 지원합니다.

## 인증 방식
OAuth 1.0a와 기본 인증을 지원합니다. HTTPS 연결에서 기본 인증이 권장됩니다.

## 웹훅
주문 생성, 상품 업데이트, 재고 변경 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## CLI 도구
WP-CLI를 통해 명령줄에서 WooCommerce를 관리할 수 있습니다.

## Rate Limiting
기본적으로 제한이 없지만, 서버 성능에 따라 자체 제한을 설정할 수 있습니다.

## 사용 사례
재고 관리 시스템, 주문 처리 자동화, 모바일 앱 연동, 마켓플레이스 통합, 회계 시스템 연동 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 39. Unsplash API
(
    39,
    1,
    '## 개요
Unsplash API는 고품질의 무료 스톡 사진을 제공하는 플랫폼의 API입니다. 200만 개 이상의 전문가급 이미지에 접근할 수 있으며, 상업적 및 비상업적 용도로 자유롭게 사용 가능합니다.

## 주요 기능

### 사진 검색
키워드로 사진을 검색하고, 색상, 방향으로 필터링할 수 있습니다.

### 큐레이션 컬렉션
에디토리얼 컬렉션, 테마별 컬렉션에 접근할 수 있습니다.

### 사진 상세 정보
개별 사진의 메타데이터, 작가 정보, 다운로드 링크를 조회할 수 있습니다.

### 랜덤 사진
특정 카테고리나 키워드의 랜덤 사진을 가져올 수 있습니다.

### 통계 정보
사진의 조회수, 다운로드 수 등의 통계를 확인할 수 있습니다.

### 다양한 해상도
원본 해상도부터 썸네일까지 다양한 크기의 이미지를 제공합니다.

## 라이선스
모든 이미지는 Unsplash License 하에 제공되며, 저작권 표시 없이 자유롭게 사용할 수 있습니다. 단, Unsplash 크레딧 표기가 권장됩니다.

## 인증 방식
OAuth 2.0과 Access Key를 통한 인증을 지원합니다.

## Rate Limiting
시간당 50개의 요청으로 제한되며, 프로덕션 키를 신청하면 더 많은 요청이 가능합니다.

## 다운로드 트래킹
API를 통한 다운로드는 사진 작가에게 크레딧으로 인정됩니다.

## JSON 응답
이미지 URL, 작가 정보, 설명, 위치 등의 메타데이터를 제공합니다.

## 사용 사례
웹사이트 배경 이미지, 블로그 포스트 이미지, 디자인 프로젝트, 프레젠테이션 자료, 소셜 미디어 콘텐츠 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 40. SendGrid
(
    40,
    1,
    '## 개요
SendGrid API는 이메일 전송 서비스를 제공하는 클라우드 기반 플랫폼의 API입니다. 트랜잭셔널 이메일과 마케팅 이메일을 안정적으로 발송하고, 전송 통계를 추적할 수 있습니다.

## 주요 기능

### 이메일 전송
SMTP 또는 Web API를 통해 이메일을 발송할 수 있습니다. 대량 발송과 개인화된 이메일을 지원합니다.

### 템플릿 관리
동적 이메일 템플릿을 생성하고 관리할 수 있습니다. 변수와 조건부 콘텐츠를 지원합니다.

### 리스트 관리
수신자 리스트를 생성하고 세그먼트를 관리할 수 있습니다.

### 이메일 검증
이메일 주소의 유효성을 검증하여 반송률을 줄일 수 있습니다.

### 스팸 체크
발송 전 스팸 필터를 통과할 수 있는지 확인합니다.

### 전송 통계
오픈율, 클릭율, 반송률, 스팸 신고 등의 통계를 실시간으로 확인할 수 있습니다.

## 웹훅
이메일 전송, 오픈, 클릭, 반송 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## 이메일 활동 피드
모든 이메일 활동을 조회하고 필터링할 수 있습니다.

## 인증 방식
API 키를 사용한 인증을 지원합니다. 다양한 권한 레벨의 키를 생성할 수 있습니다.

## IP 관리
전용 IP 주소를 사용하여 이메일 평판을 관리할 수 있습니다.

## 구독 취소 관리
구독 취소 요청을 자동으로 처리하고 관리할 수 있습니다.

## SDK 지원
Node.js, Python, PHP, Ruby, C#, Go 등 다양한 언어의 SDK를 제공합니다.

## 사용 사례
비밀번호 재설정, 주문 확인, 배송 알림, 뉴스레터, 마케팅 캠페인, 트랜잭션 알림 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 41. Naver Papago
(
    41,
    1,
    '## 개요
Naver Papago API는 네이버의 인공지능 기반 번역 서비스를 제공하는 API입니다. 특히 한국어 번역에 최적화되어 있으며, 신경망 기계 번역 기술을 사용하여 자연스러운 번역을 제공합니다.

## 주요 기능

### 텍스트 번역
단어, 문장, 문단을 번역할 수 있습니다. 문맥을 고려한 정확한 번역을 제공합니다.

### 언어 감지
입력 텍스트의 언어를 자동으로 감지합니다.

### 웹사이트 번역
URL을 입력하여 웹페이지 전체를 번역할 수 있습니다.

### 이미지 번역
이미지 내의 텍스트를 인식하고 번역합니다.

### 음성 번역
음성 입력을 텍스트로 변환한 후 번역합니다.

## 지원 언어
한국어, 영어, 일본어, 중국어(간체/번체), 스페인어, 프랑스어, 독일어, 러시아어, 포르투갈어, 이탈리아어, 베트남어, 태국어, 인도네시아어, 힌디어 등을 지원합니다.

## 번역 품질
한국어-영어, 한국어-일본어, 한국어-중국어 번역에서 특히 뛰어난 성능을 보입니다.

## 인증 방식
Client ID와 Client Secret을 HTTP 헤더에 포함하여 인증합니다.

## API 엔드포인트
텍스트 번역, 언어 감지, 로마자 변환 등의 엔드포인트를 제공합니다.

## Rate Limiting
무료 플랜은 일일 10,000자로 제한되며, 유료 플랜은 사용량에 따라 과금됩니다.

## 사용 사례
다국어 웹사이트, 채팅 앱 실시간 번역, 문서 번역, 여행 앱, 언어 학습 도구, 국제 비즈니스 커뮤니케이션 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 42. Youtube API
(
    42,
    1,
    '## 개요
YouTube API는 Google의 동영상 플랫폼인 YouTube의 데이터와 기능에 접근할 수 있게 해주는 RESTful API입니다. 동영상 검색, 업로드, 재생목록 관리, 댓글 처리 등이 가능합니다.

## 주요 기능

### 동영상 검색
키워드로 동영상을 검색하고, 필터와 정렬 옵션을 사용할 수 있습니다.

### 동영상 업로드
프로그래밍 방식으로 동영상을 업로드하고 메타데이터를 설정할 수 있습니다.

### 재생목록 관리
재생목록을 생성, 수정, 삭제하고 동영상을 추가할 수 있습니다.

### 댓글 관리
댓글을 조회하고, 작성하며, 답글을 달 수 있습니다.

### 채널 정보
채널 데이터, 구독자 수, 통계를 조회할 수 있습니다.

### 라이브 스트리밍
라이브 방송을 생성하고 관리할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, API 키만으로도 일부 읽기 작업이 가능합니다.

## 할당량 시스템
모든 API 요청은 할당량 단위를 소비하며, 일일 10,000 단위가 무료로 제공됩니다.

## YouTube Analytics API
채널 및 동영상의 상세한 분석 데이터를 제공합니다.

## YouTube Player API
웹사이트에 YouTube 플레이어를 임베드하고 제어할 수 있습니다.

## 사용 사례
동영상 큐레이션 플랫폼, 콘텐츠 관리 도구, 소셜 미디어 통합, 분석 대시보드, 동영상 마케팅 도구 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 43. Giphy API
(
    43,
    1,
    '## 개요
Giphy API는 세계 최대의 GIF 라이브러리인 Giphy의 콘텐츠에 접근할 수 있게 해주는 API입니다. 수십억 개의 애니메이션 GIF를 검색하고, 트렌딩 GIF를 가져오며, 임베드할 수 있습니다.

## 주요 기능

### GIF 검색
키워드로 GIF를 검색하고, 관련도 또는 최신순으로 정렬할 수 있습니다.

### 트렌딩 GIF
현재 인기 있는 GIF를 가져올 수 있습니다.

### 번역 기능
검색어를 가장 관련성 높은 단일 GIF로 변환합니다.

### 랜덤 GIF
특정 태그의 랜덤 GIF를 가져올 수 있습니다.

### 스티커
애니메이션 스티커를 검색하고 사용할 수 있습니다.

### GIF 업로드
Giphy에 새로운 GIF를 업로드할 수 있습니다.

## 인증 방식
API 키를 사용한 간단한 인증 방식입니다.

## Rate Limiting
무료 플랜에서도 제한이 매우 관대하며, 대부분의 애플리케이션에 충분합니다.

## 응답 형식
JSON 형식으로 GIF URL, 크기, 제목 등의 정보를 제공합니다.

## 다양한 크기
원본, 다운샘플, 고정 높이/너비 등 다양한 크기의 GIF를 제공합니다.

## 사용 사례
메시징 앱, 소셜 미디어 플랫폼, 댓글 시스템, 이메일 클라이언트, 챗봇 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 44. AWS S3
(
    44,
    1,
    '## 개요
AWS S3 API는 Amazon Web Services의 객체 스토리지 서비스인 Simple Storage Service에 접근할 수 있게 해주는 API입니다. 확장 가능하고 내구성 있는 클라우드 스토리지를 제공합니다.

## 주요 기능

### 객체 저장 및 검색
파일을 업로드하고 다운로드할 수 있습니다. 멀티파트 업로드로 대용량 파일을 효율적으로 처리합니다.

### 버킷 관리
스토리지 컨테이너인 버킷을 생성하고 관리할 수 있습니다.

### 액세스 제어
IAM 정책, 버킷 정책, ACL을 사용하여 세밀한 권한 관리가 가능합니다.

### 버전 관리
객체의 여러 버전을 보관하고 복원할 수 있습니다.

### 수명 주기 관리
자동으로 객체를 다른 스토리지 클래스로 이동하거나 삭제할 수 있습니다.

### 정적 웹사이트 호스팅
S3 버킷을 정적 웹사이트로 구성할 수 있습니다.

## 스토리지 클래스
Standard, Intelligent-Tiering, Standard-IA, Glacier 등 다양한 스토리지 옵션을 제공합니다.

## 보안
서버 측 암호화, 클라이언트 측 암호화, SSL/TLS 전송을 지원합니다.

## S3 Select
SQL 쿼리로 객체 내용을 필터링하여 가져올 수 있습니다.

## 인증 방식
AWS Signature V4를 사용한 인증을 지원합니다.

## 이벤트 알림
객체 생성, 삭제 등의 이벤트를 Lambda, SNS, SQS로 전달할 수 있습니다.

## 사용 사례
백업 및 아카이빙, 데이터 레이크, 정적 웹 호스팅, 미디어 저장소, 빅데이터 분석 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 45. GitHub API
(
    45,
    1,
    '## 개요
GitHub API는 코드 호스팅 플랫폼인 GitHub의 거의 모든 기능에 프로그래밍 방식으로 접근할 수 있게 해주는 RESTful 및 GraphQL API입니다.

## 주요 기능

### 저장소 관리
저장소를 생성, 수정, 삭제하고 설정을 변경할 수 있습니다.

### 이슈 및 PR
이슈와 풀 리퀘스트를 생성하고, 라벨을 추가하며, 댓글을 달 수 있습니다.

### 코드 검색
저장소, 코드, 이슈, 사용자를 검색할 수 있습니다.

### Gist 관리
코드 스니펫을 생성하고 공유할 수 있습니다.

### GitHub Actions
CI/CD 워크플로우를 트리거하고 관리할 수 있습니다.

### 웹훅
푸시, PR, 이슈 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## GraphQL API
더 효율적인 데이터 조회를 위한 GraphQL API를 제공합니다.

## 인증 방식
Personal Access Token, OAuth App, GitHub App을 통한 인증을 지원합니다.

## Rate Limiting
인증된 요청은 시간당 5,000개로 제한됩니다.

## GitHub Apps
GitHub 플랫폼에 통합되는 앱을 개발할 수 있습니다.

## 사용 사례
CI/CD 통합, 프로젝트 관리 도구, 코드 리뷰 자동화, 이슈 트래킹, 문서 자동화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 46. Firebase
(
    46,
    1,
    '## 개요
Firebase는 Google의 모바일 및 웹 애플리케이션 개발 플랫폼으로, 백엔드 서비스, 인증, 데이터베이스, 호스팅 등을 제공합니다.

## 주요 기능

### Realtime Database
실시간 NoSQL 데이터베이스로 데이터 동기화를 자동으로 처리합니다.

### Cloud Firestore
확장 가능한 NoSQL 문서 데이터베이스입니다.

### Authentication
이메일/비밀번호, 소셜 로그인 등 다양한 인증 방식을 지원합니다.

### Cloud Storage
파일 업로드 및 다운로드 기능을 제공합니다.

### Cloud Functions
서버리스 백엔드 코드를 실행할 수 있습니다.

### Hosting
정적 웹사이트와 웹 앱을 호스팅합니다.

## Firebase Admin SDK
서버 환경에서 Firebase 서비스에 접근할 수 있습니다.

## 실시간 동기화
여러 클라이언트 간 데이터를 실시간으로 동기화합니다.

## 오프라인 지원
네트워크 연결이 없어도 데이터를 로컬에 캐시합니다.

## 보안 규칙
세밀한 데이터 액세스 제어를 설정할 수 있습니다.

## 사용 사례
모바일 앱 백엔드, 실시간 채팅, 협업 도구, 게임 백엔드, IoT 애플리케이션 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 47. Twitter API
(
    47,
    1,
    '## 개요
X API는 소셜 미디어 플랫폼 X의 데이터와 기능에 접근할 수 있게 해주는 API입니다. 트윗 게시, 검색, 타임라인 조회 등이 가능합니다.

## 주요 기능

### 트윗 작성
텍스트, 이미지, 비디오를 포함한 트윗을 게시할 수 있습니다.

### 트윗 검색
키워드로 트윗을 검색하고 필터링할 수 있습니다.

### 타임라인 조회
사용자 타임라인, 홈 타임라인, 멘션을 조회할 수 있습니다.

### 팔로우 관리
팔로우, 언팔로우, 팔로워 목록 조회가 가능합니다.

### 리트윗 및 좋아요
리트윗, 좋아요를 추가하거나 취소할 수 있습니다.

### 다이렉트 메시지
DM을 보내고 받을 수 있습니다.

## API v2
최신 버전으로 개선된 기능과 더 나은 성능을 제공합니다.

## 인증 방식
OAuth 1.0a와 OAuth 2.0을 지원합니다.

## Rate Limiting
플랜에 따라 다른 제한이 적용됩니다.

## 스트리밍 API
실시간으로 트윗 스트림을 받을 수 있습니다.

## 사용 사례
소셜 미디어 분석, 자동 포스팅, 고객 서비스 봇, 트렌드 모니터링, 감정 분석 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 48. Salesforce API
(
    48,
    1,
    '## 개요
Salesforce API는 세계 최대의 CRM 플랫폼인 Salesforce의 데이터와 기능에 접근할 수 있게 해주는 API입니다. 고객 관리, 영업 자동화, 마케팅 캠페인을 프로그래밍 방식으로 처리할 수 있습니다.

## 주요 기능

### 객체 관리
계정, 연락처, 리드, 기회 등의 CRM 객체를 생성, 읽기, 업데이트, 삭제할 수 있습니다.

### SOQL 쿼리
SQL과 유사한 쿼리 언어로 데이터를 조회할 수 있습니다.

### Apex 실행
서버 측 비즈니스 로직을 실행할 수 있습니다.

### 메타데이터 관리
조직의 구조와 설정을 관리할 수 있습니다.

### Bulk API
대량의 데이터를 효율적으로 처리할 수 있습니다.

## API 유형

### REST API
표준 RESTful 인터페이스로 대부분의 기능을 제공합니다.

### SOAP API
엔터프라이즈급 통합에 적합한 SOAP 프로토콜을 사용합니다.

### Streaming API
실시간 데이터 변경 알림을 받을 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, 다양한 인증 플로우를 지원합니다.

## Lightning Platform
커스텀 앱을 구축할 수 있는 플랫폼을 제공합니다.

## 사용 사례
CRM 통합, 영업 자동화, 마케팅 캠페인 관리, 고객 서비스, 데이터 동기화 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 49. Kakao Maps
(
    49,
    1,
    '## 개요
Kakao Maps API는 카카오의 지도 서비스를 활용할 수 있게 해주는 API입니다. 한국 지역에 최적화된 상세한 지도 정보와 위치 기반 서비스를 제공합니다.

## 주요 기능

### 지도 표시
웹과 모바일 앱에 지도를 표시하고 다양한 스타일을 적용할 수 있습니다.

### 주소 검색
키워드로 장소를 검색하고 상세 정보를 조회할 수 있습니다.

### 좌표 변환
주소를 좌표로, 좌표를 주소로 변환할 수 있습니다.

### 길찾기
자동차, 도보, 자전거 경로를 계산할 수 있습니다.

### 로드뷰
거리뷰 이미지를 표시할 수 있습니다.

### 지역 정보
행정구역, 우편번호 등의 지역 정보를 조회할 수 있습니다.

## 인증 방식
REST API Key와 JavaScript Key를 사용한 인증을 지원합니다.

## SDK 지원
JavaScript, iOS, Android SDK를 제공합니다.

## Rate Limiting
일일 30만 건의 무료 호출을 제공하며, 초과 시 유료입니다.

## 사용 사례
배달 앱, 부동산 서비스, 여행 플랫폼, 매장 찾기, 위치 추적 등에 활용됩니다.
',
    NOW(),
    NOW()
),

-- 50. Facebook Graph
(
    50,
    1,
    '## 개요
Facebook Graph API는 Meta의 소셜 네트워크 플랫폼인 Facebook의 데이터와 기능에 접근할 수 있게 해주는 API입니다. 사용자 프로필, 페이지, 그룹, 이벤트 등을 관리할 수 있습니다.

## 주요 기능

### 프로필 정보
사용자의 기본 프로필 정보, 친구 목록, 사진 등에 접근할 수 있습니다.

### 페이지 관리
Facebook 페이지에 포스트를 게시하고, 댓글을 관리하며, 인사이트를 조회할 수 있습니다.

### 그룹 관리
그룹 정보를 조회하고 그룹에 포스트할 수 있습니다.

### 이벤트 관리
이벤트를 생성하고 참석자를 관리할 수 있습니다.

### 광고 관리
Facebook 광고 캠페인을 생성하고 관리할 수 있습니다.

## 인증 방식
OAuth 2.0을 사용하며, 액세스 토큰을 통해 인증합니다.

## 권한 시스템
세밀한 권한 요청으로 필요한 데이터에만 접근할 수 있습니다.

## 버전 관리
정기적으로 새 버전이 출시되며, 구 버전은 일정 기간 후 지원 종료됩니다.

## Webhooks
페이지 메시지, 댓글 등의 이벤트를 실시간으로 알림받을 수 있습니다.

## 사용 사례
소셜 로그인, 소셜 미디어 관리, 광고 자동화, 커뮤니티 관리, 분석 도구 등에 활용됩니다.
',
    NOW(),
    NOW()
);

-- 4. 카테고리 데이터 삽입
INSERT INTO categories (name, created_at, updated_at) VALUES
-- ㄱ
('개발', NOW(), NOW()),
('게임', NOW(), NOW()),
('계산', NOW(), NOW()),
('결제', NOW(), NOW()),
('금융', NOW(), NOW()),
-- ㄴ ~ ㄹ
('날씨', NOW(), NOW()),
('뉴스', NOW(), NOW()),
('데이터', NOW(), NOW()),
-- ㅁ
('마케팅', NOW(), NOW()),
('머신러닝', NOW(), NOW()),
('메시징', NOW(), NOW()),
('미디어', NOW(), NOW()),
-- ㅂ
('백엔드', NOW(), NOW()),
('번역', NOW(), NOW()),
('봇', NOW(), NOW()),
('분석', NOW(), NOW()),
('비디오', NOW(), NOW()),
('비즈니스', NOW(), NOW()),
-- ㅅ
('생산성', NOW(), NOW()),
('소셜', NOW(), NOW()),
('소셜로그인', NOW(), NOW()),
('쇼핑', NOW(), NOW()),
('스토리지', NOW(), NOW()),
-- ㅇ
('위치', NOW(), NOW()),
('음악', NOW(), NOW()),
('이미지', NOW(), NOW()),
('이메일', NOW(), NOW()),
('인지', NOW(), NOW()),
('인증', NOW(), NOW()),
-- ㅈ
('전자상거래', NOW(), NOW()),
('지도', NOW(), NOW()),
('지식', NOW(), NOW()),
-- ㅋ
('커뮤니케이션', NOW(), NOW()),
('커뮤니티', NOW(), NOW()),
('클라우드', NOW(), NOW()),
-- ㅌ ~ ㅎ
('통신', NOW(), NOW()),
('프로젝트관리', NOW(), NOW()),
('협업', NOW(), NOW()),
('화상회의', NOW(), NOW()),
-- 영문
('AI', NOW(), NOW()),
('CRM', NOW(), NOW()),
('DB', NOW(), NOW()),
('GIF', NOW(), NOW()),
('POS', NOW(), NOW()),
('SMS', NOW(), NOW()),
('SNS', NOW(), NOW());

-- 5. API-카테고리 매핑
INSERT INTO api_categories_map (api_id, category_id) VALUES
-- 1. Spotify API ["음악","미디어"]
(1, (SELECT id FROM categories WHERE name = '음악')),
(1, (SELECT id FROM categories WHERE name = '미디어')),

-- 2. Jira API ["개발","프로젝트관리"]
(2, (SELECT id FROM categories WHERE name = '개발')),
(2, (SELECT id FROM categories WHERE name = '프로젝트관리')),

-- 3. Google Translate ["번역","AI"]
(3, (SELECT id FROM categories WHERE name = '번역')),
(3, (SELECT id FROM categories WHERE name = 'AI')),

-- 4. Weather API ["날씨","데이터"]
(4, (SELECT id FROM categories WHERE name = '날씨')),
(4, (SELECT id FROM categories WHERE name = '데이터')),

-- 5. Telegram Bot ["메시징","봇"]
(5, (SELECT id FROM categories WHERE name = '메시징')),
(5, (SELECT id FROM categories WHERE name = '봇')),

-- 6. Mixpanel API ["분석","데이터"]
(6, (SELECT id FROM categories WHERE name = '분석')),
(6, (SELECT id FROM categories WHERE name = '데이터')),

-- 7. Trello API ["프로젝트관리","협업"]
(7, (SELECT id FROM categories WHERE name = '프로젝트관리')),
(7, (SELECT id FROM categories WHERE name = '협업')),

-- 8. Google Login ["소셜로그인","인증"]
(8, (SELECT id FROM categories WHERE name = '소셜로그인')),
(8, (SELECT id FROM categories WHERE name = '인증')),

-- 9. Stripe API ["결제","금융"]
(9, (SELECT id FROM categories WHERE name = '결제')),
(9, (SELECT id FROM categories WHERE name = '금융')),

-- 10. LinkedIn API ["비즈니스","SNS"]
(10, (SELECT id FROM categories WHERE name = '비즈니스')),
(10, (SELECT id FROM categories WHERE name = 'SNS')),

-- 11. Google Cloud Vision ["AI","이미지"]
(11, (SELECT id FROM categories WHERE name = 'AI')),
(11, (SELECT id FROM categories WHERE name = '이미지')),

-- 12. Instagram API ["SNS","소셜"]
(12, (SELECT id FROM categories WHERE name = 'SNS')),
(12, (SELECT id FROM categories WHERE name = '소셜')),

-- 13. Discord API ["커뮤니케이션","게임"]
(13, (SELECT id FROM categories WHERE name = '커뮤니케이션')),
(13, (SELECT id FROM categories WHERE name = '게임')),

-- 14. Asana API ["프로젝트관리","협업"]
(14, (SELECT id FROM categories WHERE name = '프로젝트관리')),
(14, (SELECT id FROM categories WHERE name = '협업')),

-- 15. Wolfram Alpha ["지식","계산"]
(15, (SELECT id FROM categories WHERE name = '지식')),
(15, (SELECT id FROM categories WHERE name = '계산')),

-- 16. Notion API ["생산성","협업"]
(16, (SELECT id FROM categories WHERE name = '생산성')),
(16, (SELECT id FROM categories WHERE name = '협업')),

-- 17. HubSpot API ["마케팅","CRM"]
(17, (SELECT id FROM categories WHERE name = '마케팅')),
(17, (SELECT id FROM categories WHERE name = 'CRM')),

-- 18. Pexels API ["이미지","비디오"]
(18, (SELECT id FROM categories WHERE name = '이미지')),
(18, (SELECT id FROM categories WHERE name = '비디오')),

-- 19. Slack API ["협업","커뮤니케이션"]
(19, (SELECT id FROM categories WHERE name = '협업')),
(19, (SELECT id FROM categories WHERE name = '커뮤니케이션')),

-- 20. OpenStreetMap ["지도","위치"]
(20, (SELECT id FROM categories WHERE name = '지도')),
(20, (SELECT id FROM categories WHERE name = '위치')),

-- 21. PayPal API ["결제","금융"]
(21, (SELECT id FROM categories WHERE name = '결제')),
(21, (SELECT id FROM categories WHERE name = '금융')),

-- 22. Reddit API ["SNS","커뮤니티"]
(22, (SELECT id FROM categories WHERE name = 'SNS')),
(22, (SELECT id FROM categories WHERE name = '커뮤니티')),

-- 23. Dropbox API ["스토리지","클라우드"]
(23, (SELECT id FROM categories WHERE name = '스토리지')),
(23, (SELECT id FROM categories WHERE name = '클라우드')),

-- 24. DeepL API ["번역","AI"]
(24, (SELECT id FROM categories WHERE name = '번역')),
(24, (SELECT id FROM categories WHERE name = 'AI')),

-- 25. Twilio SMS ["SMS","통신"]
(25, (SELECT id FROM categories WHERE name = 'SMS')),
(25, (SELECT id FROM categories WHERE name = '통신')),

-- 26. Azure Cognitive ["AI","인지"]
(26, (SELECT id FROM categories WHERE name = 'AI')),
(26, (SELECT id FROM categories WHERE name = '인지')),

-- 27. News API ["뉴스","미디어"]
(27, (SELECT id FROM categories WHERE name = '뉴스')),
(27, (SELECT id FROM categories WHERE name = '미디어')),

-- 28. IBM Watson ["AI","머신러닝"]
(28, (SELECT id FROM categories WHERE name = 'AI')),
(28, (SELECT id FROM categories WHERE name = '머신러닝')),

-- 29. OpenAI GPT-4 ["AI","번역"]
(29, (SELECT id FROM categories WHERE name = 'AI')),
(29, (SELECT id FROM categories WHERE name = '번역')),

-- 30. Mailchimp ["마케팅","이메일"]
(30, (SELECT id FROM categories WHERE name = '마케팅')),
(30, (SELECT id FROM categories WHERE name = '이메일')),

-- 31. Google Analytics ["분석","마케팅"]
(31, (SELECT id FROM categories WHERE name = '분석')),
(31, (SELECT id FROM categories WHERE name = '마케팅')),

-- 32. Shopify API ["전자상거래","쇼핑"]
(32, (SELECT id FROM categories WHERE name = '전자상거래')),
(32, (SELECT id FROM categories WHERE name = '쇼핑')),

-- 33. AWS Rekognition ["AI","이미지"]
(33, (SELECT id FROM categories WHERE name = 'AI')),
(33, (SELECT id FROM categories WHERE name = '이미지')),

-- 34. Google Drive ["스토리지","클라우드"]
(34, (SELECT id FROM categories WHERE name = '스토리지')),
(34, (SELECT id FROM categories WHERE name = '클라우드')),

-- 35. Square API ["결제","POS"]
(35, (SELECT id FROM categories WHERE name = '결제')),
(35, (SELECT id FROM categories WHERE name = 'POS')),

-- 36. Amplitude API ["분석","데이터"]
(36, (SELECT id FROM categories WHERE name = '분석')),
(36, (SELECT id FROM categories WHERE name = '데이터')),

-- 37. Zoom API ["화상회의","협업"]
(37, (SELECT id FROM categories WHERE name = '화상회의')),
(37, (SELECT id FROM categories WHERE name = '협업')),

-- 38. WooCommerce ["전자상거래","쇼핑"]
(38, (SELECT id FROM categories WHERE name = '전자상거래')),
(38, (SELECT id FROM categories WHERE name = '쇼핑')),

-- 39. Unsplash API ["이미지","미디어"]
(39, (SELECT id FROM categories WHERE name = '이미지')),
(39, (SELECT id FROM categories WHERE name = '미디어')),

-- 40. SendGrid ["이메일","마케팅"]
(40, (SELECT id FROM categories WHERE name = '이메일')),
(40, (SELECT id FROM categories WHERE name = '마케팅')),

-- 41. Naver Papago ["번역","AI"]
(41, (SELECT id FROM categories WHERE name = '번역')),
(41, (SELECT id FROM categories WHERE name = 'AI')),

-- 42. Youtube API ["미디어","SNS"]
(42, (SELECT id FROM categories WHERE name = '미디어')),
(42, (SELECT id FROM categories WHERE name = 'SNS')),

-- 43. Giphy API ["GIF","미디어"]
(43, (SELECT id FROM categories WHERE name = 'GIF')),
(43, (SELECT id FROM categories WHERE name

