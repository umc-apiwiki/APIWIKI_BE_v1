# 📌 프로젝트 그라운드 룰 & 기술 스택

이 문서는 프로젝트 협업 규칙, 설계 원칙 및 구현 세부 사항을 정의합니다.

## 1. 🤝 협업 룰 (Collaboration)

### 기본 규칙
* **로컬 개발 환경:** Windows + IntelliJ IDEA
* **커밋 메시지 & 브랜치 전략:** `CONTRIBUTING.md`에 정의된 규칙 준수
* **Code Review:** 하나의 PR에 최소 **1명 이상의 리뷰** 승인 후 Merge

### PR/이슈 관리
* 작업 시작 전 이슈 생성 권장 (선택 사항)
* PR 본문에 관련 이슈 링크 (`Closes #이슈번호`) 포함

## 2. 📐 설계 문서 (Design)

| 구분 | 관리 방법 및 도구    | 비고                                           |
| :--- |:--------------|:---------------------------------------------|
| **API 명세서** | **Notion**    | 베타 버전 API 명세를 수정해서 사용                        |
| **에러 코드** | **API 명세서 내** | 메인 시나리오 외 예외 케이스는 API 명세서의 에러 코드를 통해 통합 관리 |
| **ERD** | **Mermaid**   | `docs/erd/` 경로에 `.md` 파일로 관리                  |
| **인프라 구성도** | **PPT**         | `docs/infra/` 경로에 이미지 파일(`.png`)로 관리 |

## 3. 🛠 구현 상세 (Implementation)

### Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.4.2
* **Build Tool:** Gradle
* **Deploy:** AWS EC2
* **Database:**
    * Local/Test: H2
    * Prod: MySQL 8.0.43

### Architecture & Pattern
* **프로젝트 구조:** 도메인형 패키지 구조 (Domain-driven)
* **데이터 접근:** 
    * 기본 : JPA + JPQL
    * 필터 필요 시 : QueryDSL (동적 쿼리)
* **API 문서화:** Swagger (OAS)
    * *구현 방식:* 워크북 예제에 따라 `Docs` 클래스를 상속하여 프로덕션 코드와 문서화 코드 분리
    * *용도:* 프론트엔드 팀과의 소통 전용

### Features & Logic
* **인증/인가:**
    * JWT (Access + Refresh Token)
    * (추가 옵션) OAuth2 소셜 로그인 (Google, GitHub)
* **DTO 전략:**
    * Java **`record`** 사용
    * **Inner Record 패턴 적용:** 도메인 이름의 껍데기 클래스(Wrapper Class) 내부에 `static record`로 그룹화하여 관리 (예: `MemberDto.JoinRequest`)
    * Entity ↔ DTO 변환 로직은 `record` 내부에 포함 (`from` 메서드 등)
* **데이터 관리:**
    * **삭제 정책:** Hard Delete 기본 (User 등 필요 시 Soft Delete 확장 고려)
    * **페이징:** 디자이너/프론트측 결정에 따라 16개 단위로 페이징 처리
* **공통 처리:**
    * 성공/에러 응답 형식(Global Response) 통일
    * `GlobalExceptionHandler`를 통한 전역 예외 처리

### DevOps
* **CI/CD:** GitHub Actions (Docker 없이 Jar 배포 방식 사용)
