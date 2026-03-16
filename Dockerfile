# ---- Build Stage ----
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Gradle wrapper & 설정 파일 먼저 복사 (의존성 캐싱)
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

# 의존성 미리 다운로드 (레이어 캐싱 활용)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스 코드 복사 후 빌드
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# ---- Runtime Stage ----
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
