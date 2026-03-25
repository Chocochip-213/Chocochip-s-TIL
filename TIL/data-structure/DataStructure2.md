# 데이터 스트럭처 2 요약

## 1. 문자열 (String)
문자열은 불변 객체. 메서드 실행 시 원본 변경 없이 새로운 문자열 반환.

```python
text = " Hello, Python "
text.find("o")
text.index("o")
text.replace("Python", "World")
text.strip()
text.split(",")
",".join(["a", "b", "c"])
```
2. 리스트 (List)
리스트는 가변 객체. 내부 요소 직접 수정 가능. 다양한 삽입, 삭제, 정렬 기능 제공.

python
복사
편집
lst = [1, 2, 3]
lst.append(4)
lst.extend([5, 6])
lst.insert(1, 10)
lst.remove(3)
lst.pop(2)
lst.index(10)
lst.count(1)
lst.reverse()
lst.sort()
3. 복사
얕은 복사는 상위 구조만 복사, 깊은 복사는 내부 구조까지 복사.
가변 객체 복사 시에는 깊은 복사를 통해 의도하지 않은 공유를 방지.

python
복사
편집
a = [1, 2, 3]
b = a[:]
c = a.copy()

import copy
a = [[1], [2]]
b = copy.deepcopy(a)
4. 딕셔너리 (Dictionary)
키와 값의 쌍으로 구성된 자료구조. 키는 고유하고 불변 객체만 가능.

python
복사
편집
d = {"a": 1, "b": 2}
d.get("a")
d.get("z", 0)
d.keys()
d.values()
d.items()
d.pop("a")
d.setdefault("c", 3)
d.update({"b": 4, "d": 5})
5. 세트 (Set)
중복을 허용하지 않는 집합 자료구조. 순서 없음. 빠른 연산이 가능.

python
복사
편집
s = {1, 2, 3}
s.add(4)
s.clear()

s = {1, 2, 3}
s.remove(2)
s.discard(5)
s.pop()
s.update([4, 5, 6])
집합 연산 (차집합, 교집합, 합집합, 포함 관계)
python
복사
편집
a = {1, 2, 3}
b = {3, 4, 5}

a - b      # 차집합
a & b      # 교집합
a | b      # 합집합
a <= b     # 부분집합 여부
a >= b     # 상위집합 여부
6. 메서드 체이닝
연속적으로 메서드를 호출할 수 있는 방식. 반환값이 객체일 때만 가능.

python
복사
편집
text = "HeLLo"
text.swapcase().replace("L", "Z")
7. 해시 테이블
파이썬의 딕셔너리와 세트는 해시 테이블 기반으로 작동.
불변 객체만 해시 가능. 중복 없는 빠른 조회에 유리.

python
복사
편집
hash("abc")
hash(123)
8. 내장 함수 직접 구현
내장 함수의 동작 원리를 이해하기 위해 수동 구현 연습.

길이 구하기
python
복사
편집
def custom_len(items):
    count = 0
    for _ in items:
        count += 1
    return count
최댓값 구하기
python
복사
편집
def custom_max(items):
    max_value = items[0]
    for i in items[1:]:
        if i > max_value:
            max_value = i
    return max_value
합계 구하기
python
복사
편집
def custom_sum(items):
    total = 0
    for i in items:
        total += i
    return total
특정 값의 인덱스 찾기
python
복사
편집
def custom_index(items, value):
    for index, val in enumerate(items):
        if val == value:
            return index
    return -1
리스트 뒤집기
python
복사
편집
def custom_reverse(items):
    new_list = []
    for i in range(len(items) - 1, -1, -1):
        new_list.append(items[i])
    return new_list
yaml
복사
편집

---