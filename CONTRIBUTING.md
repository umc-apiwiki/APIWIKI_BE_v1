# 📜 Contributing Guidelines

협업 시 코드의 일관성과 효율적인 관리를 위해 아래의 규칙을 사용해주세요!

## 1. Commit Message Convention (커밋 메시지)

커밋 메시지는 **`태그: 제목`** 형식을 따릅니다.

| 태그 (Tag) | 사용 시점 (Description) | 예시 (Example) |
| --- | --- | --- |
| `feat` | 새로운 기능 추가 | `feat: 회원가입 API 구현` |
| `fix` | 버그 수정 | `fix: 로그인 시 NullPointerException 해결` |
| `docs` | 문서 수정 | `docs: README.md 설치 방법 업데이트` |
| `style` | 코드 포맷팅 (로직 변경 X) | `style: 코드 들여쓰기 정리` |
| `refactor` | 코드 리팩토링 (기능 변경 X) | `refactor: 중복되는 메소드 분리` |
| `test` | 테스트 코드 | `test: 회원가입 서비스 테스트 코드 작성` |
| `chore` | 빌드, 설정, 패키지 매니저 | `chore: application.yml DB 설정 변경` |


## 2. Branch Naming Strategy (브랜치 전략)

브랜치명은 **`타입/이슈번호-설명`** 형식을 따릅니다.

* **Format:** `{Type}/{Issue Number}-{Description}`

| 타입 (Type) | 사용 시점 | 작성 예시 |
| --- | --- | --- |
| `feature` | 새로운 기능 개발 | `feature/10-login-api` |
| `chore` | 빌드, 배포, 환경 설정 등 | `chore/10-aws-deployment` |
| `fix` | 버그 수정 | `fix/12-jwt-token-error` |
| `refactor` | 기능 변경 없는 코드 개선 | `refactor/15-member-service` |
| `hotfix` | 배포된 서버의 급한 에러 수정 | `hotfix/18-payment-error` |

## 3. Pull Request (PR) Convention

PR 제목은 커밋 메시지 타입과 동일한 태그를 사용하며, 관련 이슈 번호를 명시합니다.

* **Format:** `[Type] Title (#IssueNumber)`

### ✅ 작성 예시

* `[Feat] 로그인 API 구현 (#10)`
* `[Fix] JWT 토큰 만료 에러 해결 (#12)`
* `[Refactor] 회원 서비스 로직 개선 (#15)`

