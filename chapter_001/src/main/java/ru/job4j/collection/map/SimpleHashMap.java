package ru.job4j.collection.map;

import java.util.*;

/**
 * Простая реализация структуры данных HashMap.
 * @param <K> любой ссылочный тип.
 * @param <V> любой ссылочный тип.
 */
public final class SimpleHashMap<K, V> implements Iterable {
    /**
     * Максимальный размер таблицы.
     */
    private final static int MAX_CAPACITY = 1 << 30;

    /**
     * Размер таблицы по умолчанию.
     */
    private final static int DEFAULT_CAPACITY = 1 << 4;

    /**
     * Коэффициент заполнения по умолчанию.
     */
    private final static float LOAD_FACTOR = 0.75f;

    /**
     * Коэффициент заполнения таблицы.
     */
    private final float loadFactor;

    /**
     * Предельное количество элементов, при достижении которого размер хеш-таблицы увеличивается вдвое.
     */
    private int threshold;

    /**
     * Счетчик элементов.
     */
    private int elementCount = 0;

    /**
     * Счетчик изменений внутреннего массива.
     */
    private int modCount;

    /**
     * Элемент таблицы.
     * @param <K> любой ссылочный тип.
     * @param <V> любой ссылочный тип.
     */
    static class Node<K, V> {
        /**
         * Ключ.
         */
        final K key;
        /**
         * Значение.
         */
        V value;

        /**
         * Конструктор по всем полям.
         * @param key ключ.
         * @param value значение.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Геттер поля key.
         * @return значение поля key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Геттер поля value.
         * @return значение поля value.
         */
        public V getValue() {
            return value;
        }

        /**
         * Переопределение toString.
         * @return строку типа "key = value"
         */
        public final String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Внутренняя реализация использует массив.
     */
    private Node<K, V>[] table;

    /**
     * Конструктор по умолчанию.
     */
    public SimpleHashMap(int capacity, float load) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        int cap = capacity;
        if (capacity > MAX_CAPACITY) {
            cap = MAX_CAPACITY;
        }
        if (load <= 0 || Float.isNaN(load)) {
            throw new IllegalArgumentException("Illegal load factor: " + load);
        }
        this.table = new Node[cap];
        this.loadFactor = load;
        this.threshold = Math.round(cap * load);
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleHashMap(final int capacity) {
        this(capacity, LOAD_FACTOR);
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Метод возвращает размер таблицы.
     * @return размер таблицы.
     */
    public int size() {
        return this.elementCount;
    }

    /**
     * Метод преобразования содержимого таблицы в строку.
     * @return строка содержащая все данные таблицы.
     */
    @Override
    public String toString() {
        return "SimpleHashMap{"
                + "table=" + Arrays.toString(table)
                + '}';
    }

    /**
     * Метод вычисления хеш-функции.
     * @param key ключ.
     * @return хеш-значение.
     */
    public int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Метод добавления нового элемента в таблицу.
     * @param key ключ.
     * @param value значение.
     * @return true если элемент добавлен, false если уже есть такой элемент в этой ячейки.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int index = hash(key) & (this.table.length - 1);
        if (this.table[index] == null) {
            this.table[index] = new Node<>(key, value);
            this.elementCount++;
            ++this.modCount;
           result = true;
        }
        if (result && this.size() > this.threshold - 1) {
            this.resize(this.table.length << 1);
        }
        return result;
    }

    /**
     * Метод получения значения по ключу.
     * @param key ключ.
     * @return значение value, null если элемента нет или само значение null.
     */
    public V get(K key) {
        if (key == null) {
            return this.table[0] == null ? null : this.table[0].getValue();
        }
        int index = hash(key) & (this.table.length - 1);
        boolean result = this.table[index] != null
                        && this.table[index].getKey().equals(key);
        return result ? this.table[index].getValue() : null;
    }

    /**
     * Метод удаления элемента из таблицы.
     * @param key ключ.
     * @return true если элемент нашелся и был удален,
     * false - не нашелся, то нечего удалять.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = hash(key) & (this.table.length - 1);
        if (this.table[index] != null
                && Objects.equals(this.table[index].getKey(), key)
        ) {
            this.table[index] = null;
            this.elementCount--;
            ++this.modCount;
            result = true;
        }
        return result;
    }

    /**
     * Метод увиличения внутреннего массива вдвое, при достижении количества элементов значения threshold.
     * @param newCapacity старый размер масива увеличенный вдвое.
     */
    private void resize(int newCapacity) {
        if (newCapacity == MAX_CAPACITY) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        Node<K, V>[] newTable = new Node[newCapacity];
        this.transfer(newTable);
        this.table = newTable;
        this.threshold = Math.round(newCapacity * this.loadFactor);
    }

    /**
     * Метод переносит имеющиеся элементы в новый массив.
     * Перенос происходит при пересчете нового индекса.
     * @param newTable старый размер масива увеличенный вдвое.
     */
    private void transfer(Node[] newTable) {
        Iterator<Node<K, V>> iterator = this.iterator();
        Node<K, V> tmp = null;
        int index = 0;
        while (iterator.hasNext()) {
            tmp = iterator.next();
            index = this.hash(tmp.getKey()) & (newTable.length - 1);
            newTable[index] = tmp;
        }
    }

    /**
     * Метод возвращает итератор по данной структуре.
     * @return итератор по данной структуре.
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            /**
             * Начальная позиция.
             */
            private int position = 0;
            /**
             * Сколько всего элементов.
             */
            private int cursor = SimpleHashMap.this.size();
            /**
             * Индикатор изменения структуры.
             */
            private final int expectedMod = SimpleHashMap.this.modCount;

            /**
             * Метод проверяет изменилась, ли структура или нет.
             * @return true, если изменений структуры нет,
             * иначе бросается исключение ConcurrentModificationException.
             */
            private boolean isMod() {
                boolean rsl = this.expectedMod == SimpleHashMap.this.modCount;
                if (!rsl) {
                    throw new ConcurrentModificationException();
                }
                return rsl;
            }

            /**
             * Проверка, есть ли следующий элемент.
             * @return true, если элементы есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                this.isMod();
                return cursor > 0;
            }

            /**
             * Возвращает следующий элемент.
             * @return следующий элемент, если он есть.
             * Иначе бросается исключение типа NoSuchElementException.
             */
            @Override
            public Node<K, V> next() {
                this.isMod();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.ifNext();
            }

            /**
             * Перебирает элементы, пока не найдет != null.
             * @return элемент.
             */
            private Node<K, V> ifNext() {
                Node<K, V> node = null;
                while (node == null) {
                    node = SimpleHashMap.this.table[this.position++];
                }
                --this.cursor;
                return node;
            }
        };
    }
}
