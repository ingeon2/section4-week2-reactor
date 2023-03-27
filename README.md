여기는 웹 프레임워크의 사용 없이 just 리액티브 프로그래밍을 위한 실습.  
각각의 매서드들 알도록 예시들을 들어놓음.  
  
create 패키지는 새로운 Sequence를 생성(Creating)하고자 할 경우.  
transforamtion 패키지는 기존 Sequence에서 변환 작업(Transforming)이 필요한 경우.  
peeking 패키지는 Sequence 내부의 동작을 확인(Peeking)하고자 할 경우.  
errors 페키지는 에러를 처리(Handling errors)하고자 할 경우.  

핵심 포인트
Reactor의 Operator는 그 종류가 너무 많기 때문에 한번에 모든 Operator의 사용법을 익히는 것은 사실상 불가능하다.  
상황별로 자주 사용되는 Operator의 사용법을 익히고, 그때 그때 필요한 상황에 해당 Operator를 찾아서 사용하는 것이 좋다.  
flatMap() 처럼 내부에서 정의하는 Sequence를 Inner Sequece라고 한다.  
flatMap() Operator에 추가 쓰레드를 할당할 경우, 작업의 처리 순서를 보장하지 않는다.  
create() Operator는 프로그래밍 방식으로 Signal 이벤트를 발생시키는 Operator로써 한 번에 여러 건의 데이터를 비동기적으로 emit할 수 있다.  
Reactor의 concat() Operator는 논리적으로 하나의 Sequence로 이어 붙인 후, 이어 붙인 Sequecne에서 시간 순서대로 데이터를 차례대로 emit한다.  
zip() Operator는 입력으로 주어진 Sequence의 emit 시점이 매번 다르더라도 
동일 index 상의 emit 시점이 늦은 데이터가 emit될 때까지 대기했다가 데이터가 모두 emit되면 해당 데이터를 모두 전달 받는다.  
함수형 프로그래밍 세계에서 부수 효과(side-effect)는 어떤 동작을 실행하되 리턴 값이 없는 것을 의미한다.  
timeout() Operator는 입력으로 주어진 시간동안 emit되는 데이터가 없으면 onError Signal 이벤트를 발생시킨다.  

