# ThreadLocal
## 쓰레드 로컬이란?
- 멀티쓰레드 환경에서 객체의 상태값을 쓰레드별로 가지기 위해 사용하는 기술이다.
- Spring은 Bean을 기본적으로 싱글톤으로 관리하는데 이런 이유로 Bean 객체가 상태를 가지게되면 동시성 문제로 오류가 발생할수 있다.
- Thread Local은 클래스 필드의 값을 쓰레드 별로 가지게되어 멀티쓰레드 환경에서도 상태를 이용할수 있다.

## Spring에서는 어떻게 사용하나?
- DB에서 여러 작업을 하나의 트렌젝션으로 묶으려면 동일한 커넥션을 사용해야한다. 
- 톰켓을 멀티쓰레드 기반으로 요청마나 새로운 쓰레드를 할당하기 댸문에 만약 쓰레드 로컬을 사용할수 없다면 여러 메소드의 호출을 하나의 트렌젝션으로 묶으려 할때 메소드 파라미터로 커넥션 객체를 전달해야한다. 
- Spring에서는 이런 불편함을 트렌젝션 사용시 여러 메소드드 들을 파라미터의 전달없이 동일한 커넥션을 유지하기 위해 트렌젝션 상태 매니저를 사용한다.
- 트렌젝션 매니저를 통해 DataSource에서 꺼낸 커넥션 객체를 트렌젝션 상태 매니저에 전달하고 트렌젝션 상태 매니저는 내부의 ThreadLocal 타입의 필드에 저장한다.
- 메소드에서 데이터 접속로직을 호출시 트렌젝션 상태 매니저에서 커넥션 객체를 꺼내와 사용하기 때문에 파라미터로 커넥션을 전달하지 않으면서 멀티쓰레드 문제 없이 여러 메소드에서 같은 커넥션 객체를 사용할 수 있다.

## 사용시 주의점
- 쓰레드 로컬은 쓰레드에 저장되는 값이기 때문에 쓰레드풀 사용시 사용이 끝난 쓰레드 로컬 값을 삭제하지 않으면 문제가 발생한다.
- 사용이 끝난 쓰레드는 쓰레드 풀로 반환이 되는데, 쓰레드가 삭제된 것이 아니기 때문에 쓰레드 로컬 필드도 유지되게 된다.
- 만약 A사용사 정보를 쓰레드로컬에 담은 상태에서 요청이 종료된뒤 쓰레드로컬에 저장된값을 삭제하지 않은경우 B사용자가 사용자 정보를 요청시 쓰레드 로컬에 저장된 A사용저의 정보가 조회될 수 있다.