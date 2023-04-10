자바에서 `ArrayList`와 `LinkedList`는 모두`List` 인터페이스를 구현합니다.

`ArrayList`는 `AbstractList<E>`를 상속받고

`LinkedList`는 `AbstractSequentialList<E>`를 상속받습니다.

# ArrayList<E>

`ArrayList`는 `List`인터페이스를 구현한 클래스입니다. `AbstractList`를 상속받고 아래와 같은 필드를 가집니다.

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

		@java.io.Serial
    private static final long serialVersionUID = 8683452581122892189L;

    private static final int DEFAULT_CAPACITY = 10; // 디폴트 용량

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

		...

}
```

`ArrayList`는 동적 배열을 구현한 클래스입니다.

내부적으로 `elementData`라는 배열을 이용하여 데이터를 저장하며, 배열의 크기를 동적으로 조장하여 데이터의 추가, 삭제, 탐색 작업을 처리합니다.

확장과 축소(음,, 축소라기 보단 메모리 최적화?)가 가능한 동적배열을 사용한다는 점이 `ArrayList`의 가장 큰 특징입니다.

배열은 인덱스로 직접 접근이 가능하기 때문에 탐색 작업이 효율적입니다.

하지만, 배열의 크기를 동적으로 조절하기 때문에 추가, 삭제 작업 시에는 해당 위치의 데이터를 모두 이동시켜야 하는 비용이 발생하므로, 크기가 큰 배열에서는 성능이 저하될 수 있습니다.

---

## 생성자

`ArrayList`의 생성자는 총 3개입니다.

- 기본생성자
- 배열의 초기 크기 값을 설정하는 생성자
- 컬렉션을 `ArrayList`로 변환하는 생성자

### 기본생성자

아래는 `ArrayList`의 기본 생성자 입니다.

```java
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}
```

기본 생성자는 `ArrayList`의 필드인 `elementData`에`DEFAULTCAPACITY_EMPTY_ELEMENTDATA`라는 값을 할당 해주는 것을 확인할 수 있습니다.

```java
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

transient Object[] elementData;

