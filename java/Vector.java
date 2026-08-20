
/**
 * Vector
 */
import java.lang.IndexOutOfBoundsException;
import java.lang.Exception;

public class Vector {

    class Item {
        Object value;
        Item(Object value) {
            this.value = value;
        }
    }

    // instance varaibles are autmatically initiallised with default values, But not local vriables inside methods
    int size; // the size here lives in Heap inside Vector Object
    Item[] data;

    // Java constructors have no return type
    public Vector(int size) { // The size here lived on stack inside this method
        this.size = size; // Saying here `this.size`, it points directly to the heap area.
        this.data = new Item[size];
    }
    
    // Only non-static and constructor methods can access `this`.
    /**
    public static void sizeChecker() {
        assert this.size < 10;
    }
     */

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Item at(int index) throws IndexOutOfBoundsException {
        if (-1 < index && index < this.size) {
            return this.data[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public int insert(Object value, int atIndex)throws Exception {
        if (-1 < atIndex && atIndex <= this.size) {
            Item item = new Item(value);
            this.size += 1;
            Item[] newData = new Item[this.size];
            for (int i = 0; i < this.size; i++) {
                if (i == atIndex) {
                    newData[atIndex] = item;
                }
                newData[i] = this.data[i];
            }
            this.data = newData;
            return atIndex;
        }
        throw new Exception("Operation not allowed!");
    }

    public int push(Object value) throws Exception{
        int atIndex = this.size;
        this.insert(value, atIndex);
        this.size += 1;
        return atIndex;
    }
}