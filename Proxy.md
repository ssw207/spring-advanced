# 인터페이스 vs 클래스 프록시
## 클래스 기반 프록시 제약
- 부모클래스 생성자 호출
- 클래스, 메소드에 final이 붙으면 상ㅅ속불가
## 인터페이스 기반의 프록시 제약
- 인터페이스가 필요하다는게 단점

# 리플랙션
- 클래스나 메서드의 메타정보를 얻을수 있다.
- 컴파일시 에러를 체크할수 없다

# JDK동적 프록시
- 인터페이스 기반으로 동작

# cblib 방식
- 상속을 사용하기때문에 부모클래스의 기본생성자가 필요
- 클래스에 final 키워드가 붙으면 상속이 안되기 때문에 불가능
- 메서드에 final 키워드가 붙으면 메서드를 오버라이딩 할수 없다. -> 동작하지 않음 
