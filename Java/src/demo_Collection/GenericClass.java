package demo_Collection;

public class GenericClass<E> {

    private E variable;

    public GenericClass() {
    }

    public GenericClass(E variable) {
        this.variable = variable;
    }

    public E getVariable() {
        return variable;
    }

    public void setVariable(E variable) {
        this.variable = variable;
    }
}
