# 파이썬 프로그래밍의 핵심: 함수와 제어문

파이썬에서 함수와 제어문은 프로그램의 흐름을 설계하고, 코드를 효율적으로 관리하기 위해 꼭 알아야 할 핵심 요소입니다.

---

## 1. 함수 (Function)

함수는 특정 작업을 수행하는 코드의 묶음으로, 반복되는 코드를 간결하고 재사용 가능하게 만들어줍니다.

### 함수 정의 방법

```python
def add(a, b):
    """이 함수는 두 숫자를 더한 결과를 반환합니다."""
    result = a + b
    return result
```

- `def`: 함수를 정의할 때 사용하는 키워드입니다.
- `a`, `b`: 함수에 전달되는 **매개변수(parameter)** 입니다.
- `return`: 함수 실행 결과를 호출한 곳으로 돌려줍니다.
- `"""Docstring"""`: 함수의 용도를 설명하는 주석입니다.

### 함수 호출

```python
sum_result = add(5, 3)  # add 함수에 5와 3을 인자로 전달
print(sum_result)       # 출력: 8
```

---

### 다양한 인자(Arguments) 형태

#### 위치 인자 (Positional Arguments)

```python
def greet(name, message):
    print(f"{name}님, {message}!")

greet("홍길동", "안녕하세요")  # 위치 인자 사용
```

#### 키워드 인자 (Keyword Arguments)

```python
greet(message="반갑습니다", name="이순신")  # 키워드 인자 사용
```

#### 기본값 인자 (Default Argument)

```python
def greet(name, message="안녕하세요"):
    print(f"{name}님, {message}!")

greet("김철수")  # message는 생략되어 기본값 사용
```

#### 임의의 위치 인자 (`*args`)

```python
def add_all(*numbers):
    return sum(numbers)

print(add_all(1, 2, 3))       # 출력: 6
print(add_all(1, 2, 3, 4, 5)) # 출력: 15
```

#### 임의의 키워드 인자 (`**kwargs`)

```python
def print_info(**info):
    for key, value in info.items():
        print(f"{key}: {value}")

print_info(name="파이썬", version="3.11", author="Guido van Rossum")
```

---

## 2. 변수의 범위 (Scope)

함수 안과 밖에서 사용하는 변수는 서로 다른 범위를 갖습니다. 이를 **스코프(scope)** 라고 부릅니다.

| 변수 종류 | 정의 위치 | 사용 범위 |
| -------- | ---------- | ---------- |
| 전역 변수 (Global) | 함수 바깥 | 전체 프로그램 |
| 지역 변수 (Local)  | 함수 내부 | 해당 함수 내부 |

### 변수 이름 탐색 순서 (LEGB Rule)

1. **Local**: 현재 함수 내부
2. **Enclosed**: 중첩 함수의 바깥 함수
3. **Global**: 모듈의 최상단
4. **Built-in**: 파이썬 내장 영역

### 변수의 수명 주기

| 스코프 | 생성 시점 | 소멸 시점 |
|--------|------------|------------|
| Built-in | 파이썬 실행 시 | 프로그램 종료 시 |
| Global | 모듈 실행 시 | 인터프리터 종료 시 |
| Local | 함수 호출 시 | 함수 종료 시 |

---

## 3. 제어문 (Control Statement)

조건에 따라 코드의 실행 흐름을 제어하거나, 특정 코드를 반복적으로 실행할 때 사용됩니다.

---

### 조건문 (`if`, `elif`, `else`)

```python
score = 85

if score >= 90:
    print("A 학점입니다.")
elif score >= 80:
    print("B 학점입니다.")  # 실행됨
else:
    print("C 학점 이하입니다.")
```

- `if`: 조건이 참인 경우 실행
- `elif`: 앞 조건이 거짓일 경우, 새로운 조건 검사
- `else`: 모든 조건이 거짓일 경우 실행

#### 중첩 조건문

```python
x = 10

if x > 0:
    if x < 20:
        print("0보다 크고 20보다 작습니다.")
```

---

## 4. 반복문 (Loop)

### `for` 반복문

```python
# 리스트 순회
fruits = ['사과', '바나나', '체리']
for fruit in fruits:
    print(fruit)

# range() 사용
for i in range(5):
    print(i)  # 0부터 4까지 출력

# 딕셔너리 순회
my_dict = {'x': 1, 'y': 2}
for key in my_dict:
    print(key, my_dict[key])
```

### 중첩된 for문

```python
for i in range(2):
    for j in range(2):
        print(i, j)
```

---

### `while` 반복문

```python
a = 0
while a < 3:
    print(a)
    a += 1
print("끝")
```

```python
# 사용자 입력을 받을 때까지 반복
user_input = ""
while user_input != "exit":
    user_input = input("입력해주세요 (exit 입력 시 종료): ")
```

---

### 반복문 제어: `break`, `continue`, `pass`

```python
for i in range(10):
    if i == 3:
        continue  # 3 건너뜀
    if i == 7:
        break     # 7에서 반복 종료
    print(i)
```

- `break`: 반복문 즉시 종료
- `continue`: 현재 반복만 건너뛰고 다음 반복 진행
- `pass`: 아무 작업 없이 넘어감 (자리를 위한 구문)

---

## 5. 반복문 선택 기준

| 반복문 종류 | 사용 추천 상황 |
|-------------|----------------|
| `for`       | 반복 횟수가 정해져 있는 경우 |
| `while`     | 조건이 만족될 때까지 반복해야 하는 경우 |

---

이처럼 함수와 제어문은 파이썬의 기본적인 구조를 구성하는 데 매우 중요하며, 이를 잘 활용하면 프로그램의 구조를 더 명확하고 유연하게 만들 수 있습니다.
