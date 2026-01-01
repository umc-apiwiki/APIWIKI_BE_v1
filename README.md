# 📚 API Wiki - Backend Server

> **최고의 API를 찾고 공유하세요!** <br/>
> **개발자들이 실제 사용 경험을 공유하며 함께 만드는 API 선택 가이드** <br/>
> **API Wiki의 백엔드 리포지토리입니다.**

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql&logoColor=white)
![AWS EC2](https://img.shields.io/badge/AWS%20EC2-232F3E?logo=amazon-aws&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF?logo=github-actions&logoColor=white)

## 🔥 서비스 목표
API 위키는 개발자들이 프로젝트에 적합한 API를 빠르고 정확하게 선택할 수 있도록 돕는 커뮤니티 기반 정보 플랫폼입니다.<br/>
위키피디아의 협업 방식과 Hugging Face의 실사용자 검증 모델을 결합하여,<br/>
분산된 API 정보를 한 곳에 모으고 실제 개발자들의 경험을 공유하는 생태계를 구축합니다.<br/>

- **핵심 목표**: API 조사 시간을 8시간에서 2시간으로 70% 단축
- **품질 목표**: API 선택 후 교체율을 35%에서 10%로 감소
- **커뮤니티 목표**: 월간 활성 기여자 100명 이상 확보

## 🏗️ Architecture (Infra & Deployment)

현재 AWS EC2 환경에 배포되어 있으며, **Nginx**를 리버스 프록시로 사용하여 <b>HTTPS(SSL)</b> 보안이 적용되어 있습니다.<br/>
GitHub Actions를 통해 **CI/CD 자동 배포 파이프라인**이 구축되어 있습니다.<br/>

* **Server URL:** `https://apiwiki-api.my-project.cloud`
* **Docs (Swagger):** `https://apiwiki-api.my-project.cloud/swagger-ui/index.html` (예정)

### 🔄 CI/CD Process
1.  GitHub `main` 브랜치에 코드 Push
2.  **GitHub Actions** 트리거 (Build & Test)
3.  `.jar` 파일 빌드 후 AWS EC2로 전송 (SCP)
4.  EC2 내부 `deploy.sh` 스크립트 실행
5.  기존 프로세스 종료 및 **자동 재배포**

> **⚠️ 배포 시 유의사항 (Known Limitations)**
> <br/>현재 인프라 비용 절감 및 구조 단순화를 위해 **단일 인스턴스 배포 방식**을 채택하고 있습니다.<br/>
> 이에 따라 배포 스크립트 실행 시 기존 프로세스가 종료되고 새 프로세스가 실행되는 동안 <b>약 15~30초 간의 다운타임(서비스 일시 중단)</b>이 발생합니다.<br/>
> 추후 트래픽 증가 시 **Nginx를 활용한 Blue/Green 무중단 배포** 도입을 통해 개선할 예정입니다.<br/>

## 🛠️ Getting Started (Local Development)

이 프로젝트를 로컬 환경에서 실행하기 위한 가이드입니다.

### 1. Prerequisites
* JDK 21 이상
* MySQL 8.0 이상

### 2. Installation
```bash
git clone [https://github.com/Your-Github-ID/apiwiki-backend.git](https://github.com/Your-Github-ID/apiwiki-backend.git)
cd apiwiki-backend
```

### 3. Environment Variables

보안을 위해 `application.yml`에 DB 접속 정보 등이 비워져 있습니다.<br/>
실행 시 아래 환경변수를 주입해야 정상 작동합니다.<br/><br/>

**[IntelliJ 설정 방법]**

1. 상단 실행 설정(`Run/Debug Configurations`) 클릭
2. `Modify options` -> `Environment variables` 선택
3. 아래 내용을 입력

| Key | Description | Example (Dummy) |
| --- | --- | --- |
| `DB_HOST` | RDS 엔드포인트 또는 로컬 주소 | `localhost` |
| `DB_PORT` | 데이터베이스 포트 | `3306` |
| `DB_NAME` | 스키마 이름 | `apiwiki` |
| `DB_USERNAME` | 데이터베이스 계정명 | `root` |
| `DB_PASSWORD` | 데이터베이스 비밀번호 | `1234` |

### 4. Build & Run

```bash
# Mac / Linux
./gradlew clean build
java -jar build/libs/apiwiki-backend-0.0.1-SNAPSHOT.jar

# Windows
./gradlew.bat clean build
java -jar build/libs/apiwiki-backend-0.0.1-SNAPSHOT.jar
```

## 📂 Tech Stack

| Category | Stack                                             |
| --- |---------------------------------------------------|
| **Language** | Java 21                                            |
| **Framework** | Spring Boot 3.4, Spring Security, Spring Data JPA |
| **Database** | MySQL                                             |
| **Infra** | AWS EC2, RDS                                      |
| **Web Server** | Nginx (Reverse Proxy, SSL/TLS)                    |
| **CI/CD** | GitHub Actions                                    |

## 🤝 Contribution

1. 이슈 생성 (Feature/Bug)
2. 브랜치 생성 (`feat/#이슈번호-기능명`)
3. 코드 작성 및 Commit
4. PR 생성 및 리뷰 요청
