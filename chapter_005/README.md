# Collection framework pro

## iterator
### 1. Итератор для двухмерного массива int[][]

Необходимо создать итератор для двухмерного массива.
 
int[][] value = {
   {1, 2}
   {3, 4}
};
 
метод next = должен вернуть последовательно 1, 2, 3, 4.
 
Старайтесь написать универсальное решение, чтобы оно не было жестко ориентировано на тестовый пример.
И хотя в примере указана квадратная матрица, программа должна корректно обрабатывать и jagged array тоже.
Пример jagged array - {{1},{2, 3, 4, 5,},{6, 7}, {8, 9, 10, 11, 12, 13, 14}} 
Не используйте в данном задании коллекции из JDK, вспомогательные массивы и т.д. 
Постарайтесь реализовать последовательным проходом по массиву. 
 
Перед отправкой решения - убедитесь, что программа успешно проходит тестовые методы

### 2. Создать итератор четные числа

Создать итератор возвращающий только четные цифры.
Итератор должен принимать список произвольных чисел.
 
public EvenIt(final int[] numbers) {
 
Iterator it = new EventIt(new int[] {4, 2, 1, 1});
 
методы
 
it.next() - возвращают только четные числа. В этом примере - это 4 и 2.
 
it.hasNext() - возвращает true, только если в массиве есть четные перед указателем.
 
Например. если мы дернем два раза метод next, то указатель сместить на второй элемент. При вызове метода hasNext - он вернет false. так как после указателя больше нет четных чисел.
 
Перед отправкой решения - убедитесь, что программа успешно проходит тестовые методы из прилагаемого файла.

Так как вы реализуете итератор, то желательно чтобы его поведение соответствовало спецификации и было ожидаемым для программиста, который будет его использовать. Перед реализацией ознакомьтесь пожалуйста с описанием интерфейса Iterator в спецификации API Java 8 - Iterator

- метод next в случае отсутствия элементов к возврату генерирует NoSuchElementException.
- метод next должен возвращать верные значения вне зависимости от того вызвал ли перед этим программист метод hasNext. Аналогично для hasNext. Результат работы ваших методов не должен зависеть от последовательности в которой программист вызывает методы, т.е. не полагайтесь на то, что программист будет вызывать методы именно в том порядке в котором вы ожидаете.
- не допускайте дублирования кода. Если вы видите, что методы next и hasNext имеют одинаковый код, то выносите этот код в отдельный метод и делайте уже его вызов.
- не используйте эксепшены для управления логикой вашей программы. Они созданы для обработки критических ситуаций + очень дороги в создании по сравнению с обычными объектами в Java.
- не оставляйте пустых методов в коде. Обратите внимание, что метод remove объявлен как дефолтный - это значит, что нет необходимости его реализовывать пустым, если вы не собираетесь переопределять его поведение. В коде не должно быть пустых методов, если ваша программа не поддерживает какой-либо функционал задекларированный в интерфейсе - прокидывайте UnsupportedOperationException. 

### 3. Создать convert(Iterator<Iterator>)

Реализовать класс с методом Iterator<Integer> convert(Iterator<Iterator<Integer>> it).

Что из себя представляет запись Iterator<Iterator<Integer>?.

Каждый итератор это последовательность.

Итератор 1 – 4 2 0 4 6 4 9

Итератор 2 – 0 9 8 7 5

Итератор 3 – 1 3 5 6 7 0 9 8 4

Если мы говорим о записи Итератор Итераторов. Значит итератор содержит не конечные значения, а сложенные итераторы.

Итератор - Итератор 1, Итератор 2, Итератор 3.

Метод convert должен принимать объект итератор итератор и возвращать Итератор чисел.

Iterator<Iterator<Integer> - ((4 2 0 4 6 4 9), (0 9 8 7 5), (1 3 5 6 7 0 9 8 4))

Метод должен возвращать

Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)

Метод не должен копировать данные. Нужно реализовать итератор, который будет пробегать по вложенными итераторам без копирования данных.

Шаблон класса
 
Перед отправкой решения - убедитесь, что программа успешно проходит тестовые методы из прилагаемого файла.

Пожелания:
Так как вы реализуете итератор, то желательно чтобы его поведение соответствовало спецификации и было ожидаемым для программиста, который будет его использовать. Перед реализацией ознакомьтесь пожалуйста с описанием интерфейса Iterator в спецификации API Java 8 - Iterator

- метод next в случае отсутствия элементов к возврату генерирует NoSuchElementException.
- метод next должен возвращать верные значения вне зависимости от того вызвал ли перед этим программист метод hasNext. Аналогично для hasNext. Результат работы ваших методов не должен зависеть от последовательности в которой программист вызывает методы, т.е. не полагайтесь на то, что программист будет вызывать методы именно в том порядке в котором вы ожидаете.
- не допускайте дублирования кода. Если вы видите, что методы next и hasNext имеют одинаковый код, то выносите этот код в отдельный метод и делайте уже его вызов.
- не используйте эксепшены для управления логикой вашей программы. Они созданы для обработки критических ситуаций + очень дороги в создании по сравнению с обычными объектами в Java.
- не оставляйте пустых методов в коде. Обратите внимание, что метод remove объявлен как дефолтный - это значит, что нет необходимости его реализовывать пустым, если вы не собираетесь переопределять его поведение. В коде не должно быть пустых методов, если ваша программа не поддерживает какой-либо функционал задекларированный в интерфейсе - прокидывайте UnsupportedOperationException. 

## Generic

### 1. Реализовать SimpleArray<T>

В этом задании необходимо сделать универсальную обертку над массивом. 

Создать класс:

public class SimpleArray<T>

Добавить методы:

add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;

set(int index, T model) - заменяет указанным элементом (model) элемент, находящийся по индексу index;

remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);

get(int index) - возвращает элемент, расположенный по указанному индексу;

Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры.

Объект должен принимать количество ячеек. Структура не должна быть динамической. Если идет переполнение надо выкинуть ошибку.

### 2. Реализовать Store<T extends Base>

Сделать интерфейс Store<T extends Base>,
где Base - это абстрактный класс для моделей c методами String getId();
Пример приведен выше.
 
1. Сделать два класса User, и Role, которые наследуют Base класс.
2. Сделать два класса хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
3. Помните. про инкапсуляцию. Вам нельзя изменять интерфейс Store. Например. replace(int index,  T model) - нельзя делать. Так как мы изменили входящий параметр.
 
4. После реализации проверьте можно ли избавиться от дублирования кода в вашем проекте. UserStore и RoleStore будут иметь один и тот же функционал. Общий для них функционал необходимо вынести в абстрактный класс AbstractStore.
 
5. Помните, что хранилище должны быть жестко ограничены хранимым типом. Например для UserStore тип хранимых данных должен быть User

## list

### 1. Создать динамический список на базе массива
### 2. Создать контейнер на базе связанного списка
### 3. Используя контейнер на базе связанного списка создать контейнер Stack
### 4. Очередь на двух стеках

## set
### Реализовать коллекцию Set на массиве

## map
### Реализовать собственную структуру данных - HashMap
Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
boolean insert(K key, V value);
V get(K key);
boolean delete(K key);

Реализовывать итератор.
Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение. Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.

Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.

## tree
### 1. Создать элементарную структуру дерева
### 2. Добавить метод boolean isBinary()
Добавить метод boolean isBinary().
 
Метод должен проверять количество дочерних элементов в дереве. Если их <= 2 - то дерево бинарное.
    
   

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    public boolean isBinary() {
        return false;
    }

метод должен циклически пройти по всем элементам дерева. Для этого можно использовать итератор из предыдущего задания.