private static final int DEFAULT_CAPACITY = 10;
```

> The array buffer into which the elements of the ArrayList are stored. The capacity of the ArrayList is the length of this array buffer. Any empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA will be expanded to DEFAULT_CAPACITY when the first element is added.
>

`elementData`는 배열의 요소가 저장되는 배열 버퍼입니다.

ArrayList의 용량은 `elementData`의 길이가 됩니다.. 기본 생성자를 통해 생성된 `ArrayList`의 `elementData`는 `DEFAULTCAPACITY_EMPTY_ELEMENTDATA`값을 가지게 됩니다. 이는 빈 `ArrayList`라는 의미입니다.

기본 생성자로의 생성 이후,  첫 번째 요소가 추가될 때 `DEFAULT_CAPACITY`만큼 확장됩니다.

`transient`는 직렬화 과정에서 제외하고 싶은 경우에 적용한다는데,, 공부가 필요할 것 같습니다.

### 배열의 초기 크기 값을 인자로 받는 생성자

```java
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}
```

파라미터로 넘어온 값을 초기용량으로 하는 빈 ArrayList를 생성합니다.

파라미터가 0이면 `elementData`에 `EMPTY_ELEMENTDATA`값을 할당하는데 이 값은 기본생성자에서 할당하는 `DEFAULTCAPACITY_EMPTY_ELEMENTDATA`와 같이 빈 배열의 값을 가집니다.

그럼 파라미터가 0이면 기본생성자를 호출하면 되는 것 아니냐?

> Shared empty array instance used for default sized empty instances. We distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when first element is added.
>

기본 생성자일 경우 첫 번째 요소가 추가되면 배열을 `DEFAULT_CAPACITY`만큼 늘려야 하기 때문에 `EMPTY_ELEMENTDATA`와 `DEFAULTCAPACITY_EMPTY_ELEMENTDATA`를 구분한다고 합니다.

나중에 해당 멤버들을 구분해서 배열을 늘리는 로직이 나올 듯 합니다.

### Collection을 인자로 받는 생성자

```java
public ArrayList(Collection<? extends E> c) {
    Object[] a = c.toArray();
    if ((size = a.length) != 0) {
        if (c.getClass() == ArrayList.class) {
            elementData = a;
        } else {
            elementData = Arrays.copyOf(a, size, Object[].class);
        }
    } else {
        // replace with empty array.
        elementData = EMPTY_ELEMENTDATA;
    }
}
```

컬렉션을 `Object`배열로 변환 한 이후 빈 배열일 시 `EMPTY_ELEMENTDATA`그렇지 않으면 배열을 카피하여 멤버에 할당합니다. `size`는 변환된 배열의 `length`로 초기화 됩니다.

즉, `Collection`을 `ArrayList`로 변환합니다. 모든 `Collection`은 `toArray`메서드를 통해 `Object`배열로 변환 될 수 있으니까요.

컬렉션이 `null`일 경우 `NullPointException`이 `throw`됩니다.

---

## 메서드

| Modifier and Type | Method and Description |
| --- | --- |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#add-E-(https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html e)Appends the specified element to the end of this list. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#add-int-E-(int index, https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html element)Inserts the specified element at the specified position in this list. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#addAll-java.util.Collection-(https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html<? extends https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> c)Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's Iterator. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#addAll-int-java.util.Collection-(int index, https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html<? extends https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> c)Inserts all of the elements in the specified collection into this list, starting at the specified position. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#clear--()Removes all of the elements from this list. |
| https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#clone--()Returns a shallow copy of this ArrayList instance. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#contains-java.lang.Object-(https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html o)Returns true if this list contains the specified element. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#ensureCapacity-int-(int minCapacity)Increases the capacity of this ArrayList instance, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#forEach-java.util.function.Consumer-(https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html<? super https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> action)Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception. |
| https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#get-int-(int index)Returns the element at the specified position in this list. |
| int | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#indexOf-java.lang.Object-(https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html o)Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#isEmpty--()Returns true if this list contains no elements. |
| https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#iterator--()Returns an iterator over the elements in this list in proper sequence. |
| int | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#lastIndexOf-java.lang.Object-(https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html o)Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#listIterator--()Returns a list iterator over the elements in this list (in proper sequence). |
| https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#listIterator-int-(int index)Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list. |
| https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-(int index)Removes the element at the specified position in this list. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-java.lang.Object-(https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html o)Removes the first occurrence of the specified element from this list, if it is present. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#removeAll-java.util.Collection-(https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html<?> c)Removes from this list all of its elements that are contained in the specified collection. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#removeIf-java.util.function.Predicate-(https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html<? super https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> filter)Removes all of the elements of this collection that satisfy the given predicate. |
| protected void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#removeRange-int-int-(int fromIndex, int toIndex)Removes from this list all of the elements whose index is between fromIndex, inclusive, and toIndex, exclusive. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#replaceAll-java.util.function.UnaryOperator-(https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> operator)Replaces each element of this list with the result of applying the operator to that element. |
| boolean | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#retainAll-java.util.Collection-(https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html<?> c)Retains only the elements in this list that are contained in the specified collection. |
| https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#set-int-E-(int index, https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html element)Replaces the element at the specified position in this list with the specified element. |
| int | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#size--()Returns the number of elements in this list. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#sort-java.util.Comparator-(https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html<? super https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> c)Sorts this list according to the order induced by the specified https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html. |
| https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#spliterator--()Creates a https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html#binding and fail-fast https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html over the elements in this list. |
| https://docs.oracle.com/javase/8/docs/api/java/util/List.html<https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html> | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#subList-int-int-(int fromIndex, int toIndex)Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. |
| https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html[] | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#toArray--()Returns an array containing all of the elements in this list in proper sequence (from first to last element). |
| <T> T[] | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#toArray-T:A-(T[] a)Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array. |
| void | https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#trimToSize--()Trims the capacity of this ArrayList instance to be the list's current size. |

아 많다,, 메서드는 너무 많으니 차근차근 추가해도록 하겠습니다

### trimToSize()

```java
public void trimToSize() {
    modCount++;
    if (size < elementData.length) {
        elementData = (size == 0)
          ? EMPTY_ELEMENTDATA
          : Arrays.copyOf(elementData, size);
    }
}
```

`ArrayList`의 크기를 실제 요소 수 만큼으로 변경합니다.

`modCount`는 `AbstractList`의 멤버 이고, 해당 배열이 구조적으로 수정된 횟수를 의미합니다. 구조적인 수정이란 배열의 크기를 변경하거나, 진행 중인 반복작업의 결과에 영향을 미치는 수정이라고 합니다.

## 의문점

```java
public static void main(String[] args){
    ArrayList arrayList = new ArrayList();
    System.out.println(arrayList.size()); // 0 

		// 왜??? size가 초기화 되지 않았는데 0이 나오지??
}
```

멤버 변수는 인스턴스화 되면서 기본 값으로 초기화 되기 때문

그렇다면 왜 지역변수는 초기화 안해주는 것인가 JVM!

[Weekly Java: 인스턴스 변수는 기본값으로 초기화되지만, 왜 지역 변수는 초기화되지 않나요?](https://sigridjin.medium.com/weekly-java-인스턴스-변수는-기본값으로-초기화되지만-왜-지역-변수는-초기화되지-않나요-bc7bf7a1a295)