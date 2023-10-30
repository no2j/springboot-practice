1. SpringBoot
   - Spring Legacy의 장점 계승, 단점 보완.
     - 초기설정시 간결한 설정.
     - 내장 서버 제공(Tomcat, Jetty, Undertow).
     - starter 의존성 통합 모듈제공으로 버전 관리 용이.
2. gradle
   - Build Tool
   - 직관적인 코드와 자동완성.
   - 다양한 Repository 사용가능.
     
3. Thymeleaf
   - 서버 템플릿 엔진.
   - build.gradle에 implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'로 라이브러리 추가하여 사용.
   - 사용할 html 파일에 `<html xmlns:th="http://www.thymeleaf.org">` 로 태그 수정.
    
4. H2
   - JAVA로 작성된 오픈소스 RDBMS
   - Spring Boot가 지원하는 In Memory DB
   - 가벼우며 설치가 쉽고 관리가 편하다.
   - 휘발성, 전원이 꺼지는 경우 데이터가 삭제된다.
