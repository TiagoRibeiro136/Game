package Lists.DoubleLinkedList;

import Interfaces.ListADT;
import exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * Classe abstrata base para implementar uma lista duplamente encadeada.
 *
 * @param <T> o tipo de elementos armazenados na lista duplamente encadeada
 */
public abstract class DoubleLinkedList<T> implements ListADT<T> {
    protected DoubleLinkedListNode<T> head, tail;
    protected int count;

    /**
     * Constrói uma lista duplamente encadeada vazia.
     */
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Remove e retorna o primeiro elemento da lista duplamente encadeada.
     *
     * @return o primeiro elemento da lista duplamente encadeada
     * @throws EmptyCollectionException se a lista duplamente encadeada estiver vazia
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List");
        DoubleLinkedListNode<T> current = head;

        head = head.getNext();
        head.setPrevious(null);

        this.count--;
        return current.getElement();
    }

    /**
     * Remove e retorna o último elemento da lista duplamente encadeada.
     *
     * @return o último elemento da lista duplamente encadeada
     * @throws EmptyCollectionException se a lista duplamente encadeada estiver vazia
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List");
        DoubleLinkedListNode<T> current = tail;

        tail = tail.getPrevious();
        tail.setNext(null);

        this.count--;
        return current.getElement();
    }

    /**
     * Remove o elemento especificado da lista duplamente encadeada.
     *
     * @param element o elemento a ser removido
     * @return o elemento removido
     * @throws EmptyCollectionException se a lista duplamente encadeada estiver vazia
     */
    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("List");

        boolean found = false;
        DoubleLinkedListNode<T> previous = null;
        DoubleLinkedListNode<T> current = head;
        while (current != null && !found) {
            if (element.equals(current.getElement()))
                found = true;
            else {
                previous = current;
                current = current.getNext();
            }
        }
        if (!found) throw new EmptyCollectionException("List");
        if (size() == 1) {
            head = tail = null;
        }
        else if (current.equals(head)) {
            head = current.getNext();
        }
        else if (current.equals(tail)) {
            tail = previous;
            tail.setNext(null);
        }
        else {
            previous.setNext(current.getNext());
        }
        count--;
        return current.getElement();
    }

    /**
     * Retorna o primeiro elemento na lista duplamente encadeada.
     *
     * @return o primeiro elemento na lista duplamente encadeada
     * @throws EmptyCollectionException se a lista duplamente encadeada estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        T result = head.getElement();
        return result;
    }

    /**
     * Retorna o último elemento na lista duplamente encadeada.
     *
     * @return o último elemento na lista duplamente encadeada
     * @throws EmptyCollectionException se a lista duplamente encadeada estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        T result = tail.getElement();
        return result;
    }

    /**
     * Verifica se a lista duplamente encadeada contém o elemento especificado.
     *
     * @param target o elemento a ser verificado
     * @return true se o elemento for encontrado, false caso contrário
     */
    @Override
    public boolean contains(T target) {
        T data = head.getElement();
        boolean found = false;
        int i = 0;
        while (i < this.count && !found) {
            if (target.equals(data)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Verifica se a lista duplamente encadeada está vazia.
     *
     * @return true se a lista duplamente encadeada estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o tamanho da lista duplamente encadeada.
     *
     * @return o tamanho da lista duplamente encadeada
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Retorna um iterador sobre os elementos na lista duplamente encadeada.
     *
     * @return um iterador sobre os elementos na lista duplamente encadeada
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<>(this);
    }

    /**
     * Implementação do iterador para a lista duplamente encadeada.
     */
    public class BasicIterator<T> implements Iterator<T> {
        private DoubleLinkedListNode<T> current;

        public BasicIterator(DoubleLinkedList<T> list) {
            current = list.head;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T data = current.getElement();
            current = current.getNext();
            return data;
        }
    }

    /**
     * Retorna uma representação em string da lista duplamente encadeada.
     *
     * @return uma representação em string da lista duplamente encadeada
     */
    @Override
    public String toString() {
        DoubleLinkedListNode<T> current = this.head;
        String s = "DoubleLinkedList:\n";
        while(current != null) {
            s += current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return s;
    }
}
